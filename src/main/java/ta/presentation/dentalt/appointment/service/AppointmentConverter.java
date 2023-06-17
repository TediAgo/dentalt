package ta.presentation.dentalt.appointment.service;

import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.AppointmentEntity;
import ta.presentation.dentalt.operation.OperationRepository;
import ta.presentation.dentalt.operation.service.OperationConverter;
import ta.presentation.dentalt.user.UserRepository;
import ta.presentation.dentalt.user.service.UserConverter;

public class AppointmentConverter {

    private static UserRepository userRepository;
    private static OperationRepository operationRepository;

    public static AppointmentDTO convertAppointmentEntityToDTO(AppointmentEntity appointmentEntity) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setId(appointmentEntity.getId());
        appointmentDTO.setStartDateTime(appointmentEntity.getStartDateTime());
        appointmentDTO.setEndDateTime(appointmentEntity.getEndDateTime());
        appointmentDTO.setCompletionStatus(appointmentEntity.getCompletionStatus());
        appointmentDTO.setPaymentStatus(appointmentEntity.getPaymentStatus());
        appointmentDTO.setPatient(UserConverter.convertUserEntityToDTO(appointmentEntity.getPatientEntity()));
        appointmentDTO.setDoctor(UserConverter.convertUserEntityToDTO(appointmentEntity.getDoctorEntity()));
        appointmentDTO.setOperation(OperationConverter.convertOperationEntityToDTO(appointmentEntity.getOperationEntity()));

        return appointmentDTO;
    }

    public static AppointmentEntity createAppointmentEntity(AppointmentDTO appointmentDTO) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();

        appointmentEntity.setStartDateTime(appointmentDTO.getStartDateTime());
        appointmentEntity.setEndDateTime(appointmentDTO.getEndDateTime());
        appointmentEntity.setCompletionStatus(appointmentDTO.getCompletionStatus());
        appointmentEntity.setPaymentStatus(appointmentDTO.getPaymentStatus());
        appointmentEntity.setPatientEntity(userRepository.findById(appointmentDTO.getPatient().getId()).get());
        appointmentEntity.setDoctorEntity(userRepository.findById(appointmentDTO.getDoctor().getId()).get());
        appointmentEntity.setOperationEntity(operationRepository.findById(appointmentDTO.getOperation().getId()).get());

        return appointmentEntity;
    }
}
