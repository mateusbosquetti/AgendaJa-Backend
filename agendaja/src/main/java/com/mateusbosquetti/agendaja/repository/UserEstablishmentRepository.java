package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEstablishmentRepository extends JpaRepository<UserEstablishment, UserEstablishmentId> {
    List<UserEstablishment> findUserEstablishmentById_EstablishmentIdAndId_FunctionRole(Long idEstablishmentId, FunctionRole idFunctionRole);

    List<UserEstablishment> findAllById_UserId(Long idUserId);
}
