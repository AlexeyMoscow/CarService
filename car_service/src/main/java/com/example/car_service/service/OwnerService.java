package com.example.car_service.service;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.domain.dto.owner.OwnerUpdateRequest;
import jakarta.validation.Valid;

import java.util.UUID;

public interface OwnerService {
    OwnerResponse create(@Valid OwnerCreateRequest request);

    OwnerResponse findById(UUID id);

    OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner);

    void deleteOwnerById(UUID id);
}
