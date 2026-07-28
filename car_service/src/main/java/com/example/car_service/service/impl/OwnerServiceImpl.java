package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.domain.dto.owner.OwnerUpdateRequest;
import com.example.car_service.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Override
    public OwnerResponse create(@Valid OwnerCreateRequest request) {

        return null;
    }

    @Override
    public OwnerResponse findById(UUID id) {
        return null;
    }

    @Override
    public OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner) {
        return null;
    }
}
