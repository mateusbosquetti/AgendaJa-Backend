package com.mateusbosquetti.agendaja.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Builder
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SQLDelete(sql = "UPDATE users SET disabled = true WHERE id = ?")
@Where(clause = "disabled = false")
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

    @OneToOne
    @JoinColumn(name = "address_id", unique = true, foreignKey = @ForeignKey(name = "fk_user_address"))
    private Address address;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Appointment> appointmentsAsClient;
    @OneToMany(mappedBy = "professional", fetch = FetchType.LAZY)
    private List<Appointment> appointmentsAsProfessional;
    @OneToMany(mappedBy = "professional", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ServiceProfessional> servicesAsProfessional;

}
