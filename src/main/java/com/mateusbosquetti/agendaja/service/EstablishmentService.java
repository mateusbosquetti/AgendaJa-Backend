package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.model.compositekey.UserEstablishmentId;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentSummaryDTO;
import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.userEstablishment.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.*;
import com.mateusbosquetti.agendaja.model.enums.FunctionRole;
import com.mateusbosquetti.agendaja.model.enums.UserRole;
import com.mateusbosquetti.agendaja.repository.EstablishmentRepository;
import com.mateusbosquetti.agendaja.repository.UserEstablishmentRepository;
import com.mateusbosquetti.agendaja.specification.EstablishmentSpecification;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository repository;
    private final UserService userService;
    private final UserEstablishmentRepository userEstablishmentRepository;

    public void createEstablishment(EstablishmentRequestDTO requestDTO) {
        User user = userService.getUserEntityById(requestDTO.ownerId());

        Establishment establishment = Establishment.builder()
                .name(requestDTO.name())
                .cnpj(requestDTO.cnpj())
                .address(
                        Address.builder()
                                .number(requestDTO.address().number())
                                .street(requestDTO.address().street())
                                .city(requestDTO.address().city())
                                .stateProvince(requestDTO.address().stateProvince())
                                .countryCode(requestDTO.address().countryCode())
                                .postalCode(requestDTO.address().postalCode())
                                .latitude(requestDTO.address().latitude())
                                .longitude(requestDTO.address().longitude())
                                .build()
                )
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

    public EstablishmentResponseDTO getEstablishmentById(Long id) {
        Establishment establishment = this.getEstablishmentEntityById(id);

        return this.toResposeDTO(establishment);
    }

    public Establishment getEstablishmentEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Establishment not found: id=" + id));
    }

    public Page<EstablishmentSummaryDTO> getEstablishments(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Establishment> spec = EstablishmentSpecification.nameContains(name);

        return repository.findAll(spec, pageable)
                .map(this::toSummaryDTO);
    }

    public void updateEstablishment(Long id, EstablishmentPUTRequestDTO requestDTO, Authentication authentication) {
        Establishment establishment = this.getEstablishmentEntityById(id);

        if (!this.checkUserPermission(establishment, authentication)) {
            throw new RuntimeException("Access denied: You do not have permission to delete this establishment.");
        }

        establishment.setName(requestDTO.name());
        establishment.setCnpj(requestDTO.cnpj());

        Address address = establishment.getAddress();
        address.setNumber(requestDTO.address().number());
        address.setStreet(requestDTO.address().street());
        address.setCity(requestDTO.address().city());
        address.setStateProvince(requestDTO.address().stateProvince());
        address.setCountryCode(requestDTO.address().countryCode());
        address.setPostalCode(requestDTO.address().postalCode());
        address.setLatitude(requestDTO.address().latitude());
        address.setLongitude(requestDTO.address().longitude());

        establishment.setAddress(address);

        repository.save(establishment);
    }

    public void disableEstablishment(Long id, Authentication authentication) {
        Establishment establishment = this.getEstablishmentEntityById(id);
        if (!this.checkUserPermission(establishment, authentication)) {
            throw new RuntimeException("Access denied: You do not have permission to delete this establishment.");
        }
        repository.deleteById(id);
    }

    private boolean checkUserPermission(Establishment establishment, Authentication authentication) {
        UserAuthentication userAuthentication = (UserAuthentication) authentication.getPrincipal();
        User user = userAuthentication.getUser();

        boolean isAdmin = userAuthentication.getRole().equals(UserRole.ADMIN);
        boolean isOwner = establishment.getUsersRelated()
                .stream()
                .anyMatch(ue -> ue.getFunctionRole() == FunctionRole.OWNER
                        && ue.getUser().equals(user));

        if (!isOwner && !isAdmin) {
            return false;
        }
        return true;
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