package com.example.car_service.service;

import com.example.car_service.domain.dto.owner.*;

import java.util.UUID;

public interface OwnerService {
    OwnerResponse create(OwnerCreateRequest request);

    OwnerResponse findById(UUID id);

    OwnerPageResponse findWithFilter(OwnerSearchRequest filter);

    OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner);

    void hardDeleteOwnerById(UUID id);

    void softDeleteOwnerById(UUID id);

    void restoreOwner(UUID uuid);
}
