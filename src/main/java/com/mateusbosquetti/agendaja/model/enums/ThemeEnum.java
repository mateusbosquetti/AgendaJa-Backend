package com.mateusbosquetti.agendaja.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ThemeEnum {
    LIGHT("LIGHT"),
    DARK("DARK");

    private final String theme;
}
