package com.example.car_service.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car_services")
public class CarServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID serviceId;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "service_center_id", nullable = false)
    private ServiceCenterEntity serviceCenter;

    @Column(name = "service_date", nullable = false)
    private ZonedDateTime serviceDate;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;
}