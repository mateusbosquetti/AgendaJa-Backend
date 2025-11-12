package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentSummaryDTO;
import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.userEstablishment.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;
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

        return this.toResposeDTO(establishment);
    }

    public Establishment getEstablishmentEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Establishment not found: id=" + id));
    }

    public void createEstablishment(EstablishmentRequestDTO requestDTO) {
        User user = userService.getUserEntityById(requestDTO.ownerId());

        Address address = addressService.createAddress(requestDTO.address());

        Establishment establishment = Establishment.builder()
                .name(requestDTO.name())
                .cnpj(requestDTO.cnpj())
                .address(address)
                .build();

        establishment = repository.save(establishment);

        UserEstablishment userEstablishment = UserEstablishment.builder()
                .id(new UserEstablishmentId(establishment.getId(), user.getId()))
                .establishment(establishment)
                .user(user)
                .functionRole(FunctionRole.OWNER)
                .build();

        userEstablishmentRepository.save(userEstablishment);
    }

    public Page<EstablishmentSummaryDTO> getEstablishments(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Establishment> spec = EstablishmentSpecification.nameContains(name);

        return repository.findAll(spec, pageable)
                .map(this::toSummaryDTO);
    }

    public void disableEstablishment(Long id) {
        repository.deleteById(id);
    }

    public void updateEstablishment(Long id, EstablishmentPUTRequestDTO requestDTO) {
        Establishment establishment = getEstablishmentEntityById(id);
        establishment.setName(requestDTO.name());
        establishment.setCnpj(requestDTO.cnpj());

        addressService.updateAddress(establishment.getAddress().getId(), requestDTO.address());

        repository.save(establishment);
    }

    private EstablishmentSummaryDTO toSummaryDTO(Establishment establishment) {
        return new EstablishmentSummaryDTO(
                establishment.getId(),
                establishment.getName(),
                establishment.getCnpj(),
                establishment.getAddress(),
                establishment.getLogo().getKey()
        );
    }

    private EstablishmentResponseDTO toResposeDTO (Establishment establishment) {
        return new EstablishmentResponseDTO(
                establishment.getId(),
                establishment.getName(),
                establishment.getCnpj(),
                establishment.getAddress(),
                establishment.getLogo().getKey(),
                establishment.getServiceEntities().stream()
                        .map(service -> new ServiceResponseDTO(
                                service.getId(),
                                service.getName(),
                                service.getDescription(),
                                service.getDurationMinutes(),
                                service.getPrice(),
                                establishment.getId()
                        ))
                        .toList(),
                establishment.getUsersRelated().stream()
                        .map(userEstablishment -> new UserEstablishmentResponseDTO(
                                establishment.getId(),
                                userEstablishment.getUser().getId(),
                                userEstablishment.getFunctionRole()
                        ))
                        .toList()
        );
    }

}