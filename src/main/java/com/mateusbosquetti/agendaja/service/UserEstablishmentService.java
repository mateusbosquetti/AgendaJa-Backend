package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.UserEstablishmentMapper;
import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.dto.request.UserEstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import com.mateusbosquetti.agendaja.repository.UserEstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserEstablishmentService {

    private final UserEstablishmentRepository repository;
    private final UserService userService;
    private final EstablishmentService establishmentService;

    public UserEstablishmentResponseDTO linkUserTOEstablishment(Authentication authentication, UserEstablishmentRequestDTO requestDTO) {
        UserAuthentication auth = (UserAuthentication) authentication.getPrincipal();
        UserEstablishment ue = this.getUserEstablishmentByUserEmailAndEstablishmentId(auth.getEmail(), requestDTO.establishmentId());
        if (ue.getFunctionRole() != FunctionRole.OWNER) {
            throw new RuntimeException("Only owners can add users to establishment");
        } else if (requestDTO.role() == FunctionRole.OWNER) {
            throw new RuntimeException("Only one owner allowed per establishment");
        }

        User user = userService.getUserEntityById(requestDTO.userId());
        Establishment establishment = establishmentService.getEstablishmentEntityById(requestDTO.establishmentId());

        UserEstablishmentId id = UserEstablishmentId.builder()
                .establishmentId(establishment.getId())
                .userId(user.getId())
                .build();

        UserEstablishment userEstablishment = UserEstablishment.builder()
                .id(id)
                .user(user)
                .establishment(establishment)
                .functionRole(requestDTO.role())
                .build();

        userEstablishment = repository.save(userEstablishment);

        return UserEstablishmentMapper.toDTO(userEstablishment);
    }

    public UserEstablishment getUserEstablishmentByUserEmailAndEstablishmentId(String userEmail, Long establishmentId) {
                return repository.findById_EstablishmentIdAndUser_UserAuthentication_Email(establishmentId, userEmail)
                .orElseThrow(() -> new RuntimeException("UserEstablishment not found for establishmentId=" + establishmentId + ", userEmail=" + userEmail));
    }

    public UserEstablishment getUserEstablishmentByUserIdAndEstablishmentId(Long userId, Long establishmentId) {
                return repository.findUserEstablishmentById_EstablishmentIdAndId_UserId(establishmentId, userId)
                        .orElseThrow(() -> new RuntimeException("UserEstablishment not found for establishmentId=" + establishmentId + ", userId=" + userId));
    }

    public void deleteUserAtEstablishment(Long establishmentId, Long userId, Authentication authentication) {
        System.out.println(establishmentId);
        System.out.println(userId);
        UserAuthentication auth = (UserAuthentication) authentication.getPrincipal();
        UserEstablishment ue = this.getUserEstablishmentByUserEmailAndEstablishmentId(auth.getEmail(), establishmentId);
        if (ue.getFunctionRole() != FunctionRole.OWNER) {
            throw new RuntimeException("Only owners can add users to establishment");
        }

        repository.deleteById_EstablishmentIdAndId_UserId(establishmentId, userId);
    }

    public List<UserEstablishmentResponseDTO> getUserEstablishmentsByUserId(Long id) {
        System.out.println(id);
        return repository.findAllById_UserId(id).stream().map(UserEstablishmentMapper::toDTO).toList();
    }

    public List<UserEstablishmentResponseDTO> getUserEstablishmentsByEstablishmentId(Long establishmentId) {
        System.out.println(establishmentId);
        return repository.findAllById_EstablishmentId(establishmentId).stream().map(UserEstablishmentMapper::toDTO).toList();
    }
}
