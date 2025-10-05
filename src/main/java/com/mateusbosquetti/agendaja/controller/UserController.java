package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}/establishments")
    public List<EstablishmentResponseDTO> getEstablishmentsByUserId(
            @PathVariable Long id
    ) {
        return service.getEstablishmentsByUserId(id);
    }

}
