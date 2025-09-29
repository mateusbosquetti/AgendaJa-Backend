package com.mateusbosquetti.agendaja.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(unique = true, length = 11)
    private String cpf;
    @Column(unique = true, length = 11)
    private String phone;

    @OneToOne(mappedBy = "user")
    private UserAuthentication userAuthentication;

    @OneToOne()
    @JoinColumn(name = "address_id", unique = true, foreignKey = @ForeignKey(name = "fk_user_address"))
    private Address address;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointmentsAsClient;
    @OneToMany(mappedBy = "professional")
    private List<Appointment> appointmentsAsProfessional;
    @OneToMany(mappedBy = "professional", orphanRemoval = true)
    private List<ServiceProfessional> servicesAsProfessional;

}
