package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerSearchRequest;
import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.domain.dto.owner.OwnerUpdateRequest;
import com.example.car_service.service.OwnerService;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<OwnerResponse> findWithFilter(OwnerSearchRequest filter) {
        return Page.empty();
    }

    @Override
    public OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner) {
        return null;
    }

    @Override
    public void deleteOwnerById(UUID id) {

    }
}
