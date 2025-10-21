package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;
import com.mateusbosquetti.agendaja.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<Address> createAddress(
            @RequestBody AddressRequestDTO requestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAddress(requestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<Address>> getAddressess(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String city
            //TODO: Adicionar mais filtros
    ) {
        return ResponseEntity.ok(service.getAddressess(
                page, size, city
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getAddressById(
                id
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long id,
            @RequestBody AddressRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(service.updateAddress(id, requestDTO));
    }

}
