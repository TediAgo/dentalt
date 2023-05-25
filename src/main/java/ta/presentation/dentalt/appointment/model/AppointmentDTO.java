package ta.presentation.dentalt.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.presentation.dentalt.user.model.UserAppointmentDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Integer id;
    private UserAppointmentDTO patient;
    private UserAppointmentDTO doctor;
    private String operationName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String completionStatus;
    private String paymentStatus;
}
