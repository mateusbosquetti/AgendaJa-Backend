package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.EstablishmentMapper;
import com.mateusbosquetti.agendaja.mapper.UserMapper;
import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.UserEstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import com.mateusbosquetti.agendaja.repository.EstablishmentRepository;
import com.mateusbosquetti.agendaja.specification.EstablishmentSpecification;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository repository;
    private final UserEstablishmentService userEstablishmentService;

    public EstablishmentResponseDTO getEstablishmentById(Long id) {
        Establishment establishment = this.getEstablishmentEntityById(id);
        return EstablishmentMapper.toDTO(establishment);
    }

    private Establishment getEstablishmentEntityById(Long id) {
        return repository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Establishment not found: id=" + id));
    }

    public EstablishmentResponseDTO createEstablishment(EstablishmentRequestDTO requestDTO) {
        Establishment establishment = EstablishmentMapper.toEntity(requestDTO);
        establishment = repository.save(establishment);
        userEstablishmentService.createUserEstablishment(
                User.builder().id(requestDTO.ownerId()).build(),
                establishment,
                FunctionRole.OWNER
        );
        return EstablishmentMapper.toDTO(establishment);
    }

    public Page<EstablishmentResponseDTO> getEstablishments(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Establishment> spec = EstablishmentSpecification.nameContains(name);

        Page<Establishment> establishments = repository.findAll(spec, pageable);
        return establishments.map(EstablishmentMapper::toDTO);
    }

    public void disableEstablishment(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void disableUserAtEstablishment(Long id, Long userId, Authentication authentication) {
        String email = authentication.getName();
        UserEstablishment userEstablishment = userEstablishmentService.getUserEstablishmentByUserEmailAndEstablishmentId(email, id);
        if (userEstablishment.getId().getFunctionRole() != FunctionRole.OWNER) {
            throw new RuntimeException("Only owners can disable users at establishment");
        }

        userEstablishmentService.disableUserEstablishment(id, userId);
    }

    //TODO: N+1 Problem
    public List<UserResponseDTO> getEmployeesByEstablishment(Long establishmentId) {
        List<UserEstablishment> userEstablishments = userEstablishmentService.getEstablishmentUsersByEstablishmentIdAndFunction(establishmentId, FunctionRole.EMPLOYEE);
        return userEstablishments.stream().map(ue -> UserMapper.toDTO(ue.getUser())).toList();
    }

    public UserEstablishmentResponseDTO addUserToEstablishment(UserEstablishmentRequestDTO requestDTO) {
        return userEstablishmentService.createUserEstablishment(
                User.builder().id(requestDTO.userId()).build(),
                Establishment.builder().id(requestDTO.establishmentId()).build(),
                requestDTO.role()
        );
    }
}
