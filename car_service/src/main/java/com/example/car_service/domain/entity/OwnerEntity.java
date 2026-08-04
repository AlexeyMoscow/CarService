package com.example.car_service.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car_owners")
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Builder.Default
    @OneToMany(mappedBy = "owner")
    private List<CarEntity> cars = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "record_created_at")
    private ZonedDateTime recordCreatedAt;

    @UpdateTimestamp
    @Column(name = "record_updated_at")
    private ZonedDateTime recordUpdatedAt;
}
