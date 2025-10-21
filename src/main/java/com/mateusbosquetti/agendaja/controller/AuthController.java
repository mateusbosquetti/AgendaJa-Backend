package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.LoginRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.RegisterRequestDTO;
import com.mateusbosquetti.agendaja.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(authService.login(requestDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequestDTO requestDTO
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(requestDTO));
    }
}
