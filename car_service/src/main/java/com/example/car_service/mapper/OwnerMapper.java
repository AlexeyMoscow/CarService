package com.example.car_service.mapper;

import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.domain.dto.owner.OwnerUpdateRequest;
import com.example.car_service.domain.entity.OwnerEntity;
import org.mapstruct.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(target = "fullName", source = "fullName", qualifiedByName = "formatFullName")

    @Mapping(target = "email", constant = "hardcode@test.com")

    @Mapping(target = "createdAt", source = "recordCreatedAt",  qualifiedByName = "normalizeDateTime")

    @Mapping(target = "updatedAt", source = "recordUpdatedAt",  qualifiedByName = "normalizeDateTime")
    OwnerResponse toDto(OwnerEntity entity);

    @Named("formatFullName")
    default String formatFullName(String name) {
        return "ФИО: " + name;
    }

    @Named("normalizeDateTime")
    default ZonedDateTime normalizeDateTime(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        return dateTime.truncatedTo(ChronoUnit.MINUTES);
    }

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateEntity(
            OwnerUpdateRequest request,
            @MappingTarget OwnerEntity entity
    );

}
