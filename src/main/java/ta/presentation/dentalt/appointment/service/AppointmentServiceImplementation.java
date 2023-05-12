package ta.presentation.dentalt.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.AppointmentRepository;
import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.AppointmentEntity;
import ta.presentation.dentalt.appointment.model.CompletionStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentDTO getAppointment(Integer id) {
        if(appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            return AppointmentConverter.convertAppointmentEntityToDTO(appointmentRepository.findById(id).get());
        }
        return new AppointmentDTO();
    }

    /*@Override
    public List<AppointmentDTO> getAllMyAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getValidity().equals(Boolean.TRUE))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllUncompletedUserAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getValidity().equals(Boolean.TRUE)
                        && appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllCompletedUserAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getValidity().equals(Boolean.TRUE)
                        && appointmentEntity.getCompletionStatus().equals(CompletionStatus.COMPLETED))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<AppointmentDTO> getAllDeletedAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getValidity().equals(Boolean.FALSE))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO applyForAppointmentByPatient() {
        return null;
    }

    @Override
    public AppointmentDTO createAppointmentByDoctor() {
        return null;
    }

    @Override
    public Integer deleteAppointment(Integer id) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointmentEntityToDelete = appointmentRepository.findById(id).get();
            appointmentEntityToDelete.setValidity(Boolean.FALSE);
            appointmentRepository.save(appointmentEntityToDelete);
            return id;
        }
        return null;
    }
}
