package ta.presentation.dentalt.appointment.service;

import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.AppointmentEntity;
import ta.presentation.dentalt.user.service.UserConverter;

public class AppointmentConverter {

    public static AppointmentDTO convertAppointmentEntityToDTO(AppointmentEntity appointmentEntity) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();

        appointmentDTO.setId(appointmentEntity.getId());
        appointmentDTO.setOperationName(appointmentEntity.getOperationEntity().getName());
        appointmentDTO.setStartDateTime(appointmentEntity.getStartDateTime());
        appointmentDTO.setEndDateTime(appointmentEntity.getEndDateTime());
        appointmentDTO.setCompletionStatus(appointmentEntity.getCompletionStatus());
        appointmentDTO.setPaymentStatus(appointmentEntity.getPaymentStatus());
        appointmentDTO.setPatient(UserConverter.populateUserAppointmentDTO(appointmentEntity.getPatientEntity()));
        appointmentDTO.setDoctor(UserConverter.populateUserAppointmentDTO(appointmentEntity.getDoctorEntity()));
        appointmentDTO.setValidity(Boolean.TRUE);

        return appointmentDTO;
    }

    public static AppointmentEntity createAppointmentEntity(AppointmentDTO appointmentDTO) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();



        return appointmentEntity;
    }
}
