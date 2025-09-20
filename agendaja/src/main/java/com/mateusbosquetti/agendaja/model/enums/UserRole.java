package com.mateusbosquetti.agendaja.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;
}
