package com.mateusbosquetti.agendaja.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FunctionRole {
    OWNER("owner"),
    EMPLOYEE("employee"),;

    private final String role;
}
