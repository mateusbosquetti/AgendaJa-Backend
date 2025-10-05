package com.mateusbosquetti.agendaja.model.entity;

import com.mateusbosquetti.agendaja.model.compositekey.EstablishmentCategoryId;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "establishment_categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class EstablishmentCategory {

    @EmbeddedId
    private EstablishmentCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("establishmentId")
    @JoinColumn(name = "establishment_id", nullable = false, foreignKey = @ForeignKey(name = "fk_establishment_category_establishment"))
    private Establishment establishment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id", nullable = false, foreignKey = @ForeignKey(name = "fk_establishment_category_category"))
    private Category category;

}
