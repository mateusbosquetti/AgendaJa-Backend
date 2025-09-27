package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

}
