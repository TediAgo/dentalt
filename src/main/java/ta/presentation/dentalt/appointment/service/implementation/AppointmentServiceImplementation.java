package ta.presentation.dentalt.appointment.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.dto.NewDateDTO;
import ta.presentation.dentalt.appointment.model.entity.AppointmentEntity;
import ta.presentation.dentalt.appointment.model.enums.CompletionStatus;
import ta.presentation.dentalt.appointment.model.enums.PaymentStatus;
import ta.presentation.dentalt.appointment.repository.AppointmentRepository;
import ta.presentation.dentalt.appointment.service.mapper.AppointmentConverter;
import ta.presentation.dentalt.appointment.service.services.AppointmentService;
import ta.presentation.dentalt.appointment.service.util.AppointmentUtils;
import ta.presentation.dentalt.operation.repository.OperationRepository;
import ta.presentation.dentalt.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppointmentServiceImplementation implements AppointmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public AppointmentDTO getAppointment(Integer id) {
        if(appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            return AppointmentConverter.convertAppointmentEntityToDTO(appointmentRepository.findById(id).get());
        }
        LOGGER.info("Appointment does not exist.");
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
                        && appointmentEntity.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail)
                        || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyCompletedAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> (appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail) || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                    && (appointmentEntity.getCompletionStatus().equals(CompletionStatus.COMPLETED)) )
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyUncompletedAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> (appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail) || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                    && (appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED)) )
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyPaidAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> (appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail) || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                        && (appointmentEntity.getPaymentStatus().equals(PaymentStatus.PAID)) )
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyUnpaidAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> (appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail) || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                        && (appointmentEntity.getPaymentStatus().equals(PaymentStatus.UNPAID)) )
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyAppointmentsByDate(String loggedEmail, LocalDateTime date) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> (appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail) || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                        && (appointmentEntity.getStartDateTime().toLocalDate().equals(date.toLocalDate())) )
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyNextAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointmentEntity -> (appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail) || appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail))
                        && (appointmentEntity.getStartDateTime().isAfter(LocalDateTime.now())) )
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO applyForAppointmentByPatient(String loggedEmail, AppointmentDTO appointmentDTO) {

        List<AppointmentEntity> patientAppointments = appointmentRepository.findAll().stream()
                .filter(appointmentEntity -> appointmentEntity.getPatientEntity().getEmail().equals(loggedEmail)
                && appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .collect(Collectors.toList());
        if (patientAppointments.size() > 0) {
            LOGGER.info("Patient has Uncompleted Appointments.");
            return new AppointmentDTO();
        }

        if(userRepository.findByEmail(appointmentDTO.getDoctor().getEmail()).isEmpty() || operationRepository.findById(appointmentDTO.getOperation().getId()).isEmpty()) {
            LOGGER.info("Doctor or Operation does not exist.");
            return new AppointmentDTO();
        }
        List<AppointmentEntity> doctorAppointments = appointmentRepository.findAll().stream()
                .filter(appointmentEntity -> appointmentEntity.getDoctorEntity().getEmail().equals(appointmentDTO.getDoctor().getEmail())
                && appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .collect(Collectors.toList());

        if(!AppointmentUtils.isDateTimeFree(appointmentDTO.getStartDateTime(), appointmentDTO.getEndDateTime(), doctorAppointments)) {
            LOGGER.info("Wrong Dates or Doctor may have another scheduled Appointment.");
            return new AppointmentDTO();
        }

        appointmentDTO.getPatient().setId(userRepository.findByEmail(loggedEmail).get().getId());
        AppointmentEntity appointmentEntity = createAppointmentEntity(appointmentDTO, loggedEmail);
        appointmentRepository.save(appointmentEntity);
        return AppointmentConverter.convertAppointmentEntityToDTO(appointmentEntity);
    }

    @Override
    public AppointmentDTO createAppointmentByDoctor(String loggedEmail, AppointmentDTO appointmentDTO) {
        if(userRepository.findByEmail(appointmentDTO.getPatient().getEmail()).isEmpty() || operationRepository.findById(appointmentDTO.getOperation().getId()).isEmpty()) {
            LOGGER.info("Patient or operation does not exist.");
            return new AppointmentDTO();
        }

        List<AppointmentEntity> doctorAppointments = appointmentRepository.findAll().stream()
                .filter(appointmentEntity -> appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail)
                        && appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .collect(Collectors.toList());
        if(!AppointmentUtils.isDateTimeFree(appointmentDTO.getStartDateTime(), appointmentDTO.getEndDateTime(), doctorAppointments)) {
            LOGGER.info("Doctor may have another scheduled appointment or wrong dates.");
            return new AppointmentDTO();
        }

        appointmentDTO.getDoctor().setId(userRepository.findByEmail(loggedEmail).get().getId());
        AppointmentEntity appointmentEntity = createAppointmentEntity(appointmentDTO, loggedEmail);
        appointmentRepository.save(appointmentEntity);
        return AppointmentConverter.convertAppointmentEntityToDTO(appointmentEntity);
    }

    public  AppointmentEntity createAppointmentEntity(AppointmentDTO appointmentDTO, String loggedEmail) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setStartDateTime(appointmentDTO.getStartDateTime());
        appointmentEntity.setEndDateTime(appointmentDTO.getEndDateTime());
        appointmentEntity.setCompletionStatus(CompletionStatus.UNCOMPLETED);
        appointmentEntity.setPaymentStatus(appointmentDTO.getPaymentStatus());
        appointmentEntity.setValidity(Boolean.TRUE);
        if(appointmentDTO.getPatient().getEmail() != null) {
            appointmentEntity.setPatientEntity(userRepository.findByEmail(appointmentDTO.getPatient().getEmail()).get());
        } else {
            appointmentEntity.setPatientEntity(userRepository.findByEmail(loggedEmail).get());
        }
        if(appointmentDTO.getDoctor().getEmail() != null) {
            appointmentEntity.setDoctorEntity(userRepository.findByEmail(appointmentDTO.getDoctor().getEmail()).get());
        } else {
            appointmentEntity.setDoctorEntity(userRepository.findByEmail(loggedEmail).get());
        }
        appointmentEntity.setOperationEntity(operationRepository.findById(appointmentDTO.getOperation().getId()).get());
        return appointmentEntity;
    }

    @Override
    public AppointmentDTO changeDate(String loggedEmail, Integer id, NewDateDTO newDate) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            List<AppointmentEntity> doctorAppointments = appointmentRepository.findAll().stream()
                    .filter(appointmentEntity -> appointmentEntity.getDoctorEntity().getEmail().equals(loggedEmail)
                            && appointmentEntity.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                    .collect(Collectors.toList());

            if(AppointmentUtils.isDateTimeFree(newDate.getNewStartDateTime(), newDate.getNewEndDateTime(), doctorAppointments)) {
                AppointmentEntity appointment = appointmentRepository.findById(id).get();
                appointment.setStartDateTime(newDate.getNewStartDateTime());
                appointment.setEndDateTime(newDate.getNewEndDateTime());
                appointmentRepository.save(appointment);
                return AppointmentConverter.convertAppointmentEntityToDTO(appointment);
            }
            LOGGER.info("Doctor may have another scheduled appointment or wrong dates.");
            return new AppointmentDTO();
        }
        LOGGER.info("Appointment does not exist.");
        return new AppointmentDTO();
    }

    @Override
    public AppointmentDTO changeCompletionStatus(Integer id) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointment = appointmentRepository.findById(id).get();
            appointment.setCompletionStatus(CompletionStatus.COMPLETED);
            appointmentRepository.save(appointment);
            return AppointmentConverter.convertAppointmentEntityToDTO(appointment);
        }
        LOGGER.info("Appointment does not exist.");
        return new AppointmentDTO();
    }

    @Override
    public AppointmentDTO changePaymentStatus(Integer id) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointment = appointmentRepository.findById(id).get();
            appointment.setPaymentStatus(PaymentStatus.PAID);
            appointmentRepository.save(appointment);
            return AppointmentConverter.convertAppointmentEntityToDTO(appointment);
        }
        LOGGER.info("Appointment does not exist.");
        return new AppointmentDTO();
    }

    @Override
    public Integer deleteAppointment(Integer id) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointmentEntityToDelete = appointmentRepository.findById(id).get();
            appointmentEntityToDelete.setValidity(Boolean.FALSE);
            appointmentRepository.save(appointmentEntityToDelete);
            return id;
        }
        LOGGER.info("Appointment does not exist.");
        return null;
    }
}
