package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.appointment.AppointmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.AppointmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Appointment;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import com.mateusbosquetti.agendaja.model.entity.User;

public class AppointmentMapper {

    public static Appointment toEntity(
            AppointmentRequestDTO requestDTO
    ) {
        return Appointment.builder()
                .client(User.builder().id(requestDTO.clientId()).build())
                .professional(User.builder().id(requestDTO.professionalId()).build())
                .serviceEntity(ServiceEntity.builder().id(requestDTO.serviceId()).build())
                .note(requestDTO.note())
                .time(requestDTO.time())
                .build();
    }

    public static AppointmentResponseDTO toDTO(
            Appointment appointment
    ) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                UserMapper.toDTO(appointment.getClient()),
                UserMapper.toDTO(appointment.getProfessional()),
                ServiceMapper.toDTO(appointment.getServiceEntity()),
                appointment.getTime(),
                appointment.getNote()
        );
    }

}
