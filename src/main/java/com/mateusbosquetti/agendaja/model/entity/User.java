package com.mateusbosquetti.agendaja.model.entity;

import com.mateusbosquetti.agendaja.model.enums.ThemeEnum;
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
@ToString(exclude = {"appointmentsAsClient", "appointmentsAsProfessional", "servicesAsProfessional"})
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

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_user_photo"))
    private File profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ThemeEnum theme = ThemeEnum.DARK;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAuthentication userAuthentication;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true, foreignKey = @ForeignKey(name = "fk_user_address"))
    private Address address;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointmentsAsClient;
    @OneToMany(mappedBy = "professional")
    private List<Appointment> appointmentsAsProfessional;
    @OneToMany(mappedBy = "professional", orphanRemoval = true)
    private List<ServiceProfessional> servicesAsProfessional;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<UserEstablishment> establishmentsRelated;

    @PrePersist
    public void prePersist() {
        if (this.profilePicture == null) {
            this.profilePicture = new File();
            this.profilePicture.setId(1L);
        }
    }

}
