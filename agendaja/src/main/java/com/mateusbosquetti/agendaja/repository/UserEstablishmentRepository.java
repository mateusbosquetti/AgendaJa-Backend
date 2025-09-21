package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEstablishmentRepository extends JpaRepository<UserEstablishment, UserEstablishmentId> {
}
