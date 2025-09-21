package com.mateusbosquetti.agendaja.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name = "users_authentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
//TODO: Add interface contract to implement others attributes and methods
//https://docs.google.com/document/d/1h8p_Tuf534-qPC_OsS2Kk8ThFmNw-uCTvIJUro9BqSc/edit?tab=t.0
public class UserAuthentication extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToOne()
    @JoinColumn(name = "user_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_user_authentication_user"))
    private User user;

}
