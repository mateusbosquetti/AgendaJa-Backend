package com.mateusbosquetti.agendaja.model.dto.request;

import com.mateusbosquetti.agendaja.model.enums.ThemeEnum;

public record UserThemePATCHRequestDTO(
        ThemeEnum theme
) {
}
