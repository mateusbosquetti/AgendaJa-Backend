package com.mateusbosquetti.agendaja.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String key;
    @Column()
    private String name;

    @OneToOne(mappedBy = "profilePicture")
    @JsonIgnore
    @ToString.Exclude
    User user;

    @OneToOne(mappedBy = "logo")
    @JsonIgnore
    @ToString.Exclude
    Establishment establishment;

}
