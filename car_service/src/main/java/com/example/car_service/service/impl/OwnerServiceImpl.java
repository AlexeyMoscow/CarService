package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Override
    public OwnerResponse create(OwnerCreateRequest request) {
        return null;
    }

    @Override
    public OwnerResponse findById(UUID id) {
        return null;
    }
}
