package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.repository.UserAuthenticationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    private final UserAuthenticationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }

    public boolean existsByEmail(String email) {
        return repository.findByEmail(email).orElse(null) != null;
    }
}
