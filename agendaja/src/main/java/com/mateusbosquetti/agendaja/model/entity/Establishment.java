package com.mateusbosquetti.agendaja.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity(name = "establishments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Establishment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_user_address"))
    private Address address;

    @OneToMany(mappedBy = "establishment", orphanRemoval = true)
    private List<Service> services;

}
