package com.example.car_service.service;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;

import java.util.UUID;

public interface OwnerService {
    OwnerResponse create(OwnerCreateRequest request);

    OwnerResponse findById(UUID id);
}
