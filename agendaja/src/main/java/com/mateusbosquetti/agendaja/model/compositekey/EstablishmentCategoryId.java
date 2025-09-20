package com.mateusbosquetti.agendaja.model.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EstablishmentCategoryId implements Serializable {

    @Column(name = "establishment_id")
    @EqualsAndHashCode.Include
    private Long establishmentId;
    @Column(name = "category_id")
    @EqualsAndHashCode.Include
    private Long categoryId;

}
