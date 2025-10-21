package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.AddressMapper;
import com.mateusbosquetti.agendaja.mapper.EstablishmentMapper;
import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.repository.AddressRepository;
import com.mateusbosquetti.agendaja.specification.AddressSpecification;
import com.mateusbosquetti.agendaja.specification.EstablishmentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public Address createAddress(AddressRequestDTO requestDTO) {
        Address savedAddress = AddressMapper.toEntity(requestDTO);
        return repository.save(savedAddress);
    }

    public Page<Address> getAddressess(int page, int size, String city) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Address> spec = AddressSpecification.cityContains(city);

        return repository.findAll(spec, pageable);
    }

    public Address updateAddress(Long id, AddressRequestDTO requestDTO) {
        Address address = this.getAddressById(id);
        address.setNumber(requestDTO.number());
        address.setStreet(requestDTO.street());
        address.setCity(requestDTO.city());
        address.setStateProvince(requestDTO.stateProvince());
        address.setCountryCode(requestDTO.countryCode());
        address.setPostalCode(requestDTO.postalCode());
        address.setLatitude(requestDTO.latitude());
        address.setLongitude(requestDTO.longitude());

        return repository.save(address);
    }

    public Address getAddressById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found: id=" + id));
    }

}
