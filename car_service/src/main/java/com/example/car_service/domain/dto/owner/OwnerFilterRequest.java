package com.example.car_service.domain.dto.owner;

public record OwnerFilterRequest(

        String fullName,

        String phone,

        String email

) {
}
