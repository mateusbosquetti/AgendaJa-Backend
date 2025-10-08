package com.mateusbosquetti.agendaja.model.entity;

import com.mateusbosquetti.agendaja.model.compositekey.ServiceProfessionalId;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "service_professionals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ServiceProfessional {

    @EmbeddedId
    private ServiceProfessionalId id;

    @ManyToOne()
    @MapsId("serviceId")
    @JoinColumn(name = "service_id", nullable = false, foreignKey = @ForeignKey(name = "fk_service_professional_service"))
    private ServiceEntity service;

    @ManyToOne()
    @MapsId("professionalId")
    @JoinColumn(name = "professional_id", nullable = false, foreignKey = @ForeignKey(name = "fk_service_professional_professional"))
    private User professional;

}
