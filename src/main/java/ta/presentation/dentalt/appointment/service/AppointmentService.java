package ta.presentation.dentalt.appointment.service;

import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.NewDateDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {


    AppointmentDTO getAppointment(Integer id);

    List<AppointmentDTO> getPatientUncompletedAppointments(Integer patientId);

    List<AppointmentDTO> getPatientUnpaidAppointments(Integer patientId);

    List<AppointmentDTO> getAllMyAppointments(String loggedEmail);

    List<AppointmentDTO> getAllMyCompletedAppointments(String loggedEmail);

    List<AppointmentDTO> getAllMyUncompletedAppointments(String loggedEmail);

    List<AppointmentDTO> getAllMyPaidAppointments(String loggedEmail);

    List<AppointmentDTO> getAllMyUnpaidAppointments(String loggedEmail);

    List<AppointmentDTO> getAllMyAppointmentsByDate(String loggedEmail, LocalDateTime date);

    List<AppointmentDTO> getAllMyNextAppointments(String loggedEmail);

    AppointmentDTO applyForAppointmentByPatient(AppointmentDTO appointmentDTO);

    AppointmentDTO createAppointmentByDoctor(AppointmentDTO appointmentDTO);

    AppointmentDTO changeDate(Integer id, NewDateDTO newDate);

    Integer deleteAppointment(Integer id);
}
