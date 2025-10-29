package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.model.dto.request.LoginRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.RegisterRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.TokenResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserAuthentication;
import com.mateusbosquetti.agendaja.model.enums.ThemeEnum;
import com.mateusbosquetti.agendaja.model.enums.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserAuthenticationService authenticationService;
    private final TokenService tokenService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public TokenResponseDTO login (LoginRequestDTO requestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDTO.email(), requestDTO.password());
        Authentication auth = authenticationManager.authenticate(authenticationToken);
        String token = tokenService.generateToken((UserAuthentication) auth.getPrincipal());
        return new TokenResponseDTO(token);
    }

    public TokenResponseDTO register(RegisterRequestDTO request) {
        if (authenticationService.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        UserAuthentication auth = UserAuthentication.builder()
                .email(request.email())
                .password(encodedPassword)
                .role(UserRole.USER)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        User user = User.builder()
                .name(request.name())
                .cpf(request.cpf())
                .phone(request.phone())
                .userAuthentication(auth)
                .theme(ThemeEnum.DARK)
                .build();

        auth.setUser(user);

        userService.createUser(user);
        String token = tokenService.generateToken(auth);
        return new TokenResponseDTO(token);

    }

}
