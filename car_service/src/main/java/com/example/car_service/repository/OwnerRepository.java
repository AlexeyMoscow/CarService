package com.example.car_service.repository;

import com.example.car_service.domain.entity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<OwnerEntity, UUID> {
    List<OwnerEntity> findByPhoneAndEmail(String phone, String email);

    Optional<OwnerEntity> findByIdAndDeletedAtIsNull(UUID id);

    @Query(value = """
            SELECT o from OwnerEntity o
            WHERE o.phone =: phone
            """, nativeQuery = true)
    List<OwnerEntity> findByPhone(String phone);
}
