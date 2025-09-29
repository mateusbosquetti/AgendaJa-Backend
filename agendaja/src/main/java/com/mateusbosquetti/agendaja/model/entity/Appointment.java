package com.mateusbosquetti.agendaja.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Appointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "fk_appointment_client"))
    private User client;

    @ManyToOne
    @JoinColumn(name = "professional_id", nullable = false, foreignKey = @ForeignKey(name = "fk_appointment_professional"))
    private User professional;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false, foreignKey = @ForeignKey(name = "fk_appointment_service"))
    private ServiceEntity serviceEntity;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column()
    private String note;

}
