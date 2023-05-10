package ta.presentation.dentalt.appointment.service;

import ta.presentation.dentalt.appointment.model.AppointmentDTO;

import java.util.List;

public interface AppointmentService {


    AppointmentDTO getAppointment(Integer id);

    List<AppointmentDTO> getUserAppointment(Integer userId);

    AppointmentDTO createAppointmentForPatient();

    AppointmentDTO createAppointmentForDoctor();

    Integer deleteAppointment(Integer id);

    AppointmentDTO restoreAppointment(Integer id);
}
