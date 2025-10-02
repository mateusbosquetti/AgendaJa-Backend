package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.AppointmentMapper;
import com.mateusbosquetti.agendaja.model.dto.request.AppointmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.AppointmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Appointment;
import com.mateusbosquetti.agendaja.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final ServiceService serviceService;

    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        Appointment appointment = AppointmentMapper.toEntity(appointmentRequestDTO);
        appointment = repository.save(appointment);
        return AppointmentMapper.toDTO(appointment);
    }

    public List<AppointmentResponseDTO> getAppointmentsByClient(Long clientId) {
        return repository.findAppointmentsByClient_Id(clientId)
                .stream().map(
                        AppointmentMapper::toDTO
                ).toList();
    }

    public List<AppointmentResponseDTO> getAppointmentsByProfessional(Long professionalId) {
        return repository.findAppointmentsByProfessional_Id(professionalId)
                .stream().map(
                        AppointmentMapper::toDTO
                ).toList();
    }

    public List<AppointmentResponseDTO> getAppointmentsByEstablishment(Long establishmentId) {
        List<ServiceResponseDTO> services = serviceService.getServicesByEstablishment(establishmentId);

        return repository.findAppointmentsByServiceEntity_IdIn(services.stream().map(ServiceResponseDTO::id).toList())
                .stream().map(
                        AppointmentMapper::toDTO
                ).toList();
    }

}
