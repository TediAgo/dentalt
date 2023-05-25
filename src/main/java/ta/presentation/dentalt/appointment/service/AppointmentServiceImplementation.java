package ta.presentation.dentalt.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.AppointmentRepository;
import ta.presentation.dentalt.appointment.model.*;

import java.time.LocalDateTime;
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

    @Override
    public List<AppointmentDTO> getPatientUncompletedAppointments(Integer patientId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getPatientEntity().getId().equals(patientId)
                        && appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getPatientUnpaidAppointments(Integer patientId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getPatientEntity().getId().equals(patientId)
                        && appointmentEntity.getCompletionStatus().equals(PaymentStatus.UNPAID))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyAppointments() {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllMyCompletedAppointments() {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllMyUncompletedAppointments() {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllMyPaidAppointments() {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllMyUnpaidAppointments() {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllMyAppointmentsByDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<AppointmentDTO> getAllMyNextAppointments() {
        return null;
    }

    @Override
    public AppointmentDTO changeDate(Integer id, NewDateDTO newDate) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointment = appointmentRepository.findById(id).get();
            appointment.setStartDateTime(newDate.getNewStartDateTime());
            appointment.setEndDateTime(newDate.getNewEndDateTime());
            appointmentRepository.save(appointment);
            return AppointmentConverter.convertAppointmentEntityToDTO(appointment);
        }
        return new AppointmentDTO();
    }

    @Override
    public AppointmentDTO applyForAppointmentByPatient(AppointmentDTO appointmentDTO) {
        return null;
    }

    @Override
    public AppointmentDTO createAppointmentByDoctor(AppointmentDTO appointmentDTO) {
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
