package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.UserMapper;
import com.mateusbosquetti.agendaja.model.dto.request.RegisterRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.UserThemePATCHRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserMeResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import com.mateusbosquetti.agendaja.repository.UserRepository;
import com.mateusbosquetti.agendaja.specification.EstablishmentSpecification;
import com.mateusbosquetti.agendaja.specification.UserSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserAuthenticationService authenticationService;
    private final MinioService minioService;

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
        return this.toMeDTO(user);
    }

    public UserResponseDTO updateUser(Long id, RegisterRequestDTO requestDTO) {
        User user = this.getUserEntityById(id);
        user.setName(requestDTO.name());
        user.setPhone(requestDTO.phone());
        user.setCpf(requestDTO.cpf());
        user = repository.save(user);
        return UserMapper.toDTO(user);
    }

    public UserResponseDTO updateUserTheme(Long id, UserThemePATCHRequestDTO dto) {
        User user = this.getUserEntityById(id);
        user.setTheme(dto.theme());
        user = repository.save(user);

        return UserMapper.toDTO(user);
    }

    public Page<UserMeResponseDTO> getUsers(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<User> spec = UserSpecification.nameContains(name);

        return repository.findAll(spec, pageable)
                .map(this::toMeDTO);
    }

    private UserMeResponseDTO toMeDTO(User user) {
        return new UserMeResponseDTO(
                user.getId(),
                user.getName(),
                user.getUserAuthentication().getEmail(),
                user.getUserAuthentication().getRole(),
                user.getTheme(),
                minioService.getFileUrl(user.getProfilePicture().getKey())
        );
    }

}
