package com.example.car_service.service;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerCreateResponse;

public interface OwnerService {
    OwnerCreateResponse create(OwnerCreateRequest request);
}
