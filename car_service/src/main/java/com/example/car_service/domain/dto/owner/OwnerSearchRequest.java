package com.example.car_service.domain.dto.owner;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record OwnerSearchRequest(

        @Size(
                min = 2, max = 100,
                message = "Full name must contain from 2 to 100 characters")
        @Pattern(
                regexp = ".*\\S.*",
                message = "Full name must not be blank"
        )
        String fullName,


        @Pattern(
                regexp = "^7\\d{10}$",
                message = "Phone must start with 7 and contain 11 digits"
        )
        String phone,


        @Email(
                message = "Email has invalid format")
        @Pattern(
                regexp = ".*\\S.*",
                message = "Email must not be blank"
        )
        String email,

        @PastOrPresent(
                message = "Updated from must not be in the future")
        LocalDate updatedFrom,

        @PastOrPresent(
                message = "Updated to must not be in the future")
        LocalDate updatedTo,

        @Min(value = 0, message = "Page must be greater than or equal to 0")
        Integer page,

        @Min(value = 1, message = "Size must be greater than or equal to 1")
        @Max(value = 100, message = "Size must not exceed 100")
        Integer size,

        @Pattern(
                regexp = "^(fullName|phone|email|updatedAt)$",
                message = "Unsupported sorting field"
        )
        String sortBy,

        @Pattern(
                regexp = "(?i)^(asc|desc)$",
                message = "Direction must be asc or desc"
        )
        String direction





) {
}
