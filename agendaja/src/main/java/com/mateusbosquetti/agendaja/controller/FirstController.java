package com.mateusbosquetti.agendaja.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class FirstController {

    @GetMapping("/hello")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("Hello world", HttpStatus.OK);
    }

}
