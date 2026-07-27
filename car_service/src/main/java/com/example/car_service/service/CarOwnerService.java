package com.example.car_service.service;

import com.example.car_service.domain.dto.car_owner.CarOwnerCreateRequest;
import com.example.car_service.domain.dto.car_owner.CarOwnerCreateResponse;

public interface CarOwnerService {
    CarOwnerCreateResponse create(CarOwnerCreateRequest request);
}
