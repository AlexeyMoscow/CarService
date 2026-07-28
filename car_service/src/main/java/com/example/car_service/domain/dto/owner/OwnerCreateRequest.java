package com.example.car_service.domain.dto.owner;

import jakarta.validation.constraints.Email;

public record OwnerCreateRequest(
        String fullName,

        String phone,

        @Email
        String email
) {
}
