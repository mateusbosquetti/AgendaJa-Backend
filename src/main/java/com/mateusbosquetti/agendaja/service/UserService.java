package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserEstablishmentService userEstablishmentService;

    public List<EstablishmentResponseDTO> getEstablishmentsByUserId(Long id) {
//        return userEstablishmentService.
        return List.of(); // TODO: Implement this method
    }

    public User createUser(User user) {
        return repository.save(user);
    }
}
