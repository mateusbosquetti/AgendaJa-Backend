package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.response.user.UserMeResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.User;

public class UserMapper {

    public static User toEntity(
//            UserRequestDTO requestDTO
    ) {
        return User.builder()
                .build();
    }

    public static UserResponseDTO toDTO(
            User user
    ) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getPhone(),
                user.getCpf(),
                user.getTheme(),
                user.getPhotoKey()
        );
    }

}
