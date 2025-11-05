package com.mateusbosquetti.agendaja.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Builder
@Entity(name = "establishments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SQLDelete(sql = "UPDATE establishments SET disabled = true WHERE id = ?")
@Where(clause = "disabled = false")
public class Establishment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_establishment_logo"))
    private File logo;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "address_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_user_address"))
    private Address address;

    @OneToMany(mappedBy = "establishment", orphanRemoval = true)
    private List<ServiceEntity> serviceEntities;
    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEstablishment> usersRelated;

    @PrePersist
    public void prePersist() {
        if (this.logo == null) {
            this.logo = new File();
            this.logo.setId(2L);
        }
    }

}
