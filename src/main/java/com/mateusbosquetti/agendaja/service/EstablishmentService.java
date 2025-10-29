package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.EstablishmentMapper;
import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentAllResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import com.mateusbosquetti.agendaja.repository.EstablishmentRepository;
import com.mateusbosquetti.agendaja.repository.UserEstablishmentRepository;
import com.mateusbosquetti.agendaja.specification.EstablishmentSpecification;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository repository;
    private final UserService userService;
    private final AddressService addressService;
    private final UserEstablishmentRepository userEstablishmentRepository;

    public EstablishmentResponseDTO getEstablishmentById(Long id) {
        Establishment establishment = this.getEstablishmentEntityById(id);
        return EstablishmentMapper.toDTO(establishment);
    }

    public Establishment getEstablishmentEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Establishment not found: id=" + id));
    }

    //TODO: Adaptar
    public EstablishmentResponseDTO createEstablishment(EstablishmentRequestDTO requestDTO) {
        User user = userService.getUserEntityById(requestDTO.ownerId());

        Establishment establishment = EstablishmentMapper.toEntity(requestDTO);
        establishment = repository.save(establishment);

        UserEstablishment ue = UserEstablishment.builder()
                .id(new UserEstablishmentId(establishment.getId(), user.getId()))
                .establishment(establishment)
                .user(user)
                .functionRole(FunctionRole.OWNER)
                .build();

        userEstablishmentRepository.save(ue);

        return EstablishmentMapper.toDTO(repository.findById(establishment.getId()).orElse(establishment));
    }

    public Page<EstablishmentAllResponseDTO> getEstablishments(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Establishment> spec = EstablishmentSpecification.nameContains(name);

        Page<Establishment> establishments = repository.findAll(spec, pageable);
        return establishments.map(EstablishmentMapper::toAllDTO);
    }

    public void disableEstablishment(Long id) {
        repository.deleteById(id);
    }

    public EstablishmentResponseDTO updateEstablishment(Long id, EstablishmentPUTRequestDTO requestDTO) {
        Establishment establishment = getEstablishmentEntityById(id);
        establishment.setName(requestDTO.name());
        establishment.setCnpj(requestDTO.cnpj());

        addressService.updateAddress(establishment.getAddress().getId(), requestDTO.address());

        establishment = repository.save(establishment);
        return EstablishmentMapper.toDTO(establishment);
    }
}
