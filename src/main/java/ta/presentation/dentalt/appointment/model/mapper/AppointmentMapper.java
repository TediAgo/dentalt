package ta.presentation.dentalt.appointment.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.entity.AppointmentEntity;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO appointmentToDTO(AppointmentEntity appointment);

    AppointmentEntity appointmentDTOToAppointment(AppointmentDTO appointmentDTO);
}
