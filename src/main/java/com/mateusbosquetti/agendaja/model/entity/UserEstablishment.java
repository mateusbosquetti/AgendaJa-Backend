package com.mateusbosquetti.agendaja.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "user_establishment_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class UserEstablishment {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserEstablishmentId id;

    @ManyToOne()
    @MapsId("establishmentId")
    @JoinColumn(name = "establishment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_establishment_roles_establishment"))
    private Establishment establishment;

    @ManyToOne()
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_establishment_roles_user"))
    private User user;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private FunctionRole functionRole;

}
