package com.example.car_service.mapper;

import com.example.car_service.domain.dto.owner.OwnerResponse;
import com.example.car_service.domain.entity.OwnerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    OwnerResponse toDto(OwnerEntity entity);

    default String getFullName(OwnerEntity ownerEntity) {
        return ownerEntity.getFullName();
    }
}
