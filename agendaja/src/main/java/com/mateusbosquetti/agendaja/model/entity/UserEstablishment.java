package com.mateusbosquetti.agendaja.model.entity;

import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "user_establishment_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class UserEstablishment extends BaseEntity {

    @EmbeddedId
    private UserEstablishmentId id;

    @ManyToOne
    @MapsId("establishmentId")
    @JoinColumn(name = "establishment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_establishment_roles_establishment"))
    private Establishment establishment;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_establishment_roles_user"))
    private User user;

}
