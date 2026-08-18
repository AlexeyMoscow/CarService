package com.example.car_service.util;

import com.example.car_service.domain.entity.CarEntity;
import com.example.car_service.domain.entity.OwnerEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OwnerSpecification {

    public static Specification<OwnerEntity> ownerSpecification(

            List<String> owners,

            String phone,

            String email,

            LocalDate createdFrom,

            LocalDate createdTo,

            LocalDate updatedFrom,

            LocalDate updatedTo
    ) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (owners != null && !owners.isEmpty()) {
                predicates.add(root.get("fullName").in(owners));
            }

            if (phone != null) {
                predicates.add(criteriaBuilder.equal(root.get("phone"), phone));
            }

            if (email != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), email));
            }

            Optional.ofNullable(createdFrom)
                    .map(
                            startCreatedDate -> createdTo == null
                                    ? criteriaBuilder.greaterThanOrEqualTo(root.get("recordCreatedAt"), startCreatedDate)
                                    : criteriaBuilder.between(root.get("recordCreatedAt"), startCreatedDate, createdTo)
                    ).ifPresent(predicates::add);

            Optional.ofNullable(updatedFrom)
                    .map(
                            startUpdateDate -> updatedTo == null
                                    ? criteriaBuilder.greaterThanOrEqualTo(root.get("recordUpdatedAt"), startUpdateDate)
                                    : criteriaBuilder.between(root.get("recordUpdatedAt"), startUpdateDate, updatedTo)
                    ).ifPresent(predicates::add);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}
