package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAppointmentsByClient_Id(Long clientId);

    List<Appointment> findAppointmentsByProfessional_Id(Long professionalId);

    List<Appointment> findAppointmentsByService_IdIn(List<Long> serviceIds);
}
