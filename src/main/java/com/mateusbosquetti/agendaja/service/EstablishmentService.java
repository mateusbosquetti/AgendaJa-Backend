package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.EstablishmentMapper;
import com.mateusbosquetti.agendaja.mapper.UserMapper;
import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import com.mateusbosquetti.agendaja.repository.EstablishmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new RuntimeException("Establishment not found"));
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

    public Page<EstablishmentResponseDTO> getEstablishments(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(EstablishmentMapper::toDTO);
    }

    public void disableEstablishment(Long id) {
        repository.deleteById(id);
    }

    //TODO: N+1 Problem
    public List<UserResponseDTO> getEmployeesByEstablishment(Long establishmentId) {
        List<UserEstablishment> userEstablishments = userEstablishmentService.getEstablishmentUsersByEstablishmentIdAndFunction(establishmentId, FunctionRole.EMPLOYEE);
        return userEstablishments.stream().map(ue -> UserMapper.toDTO(ue.getUser())).toList();
    }

}
