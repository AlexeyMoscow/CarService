package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.*;
import com.example.car_service.domain.entity.OwnerEntity;
import com.example.car_service.mapper.OwnerMapper;
import com.example.car_service.repository.OwnerRepository;
import com.example.car_service.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

        OwnerEntity saved = repository.save(owner);

        return ownerMapper.toDto(saved);
    }

    @Override
    public OwnerResponse findById(UUID id) {
        return null;
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
    public OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner) {
        return null;
    }

    @Override
    public void deleteOwnerById(UUID id) {

    }
}
