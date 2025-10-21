package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.UserMapper;
import com.mateusbosquetti.agendaja.model.dto.request.RegisterRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserMeResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import com.mateusbosquetti.agendaja.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserAuthenticationService authenticationService;

    public User createUser(User user) {
        return repository.save(user);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = this.getUserEntityById(id);
        return UserMapper.toDTO(user);
    }

    public User getUserEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserMeResponseDTO getMe(String email) {
        UserAuthentication auth = (UserAuthentication) authenticationService.loadUserByUsername(email);
        User user = auth.getUser();
        return new UserMeResponseDTO(user.getId(), user.getName(), auth.getEmail(), auth.getRole());
    }

    public UserResponseDTO updateUser(Long id, RegisterRequestDTO requestDTO) {
        User user = this.getUserEntityById(id);
        user.setName(requestDTO.name());
        user.setPhone(requestDTO.phone());
        user.setCpf(requestDTO.cpf());
        user = repository.save(user);
        return UserMapper.toDTO(user);
    }
}
