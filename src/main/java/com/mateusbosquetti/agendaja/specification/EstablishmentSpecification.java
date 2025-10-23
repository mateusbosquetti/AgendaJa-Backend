package com.mateusbosquetti.agendaja.specification;

import com.mateusbosquetti.agendaja.model.entity.Establishment;
import org.springframework.data.jpa.domain.Specification;

public class EstablishmentSpecification {

    public static Specification<Establishment> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                                return criteriaBuilder.conjunction(); // no filter
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("userName")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }
}