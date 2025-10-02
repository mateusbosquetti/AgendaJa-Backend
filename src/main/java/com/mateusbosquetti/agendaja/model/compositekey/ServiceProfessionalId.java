package com.mateusbosquetti.agendaja.model.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceProfessionalId implements Serializable {

    @Column(name = "service_id")
    @EqualsAndHashCode.Include
    private Long serviceId;
    @Column(name = "professional_id")
    @EqualsAndHashCode.Include
    private Long professionalId;

}
