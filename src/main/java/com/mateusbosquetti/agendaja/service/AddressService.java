package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.AddressMapper;
import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;
import com.mateusbosquetti.agendaja.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public ResponseEntity<Address> createAddress(AddressRequestDTO requestDTO) {
        Address savedAddress = AddressMapper.toEntity(requestDTO);
        savedAddress = repository.save(savedAddress);
        return ResponseEntity.ok(savedAddress);
    }

}
