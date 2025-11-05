package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/files")
@RestController
@AllArgsConstructor
public class FileController {

    private final FileService service;

    @PatchMapping("/user/{id}/photo")
    public ResponseEntity<String> updateUserPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String fileUrl = service.updateUserPhoto(id, file);
        return ResponseEntity.ok(fileUrl);
    }

    @PatchMapping("/establishment/{id}/logo")
    public ResponseEntity<String> updateEstablishmentLogo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String fileUrl = service.updateEstablishmentLogo(id, file);
        return ResponseEntity.ok(fileUrl);
    }

}
