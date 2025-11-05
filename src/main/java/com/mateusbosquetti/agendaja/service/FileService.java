package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.File;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileService {

    private final FileRepository repository;
    private final UserService userService;
    private final EstablishmentService establishmentService;
    private final MinioService minioService;

    public File saveFile(File file) {
        return repository.save(file);
    }

    public String updateUserPhoto(Long id, MultipartFile file) {
        User user = this.userService.getUserEntityById(id);

        String key = minioService.uploadFile(file);

        File profilePicture = File.builder()
                .key(key)
                .name(file.getName())
                .user(user)
                .build();

        user.setProfilePicture(profilePicture);
        this.saveFile(profilePicture);

        return minioService.getFileUrl(key);
    }

    public String updateEstablishmentLogo(Long id, MultipartFile file) {
        Establishment establishment = this.establishmentService.getEstablishmentEntityById(id);

        String key = minioService.uploadFile(file);

        File logo = File.builder()
                .key(key)
                .name(file.getName())
                .establishment(establishment)
                .build();

        establishment.setLogo(logo);
        this.saveFile(logo);

        return minioService.getFileUrl(key);
    }
}
