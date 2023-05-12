package ta.presentation.dentalt.appointment.service;

import ta.presentation.dentalt.appointment.model.AppointmentDTO;

import java.util.List;

public interface AppointmentService {


    AppointmentDTO getAppointment(Integer id);

    /*List<AppointmentDTO> getAllMyAppointments();

    List<AppointmentDTO> getAllUncompletedUserAppointments();

    List<AppointmentDTO> getAllCompletedUserAppointments();*/

    List<AppointmentDTO> getAllDeletedAppointments();

    List<AppointmentDTO> getAllAppointments();

    AppointmentDTO applyForAppointmentByPatient();

    AppointmentDTO createAppointmentByDoctor();

    Integer deleteAppointment(Integer id);
}
