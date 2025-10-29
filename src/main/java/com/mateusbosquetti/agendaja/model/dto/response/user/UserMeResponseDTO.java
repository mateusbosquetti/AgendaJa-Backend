package com.mateusbosquetti.agendaja.model.dto.response.user;

import com.mateusbosquetti.agendaja.model.enums.ThemeEnum;
import com.mateusbosquetti.agendaja.model.enums.UserRole;

public record UserMeResponseDTO(Long id, String name, String email, UserRole role, ThemeEnum theme) {
}
