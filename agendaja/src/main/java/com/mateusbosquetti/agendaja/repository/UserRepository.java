package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
