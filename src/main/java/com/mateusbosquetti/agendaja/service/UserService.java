package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.UserEstablishmentMapper;
import com.mateusbosquetti.agendaja.mapper.UserMapper;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserAuthenticationService authenticationService;
    private final UserEstablishmentService userEstablishmentService;

    public List<UserEstablishmentResponseDTO> getEstablishmentsByUserId(Long id) {
        return userEstablishmentService.getUserEstablishmentsByUserId(id).stream().map(UserEstablishmentMapper::toDTO).toList();
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.toDTO(user);
    }

    public UserResponseDTO getByEmail(String email) {
        UserAuthentication auth = (UserAuthentication) authenticationService.loadUserByUsername(email);
        return UserMapper.toDTO(auth.getUser());
    }
}
