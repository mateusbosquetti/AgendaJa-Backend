package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.AppointmentMapper;
import com.mateusbosquetti.agendaja.model.dto.request.appointment.AppointmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.appointment.AppointmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.AppointmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Appointment;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.repository.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final ServiceService serviceService;
    private final UserService userService;
    private final UserEstablishmentService userEstablishmentService;


    public AppointmentResponseDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO) {

        ServiceEntity serviceEntity = serviceService.getServiceEntityById(appointmentRequestDTO.serviceId());
        User client = userService.getUserEntityById(appointmentRequestDTO.clientId());
        User professional = userService.getUserEntityById(appointmentRequestDTO.professionalId());

        userEstablishmentService.getUserEstablishmentByUserIdAndEstablishmentId(professional.getId(), serviceEntity.getEstablishment().getId());

        Appointment appointment = Appointment.builder()
                .client(client)
                .professional(professional)
                .serviceEntity(serviceEntity)
                .note(appointmentRequestDTO.note())
                .time(appointmentRequestDTO.time())
                .build();

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

    public AppointmentResponseDTO getAppointmentById(Long id) {
        Appointment appointment = this.getAppointmentEntityById(id);
        return AppointmentMapper.toDTO(appointment);
    }

    public Appointment getAppointmentEntityById(Long id) {
        System.out.println("Fetching appointment with ID: " + id);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public AppointmentResponseDTO updateAppointment(Long id, AppointmentPUTRequestDTO requestDTO) {
        Appointment appointment = this.getAppointmentEntityById(id);

        ServiceEntity serviceEntity = serviceService.getServiceEntityById(requestDTO.serviceId());
        User professional = userService.getUserEntityById(requestDTO.professionalId());

        userEstablishmentService.getUserEstablishmentByUserIdAndEstablishmentId(professional.getId(), serviceEntity.getEstablishment().getId());

        appointment.setProfessional(professional);
        appointment.setServiceEntity(serviceEntity);
        appointment.setTime(requestDTO.time());
        appointment.setNote(requestDTO.note());

        repository.save(appointment);
        return AppointmentMapper.toDTO(appointment);
    }

    public void disableAppointment(Long id) {
        repository.deleteById(id);
    }
}
