package com.mateusbosquetti.agendaja.model.compositekey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentCategoryId implements Serializable {

    @Column(name = "establishment_id")
    @EqualsAndHashCode.Include
    private Long establishmentId;
    @Column(name = "category_id")
    @EqualsAndHashCode.Include
    private Long categoryId;

}
