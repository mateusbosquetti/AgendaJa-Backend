package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.Service;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticationRepository extends JpaRepository<UserAuthentication, Long> {
}
