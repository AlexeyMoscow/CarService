package com.example.car_service.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car_owners")
public class CarOwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID ownerId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<CarEntity> cars;

    @CreationTimestamp
    @Column(name = "record_created_at")
    private ZonedDateTime recordCreatedAt;

    @CreationTimestamp
    @Column(name = "record_updated_at")
    private ZonedDateTime recordUpdatedAt;
}