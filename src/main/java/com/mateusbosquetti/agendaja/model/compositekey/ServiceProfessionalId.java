package com.mateusbosquetti.agendaja.model.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceProfessionalId implements Serializable {

    @Column(name = "service_id")
    @EqualsAndHashCode.Include
    private Long serviceId;
    @Column(name = "professional_id")
    @EqualsAndHashCode.Include
    private Long professionalId;

}
