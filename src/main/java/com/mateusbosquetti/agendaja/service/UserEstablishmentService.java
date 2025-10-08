package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.UserEstablishmentMapper;
import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import com.mateusbosquetti.agendaja.repository.UserEstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserEstablishmentService {

    private final UserEstablishmentRepository repository;

    public UserEstablishmentResponseDTO createUserEstablishment(User user, Establishment establishment, FunctionRole role) {
        UserEstablishmentId id = UserEstablishmentId.builder()
                .establishmentId(establishment.getId())
                .userId(user.getId())
                .functionRole(role)
                .build();

        UserEstablishment userEstablishment = UserEstablishment.builder()
                .id(id)
                .user(user)
                .establishment(establishment)
                .build();

        userEstablishment = repository.save(userEstablishment);

        return UserEstablishmentMapper.toDTO(userEstablishment);
    }

    public List<UserEstablishment> getEstablishmentUsersByEstablishmentIdAndFunction(Long establishmentId, FunctionRole functionRole) {
        return repository.findUserEstablishmentById_EstablishmentIdAndId_FunctionRole(establishmentId, functionRole);
    }

    public List<UserEstablishment> getUserEstablishmentsByUserId(Long userId) {
        return repository.findAllById_UserId(userId);
    }

    public UserEstablishment getUserEstablishmentByUserEmailAndEstablishmentId(String userEmail, Long establishmentId) {
                return repository.findById_EstablishmentIdAndUser_UserAuthentication_Email(establishmentId, userEmail)
                .orElseThrow(() -> new RuntimeException("UserEstablishment not found for establishmentId=" + establishmentId + ", userEmail=" + userEmail));
    }

    public void disableUserEstablishment(Long establishmentId, Long userId) {
        repository.deleteById_EstablishmentIdAndId_UserId(establishmentId, userId);
    }

}
