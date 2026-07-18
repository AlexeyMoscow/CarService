package com.example.car_service.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "id")
    private UUID carId;

    @Column (name = "year")
    private int year;
    @Column (name = "producer")
    private String producer;
    private String model;
    private ZonedDateTime createdAt;

    @ManyToMany
    private List<ServiceCenterEntity> serviceCenters;
}
