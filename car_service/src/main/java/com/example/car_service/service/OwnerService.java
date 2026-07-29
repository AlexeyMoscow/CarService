package com.example.car_service.service;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerFilterRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.domain.dto.owner.OwnerUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OwnerService {
    OwnerResponse create(@Valid OwnerCreateRequest request);

    OwnerResponse findById(UUID id);

    Page<OwnerResponse> findWithFilter(OwnerFilterRequest filter, Pageable pageable);

    OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner);

    void deleteOwnerById(UUID id);
}
