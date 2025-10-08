package com.mateusbosquetti.agendaja.model.entity;

import com.mateusbosquetti.agendaja.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Entity(name = "users_authentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SQLDelete(sql = "UPDATE users_authentication SET disabled = true WHERE id = ?")
@Where(clause = "disabled = false")
@ToString(exclude = {"password", "user"})
public class UserAuthentication extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_user_authentication_user"))
    private User user;

    @Column(nullable = false)
    private boolean accountNonExpired;
    @Column(nullable = false)
    private boolean accountNonLocked;
    @Column(nullable = false)
    private boolean credentialsNonExpired;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return !this.getDisabled();
    }


}
