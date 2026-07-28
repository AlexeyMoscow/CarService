package com.example.car_service.domain.dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OwnerCreateRequest(
        @NotBlank(message = "Full name is required")
        @Size(min = 2, max = 100, message = "Full name must contain from 2 to 100 characters")
        String fullName,

        @NotBlank(message = "Phone is required")
        @Pattern(
                regexp = "^7\\d{10}$",
                message = "Phone must start with 7 and contain 11 digits"
        )
        String phone,

        @NotBlank(message = "Email is required")
        @Email(message = "Email has invalid format")
        String email
) {
}
