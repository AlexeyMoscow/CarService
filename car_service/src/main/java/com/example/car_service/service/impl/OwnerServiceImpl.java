package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.*;
import com.example.car_service.domain.entity.OwnerEntity;
import com.example.car_service.mapper.OwnerMapper;
import com.example.car_service.repository.OwnerRepository;
import com.example.car_service.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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

        OwnerEntity saved = repository.saveAndFlush(owner);

        return ownerMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerResponse findById(UUID id) {

        OwnerEntity owner = getOwnerEntityById(id);
        return ownerMapper.toDto(owner);
    }

    @Override
    public OwnerPageResponse findWithFilter(OwnerSearchRequest filter) {

        Page<OwnerResponse> ownersPage = Page.empty();

        return new OwnerPageResponse(
                ownersPage.getContent(),
                ownersPage.getNumber(),
                ownersPage.getSize(),
                ownersPage.getTotalElements(),
                ownersPage.getTotalPages()
        );
    }

    @Override
    @Transactional
    public OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner) {

        OwnerEntity owner = getOwnerEntityById(id);
        ownerMapper.updateEntity(updatedOwner, owner);

        OwnerEntity saved = repository.saveAndFlush(owner);
        return ownerMapper.toDto(saved);

    }

    @Override
    @Transactional
    public void hardDeleteOwnerById(UUID id) {

        OwnerEntity owner = getOwnerEntityById(id);

        checkOwnerHasCars(owner);

        repository.delete(owner);
    }

    @Override
    @Transactional
    public void softDeleteOwnerById(UUID id) {

        OwnerEntity owner = getOwnerEntityById(id);

        checkOwnerIsDeleted(id, owner);
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
            throw new RuntimeException("Owner with linked cars cannot be deactivated");
        }
    }

    private static void checkOwnerIsDeleted(UUID id, OwnerEntity owner) {
        if (owner.getDeletedAt() != null) {
            throw new RuntimeException("Owner with provided id " + id + " is already deleted");
        }
    }

    private static void checkOwnerCanBeRestored(UUID id, OwnerEntity owner) {
        if (owner.getDeletedAt() == null) {
            throw new RuntimeException("Owner with provided id " + id + " is not deleted");
        }
    }

    private OwnerEntity getOwnerEntityById(UUID id) {
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
