package com.example.car_service.domain.dto.owner;

import com.example.car_service.domain.entity.CarEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record OwnerResponse(

        UUID id,

        String fullName,

        String phone,

        String email,

        List<CarEntity> cars,

        @JsonFormat(pattern = "HH:mm - dd.MM.yyyy")
        ZonedDateTime createdAt,

        @JsonFormat(pattern = "HH:mm - dd.MM.yyyy")
        ZonedDateTime updatedAt

) {
}
