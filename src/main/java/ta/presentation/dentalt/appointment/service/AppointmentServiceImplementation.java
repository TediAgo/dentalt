package ta.presentation.dentalt.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.operation.OperationRepository;

import java.util.List;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public AppointmentDTO getAppointment(Integer id) {
        return null;
    }

    @Override
    public List<AppointmentDTO> getUserAppointment(Integer userId) {
        return null;
    }

    @Override
    public AppointmentDTO createAppointmentForPatient() {
        return null;
    }

    @Override
    public AppointmentDTO createAppointmentForDoctor() {
        return null;
    }

    @Override
    public Integer deleteAppointment(Integer id) {
        return null;
    }

    @Override
    public AppointmentDTO restoreAppointment(Integer id) {
        return null;
    }
}
