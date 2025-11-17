package com.mateusbosquetti.agendaja.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "profilePicture")
    @JsonIgnore
    List<User> users;

    @OneToMany(mappedBy = "logo")
    @JsonIgnore
    List<Establishment> establishments;

}
