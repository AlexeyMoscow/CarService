package com.example.car_service.domain.dto.owner;

import com.example.car_service.domain.entity.CarEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public record OwnerResponse(

        UUID id,

        String fullName,

        String phone,

        String email,

        List<CarEntity> cars,

        ZonedDateTime createdAt,

        ZonedDateTime updatedAt

) {
}
