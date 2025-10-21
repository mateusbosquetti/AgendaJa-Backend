package com.mateusbosquetti.agendaja.model.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEstablishmentId implements Serializable {

    @Column(name = "establishment_id")
    @EqualsAndHashCode.Include
    private Long establishmentId;
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long userId;

}
