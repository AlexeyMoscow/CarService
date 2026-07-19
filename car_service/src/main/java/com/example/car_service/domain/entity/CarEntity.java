package com.example.car_service.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID carId;

    @Column(name = "vin", nullable = false, unique = true)
    private String vin;

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "production_year")
    private Integer year;

    @Column(name = "mileage", nullable = false)
    private int mileage;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "model", nullable = false)
    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private CarOwnerEntity owner;

    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;
}