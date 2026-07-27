package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.car_owner.CarOwnerCreateRequest;
import com.example.car_service.domain.dto.car_owner.CarOwnerCreateResponse;
import com.example.car_service.service.CarOwnerService;
import org.springframework.stereotype.Service;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {
    @Override
    public CarOwnerCreateResponse create(CarOwnerCreateRequest request) {
        return null;
    }
}
