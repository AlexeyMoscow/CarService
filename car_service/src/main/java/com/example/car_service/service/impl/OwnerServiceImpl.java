package com.example.car_service.service.impl;

import com.example.car_service.domain.dto.owner.*;
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
    public OwnerPageResponse findWithFilter(OwnerSearchRequest filter) {

        Page<OwnerResponse> ownersPage = Page.empty();

        return new OwnerPageResponse(
                ownersPage.getContent(),
                ownersPage.getNumber(),
                ownersPage.getSize(),
                ownersPage.getTotalElements(),
                ownersPage.getTotalPages()
        );
    }

    @Override
    public OwnerResponse updateOwnerById(UUID id, OwnerUpdateRequest updatedOwner) {
        return null;
    }

    @Override
    public void deleteOwnerById(UUID id) {

    }
}
