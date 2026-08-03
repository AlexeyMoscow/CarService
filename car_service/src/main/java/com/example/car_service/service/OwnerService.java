package com.example.car_service.service;

import com.example.car_service.domain.dto.owner.*;
import jakarta.validation.Valid;

import java.util.UUID;

public interface OwnerService {
    OwnerResponse create(OwnerCreateRequest request);

    OwnerResponse findById(UUID id);

    OwnerPageResponse findWithFilter(OwnerSearchRequest filter);

    OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner);

    void deleteOwnerById(UUID id);
}
