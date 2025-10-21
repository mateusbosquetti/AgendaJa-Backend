package com.mateusbosquetti.agendaja.specification;

import com.mateusbosquetti.agendaja.model.entity.Address;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import org.springframework.data.jpa.domain.Specification;

public class AddressSpecification {

    public static Specification<Address> cityContains(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null || city.trim().isEmpty()) {
                                return criteriaBuilder.conjunction(); // no filter
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("city")),
                    "%" + city.toLowerCase() + "%"
            );
        };
    }
}