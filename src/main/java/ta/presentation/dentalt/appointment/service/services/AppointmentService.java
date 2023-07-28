package ta.presentation.dentalt.appointment.service.services;

import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.dto.AppointmentNewDateDTO;

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

    AppointmentDTO applyForAppointmentByPatient(String loggedEmail, AppointmentDTO appointmentDTO);

    AppointmentDTO createAppointmentByDoctor(String loggedEmail, AppointmentDTO appointmentDTO);

    AppointmentDTO changeDate(String loggedEmail, Integer id, AppointmentNewDateDTO newDate);

    AppointmentDTO changeCompletionStatus(Integer id);

    AppointmentDTO changePaymentStatus(Integer id);

    Integer deleteAppointment(Integer id);
}
