package ta.presentation.dentalt.appointment.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.dto.AppointmentNewDateDTO;
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
                .filter(appointment -> appointment.getPatientEntity().getId().equals(patientId)
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getPatientUnpaidAppointments(Integer patientId) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.getPatientEntity().getId().equals(patientId)
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail))
                        && appointment.getValidity().equals(Boolean.TRUE))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyCompletedAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail) )
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getCompletionStatus().equals(CompletionStatus.COMPLETED))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyUncompletedAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail))
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyPaidAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail))
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getPaymentStatus().equals(PaymentStatus.PAID))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyUnpaidAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail))
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getPaymentStatus().equals(PaymentStatus.UNPAID))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyAppointmentsByDate(String loggedEmail, LocalDateTime date) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail))
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getStartDateTime().toLocalDate().equals(date.toLocalDate()))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAllMyNextAppointments(String loggedEmail) {
        return appointmentRepository.findAll()
                .stream()
                .filter(appointment -> (appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                                                || appointment.getPatientEntity().getEmail().equals(loggedEmail))
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getStartDateTime().isAfter(LocalDateTime.now()))
                .map(AppointmentConverter::convertAppointmentEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO applyForAppointmentByPatient(String loggedEmail, AppointmentDTO appointmentDTO) {

        List<AppointmentEntity> patientAppointments = appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getPatientEntity().getEmail().equals(loggedEmail)
                    && appointment.getValidity().equals(Boolean.TRUE)
                    && appointment.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
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
                .filter(appointment -> appointment.getDoctorEntity().getEmail().equals(appointmentDTO.getDoctor().getEmail())
                    && appointment.getValidity().equals(Boolean.TRUE)
                    && appointment.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .collect(Collectors.toList());

        if(!AppointmentUtils.isDateTimeFree(appointmentDTO.getStartDateTime(), appointmentDTO.getEndDateTime(), doctorAppointments)) {
            LOGGER.info("Wrong Dates or Doctor may have another scheduled Appointment.");
            return new AppointmentDTO();
        }

        appointmentDTO.getPatient().setId(userRepository.findByEmail(loggedEmail).get().getId());
        AppointmentEntity appointment = createAppointmentEntity(appointmentDTO, loggedEmail);
        appointmentRepository.save(appointment);
        return AppointmentConverter.convertAppointmentEntityToDTO(appointment);
    }

    @Override
    public AppointmentDTO createAppointmentByDoctor(String loggedEmail, AppointmentDTO appointmentDTO) {
        if(userRepository.findByEmail(appointmentDTO.getPatient().getEmail()).isEmpty() || operationRepository.findById(appointmentDTO.getOperation().getId()).isEmpty()) {
            LOGGER.info("Patient or operation does not exist.");
            return new AppointmentDTO();
        }

        List<AppointmentEntity> doctorAppointments = appointmentRepository.findAll().stream()
                .filter(appointment -> appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                    && appointment.getValidity().equals(Boolean.TRUE)
                    && appointment.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                .collect(Collectors.toList());
        if(!AppointmentUtils.isDateTimeFree(appointmentDTO.getStartDateTime(), appointmentDTO.getEndDateTime(), doctorAppointments)) {
            LOGGER.info("Doctor may have another scheduled appointment or wrong dates.");
            return new AppointmentDTO();
        }

        appointmentDTO.getDoctor().setId(userRepository.findByEmail(loggedEmail).get().getId());
        AppointmentEntity appointment = createAppointmentEntity(appointmentDTO, loggedEmail);
        appointmentRepository.save(appointment);
        return AppointmentConverter.convertAppointmentEntityToDTO(appointment);
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
    public AppointmentDTO changeDate(String loggedEmail, Integer id, AppointmentNewDateDTO newDate) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            List<AppointmentEntity> doctorAppointments = appointmentRepository.findAll().stream()
                    .filter(appointment -> appointment.getDoctorEntity().getEmail().equals(loggedEmail)
                        && appointment.getValidity().equals(Boolean.TRUE)
                        && appointment.getCompletionStatus().equals(CompletionStatus.UNCOMPLETED))
                    .collect(Collectors.toList());

            if(AppointmentUtils.isDateTimeFree(newDate.getStartDateTime(), newDate.getEndDateTime(), doctorAppointments)) {
                AppointmentEntity appointmentToChange = appointmentRepository.findById(id).get();
                appointmentToChange.setStartDateTime(newDate.getStartDateTime());
                appointmentToChange.setEndDateTime(newDate.getEndDateTime());
                appointmentRepository.save(appointmentToChange);
                return AppointmentConverter.convertAppointmentEntityToDTO(appointmentToChange);
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
            AppointmentEntity appointmentToComplete = appointmentRepository.findById(id).get();
            appointmentToComplete.setCompletionStatus(CompletionStatus.COMPLETED);
            appointmentRepository.save(appointmentToComplete);
            return AppointmentConverter.convertAppointmentEntityToDTO(appointmentToComplete);
        }
        LOGGER.info("Appointment does not exist.");
        return new AppointmentDTO();
    }

    @Override
    public AppointmentDTO changePaymentStatus(Integer id) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointmentToPay = appointmentRepository.findById(id).get();
            appointmentToPay.setPaymentStatus(PaymentStatus.PAID);
            appointmentRepository.save(appointmentToPay);
            return AppointmentConverter.convertAppointmentEntityToDTO(appointmentToPay);
        }
        LOGGER.info("Appointment does not exist.");
        return new AppointmentDTO();
    }

    @Override
    public Integer deleteAppointment(Integer id) {
        if (appointmentRepository.findById(id).isPresent() && appointmentRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            AppointmentEntity appointmentToDelete = appointmentRepository.findById(id).get();
            appointmentToDelete.setValidity(Boolean.FALSE);
            appointmentRepository.save(appointmentToDelete);
            return id;
        }
        LOGGER.info("Appointment does not exist.");
        return null;
    }
}
