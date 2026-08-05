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

        OwnerEntity owner = repository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow( () ->
                        new RuntimeException("Owner with provided id:" + id + " not found"));
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

        OwnerEntity owner = repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Owner with provided id -" + id + " not found"));

        ownerMapper.updateEntity(updatedOwner, owner);

        OwnerEntity saved = repository.saveAndFlush(owner);

        return ownerMapper.toDto(saved);

    }

    @Override
    @Transactional
    public void hardDeleteOwnerById(UUID id) {

        OwnerEntity owner = repository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Owner with provided id -" + id + " not found"));

        if (!owner.getCars().isEmpty()) {
            throw new RuntimeException("Owner with linked cars cannot be deactivated");
        }

        repository.delete(owner);
    }

    @Override
    @Transactional
    public void softDeleteOwnerById(UUID id) {
        OwnerEntity owner = repository.findById(id)
                .orElseThrow( () ->
                        new RuntimeException("Owner with provided id -" + id + " not found"));

        if (owner.getDeletedAt() != null) {
            throw new RuntimeException("Owner with provided id " + id + " is already deleted");
        }
        if (!owner.getCars().isEmpty()) {
            throw new RuntimeException("Owner with linked cars cannot be deactivated");
        }

        owner.setDeletedAt(ZonedDateTime.now());
    }

    @Override
    public void restoreOwner(UUID id) {
        OwnerEntity owner = repository.findById(id)
                .orElseThrow( () ->
                        new RuntimeException("Owner with provided id -" + id + " not found"));
        owner.setDeletedAt(null);
    }
}
