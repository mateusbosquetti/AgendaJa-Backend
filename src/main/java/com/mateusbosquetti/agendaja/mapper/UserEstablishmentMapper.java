package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.UserEstablishment;

public class UserEstablishmentMapper {

//    public static UserEstablishment toEntity(
//    ) {
//        return
//    }

    public static UserEstablishmentResponseDTO toDTO(
            UserEstablishment establishment
    ) {
        return new UserEstablishmentResponseDTO(
                establishment.getId().getEstablishmentId(),
                establishment.getId().getUserId(),
                establishment.getFunctionRole()
        );
    }

}
