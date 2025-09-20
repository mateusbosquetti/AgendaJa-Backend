package com.mateusbosquetti.agendaja.model.compositekey;

import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEstablishmentId implements Serializable {

    @Column(name = "establishment_id")
    @EqualsAndHashCode.Include
    private Long establishmentId;
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long userId;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Include
    private FunctionRole functionRole;

}
