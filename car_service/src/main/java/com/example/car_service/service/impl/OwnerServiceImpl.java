package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.OwnerCreateRequest;
import com.example.car_service.domain.dto.owner.OwnerCreateResponse;
import com.example.car_service.service.OwnerService;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Override
    public OwnerCreateResponse create(OwnerCreateRequest request) {
        return null;
    }
}
