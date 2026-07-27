package com.example.car_service.domain.dto.car_owner;

import jakarta.validation.constraints.Email;

public record CarOwnerCreateRequest(
        String fullName,

        String phone,

        @Email
        String email
) {
}
