package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.RegisterRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.UserThemePATCHRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserMeResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.enums.ThemeEnum;
import com.mateusbosquetti.agendaja.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserMeResponseDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(service.getMe(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody RegisterRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(service.updateUser(id, requestDTO));
    }

    @PatchMapping("/{id}/theme")
    public ResponseEntity<UserResponseDTO> updateUserTheme(
            @PathVariable Long id,
            @RequestBody UserThemePATCHRequestDTO dto
    ) {
        return ResponseEntity.ok(service.updateUserTheme(id, dto));
    }

    @PatchMapping("/{id}/photo")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String fileUrl = service.updateUserPhoto(id, file);
        return ResponseEntity.ok(fileUrl);
    }

//    @GetMapping("/{fileName}")
//    public ResponseEntity<String> getFileUrl(@PathVariable String fileName) {
//        String url = minioService.getFileUrl(fileName);
//        return ResponseEntity.ok(url);
//    }

}
