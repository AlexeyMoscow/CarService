package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.*;
import com.example.car_service.domain.entity.OwnerEntity;
import com.example.car_service.exception.OwnerBusinessException;
import com.example.car_service.mapper.OwnerMapper;
import com.example.car_service.repository.OwnerRepository;
import com.example.car_service.service.OwnerService;
import com.example.car_service.util.OwnerSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository repository;
    private final OwnerMapper ownerMapper;

    @Override
    @Transactional
    public OwnerResponse create(OwnerCreateRequest request) {

        OwnerEntity owner = OwnerEntity.builder()
                .fullName(request.fullName())
                .phone(request.phone())
                .email(request.email())
                .build();

        try {
            throw new DataAccessResourceFailureException("Ошибка БД");

         //   owner = repository.saveAndFlush(owner);
        } catch (RuntimeException e) {
            log.error("Здесь выпала ошибка: {}", e.getMessage(), e);
            throw new OwnerBusinessException("Ошибка взаимодействия с БД");
        }

       // log.debug("Сущность создана: {}", owner.getId());

      //  return ownerMapper.toDto(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerResponse findById(UUID id) {

        OwnerEntity owner = getActiveOwnerEntityById(id);
        return ownerMapper.toDto(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerPageResponse findWithFilter(OwnerSearchRequest filter) {

        Pageable pageable = PageRequest.of(
                filter.page(),
                filter.size(),
                Sort.by(Sort.Direction.fromString(filter.direction()),
                        filter.sortBy())
        );

        Specification<OwnerEntity> specification = OwnerSpecification.ownerSpecification(
                filter.owners(),
                filter.phone(),
                filter.email(),
                filter.createdFrom(),
                filter.createdTo(),
                filter.updatedFrom(),
                filter.updatedTo()
        );

        Page<OwnerEntity> ownersPage = repository.findAll(specification, pageable);

        return new OwnerPageResponse(
                ownersPage.getContent().stream().map(ownerMapper::toDto).toList(),
                ownersPage.getNumber(),
                ownersPage.getSize(),
                ownersPage.getTotalElements(),
                ownersPage.getTotalPages()
        );
    }

    @Override
    @Transactional
    public OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner) {

        OwnerEntity owner = getActiveOwnerEntityById(id);
        ownerMapper.updateEntity(updatedOwner, owner);

        OwnerEntity saved = repository.saveAndFlush(owner);
        return ownerMapper.toDto(saved);

    }

    @Override
    @Transactional
    public void hardDeleteOwnerById(UUID id) {

        OwnerEntity owner = getOwnerEntityByIdIncludingDeleted(id);

        checkOwnerHasCars(owner);

        repository.delete(owner);
    }

    @Override
    @Transactional
    public void softDeleteOwnerById(UUID id) {

        OwnerEntity owner = getOwnerEntityByIdIncludingDeleted(id);

        checkOwnerCanBeSoftDeleted(id, owner);
        checkOwnerHasCars(owner);

        owner.setDeletedAt(ZonedDateTime.now());
    }

    @Override
    @Transactional
    public void restoreOwner(UUID id) {
        OwnerEntity owner = getOwnerEntityByIdIncludingDeleted(id);

        checkOwnerCanBeRestored(id, owner);

        owner.setDeletedAt(null);
    }

    private static void checkOwnerHasCars(OwnerEntity owner) {
        if (!owner.getCars().isEmpty()) {
            throw new RuntimeException("Owner with linked cars cannot be deleted");
        }
    }

    private static void checkOwnerCanBeSoftDeleted(UUID id, OwnerEntity owner) {
        if (owner.getDeletedAt() != null) {
            throw new RuntimeException("Owner with provided id " + id + " is already deleted");
        }
    }

    private static void checkOwnerCanBeRestored(UUID id, OwnerEntity owner) {
        if (owner.getDeletedAt() == null) {
            throw new RuntimeException("Owner with provided id " + id + " is not deleted");
        }
    }

    private OwnerEntity getActiveOwnerEntityById(UUID id) {
        return repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow( () ->
                        new RuntimeException("Owner with provided id:" + id + " not found"));
    }

    private OwnerEntity getOwnerEntityByIdIncludingDeleted(UUID id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Owner with provided id: " + id + " not found"));
    }
}
