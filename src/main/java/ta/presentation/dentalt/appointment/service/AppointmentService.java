package ta.presentation.dentalt.appointment.service;

import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.NewDateDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {


    AppointmentDTO getAppointment(Integer id);

    List<AppointmentDTO> getPatientUncompletedAppointments(Integer patientId);

    List<AppointmentDTO> getPatientUnpaidAppointments(Integer patientId);

    List<AppointmentDTO> getAllMyAppointments();

    List<AppointmentDTO> getAllMyCompletedAppointments();

    List<AppointmentDTO> getAllMyUncompletedAppointments();

    List<AppointmentDTO> getAllMyPaidAppointments();

    List<AppointmentDTO> getAllMyUnpaidAppointments();

    List<AppointmentDTO> getAllMyAppointmentsByDate(LocalDateTime date);

    List<AppointmentDTO> getAllMyNextAppointments();

    AppointmentDTO applyForAppointmentByPatient(AppointmentDTO appointmentDTO);

    AppointmentDTO createAppointmentByDoctor(AppointmentDTO appointmentDTO);

    AppointmentDTO changeDate(Integer id, NewDateDTO newDate);

    Integer deleteAppointment(Integer id);
}
