package com.example.car_service.domain.dto.owner;

import java.util.List;

public record OwnerPageResponse(
        List<OwnerResponse> owners,

        int page,

        int size,

        long totalElements,

        int totalPages
) {
}
