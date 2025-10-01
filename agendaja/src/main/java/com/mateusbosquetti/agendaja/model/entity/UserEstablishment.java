package com.mateusbosquetti.agendaja.model.entity;

import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Builder
@Entity(name = "user_establishment_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SQLDelete(sql = "UPDATE user_establishment_roles SET disabled = true WHERE user_id = ? AND establishment_id = ?")
@Where(clause = "disabled = false")
public class UserEstablishment extends BaseEntity {

    @EmbeddedId
    private UserEstablishmentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("establishmentId")
    @JoinColumn(name = "establishment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_establishment_roles_establishment"))
    private Establishment establishment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_establishment_roles_user"))
    private User user;

}
