package ta.presentation.dentalt.appointment.service.mapper;

import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.entity.AppointmentEntity;
import ta.presentation.dentalt.operation.service.mapper.OperationConverter;
import ta.presentation.dentalt.user.service.mapper.UserConverter;

public class AppointmentConverter {

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
}
