package com.example.car_service.domain.dto.owner;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record OwnerFilterRequest(

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
                message = "Updated from must not be in the future")
        LocalDate updatedTo

) {
}
