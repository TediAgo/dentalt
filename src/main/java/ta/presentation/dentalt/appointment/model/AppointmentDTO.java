package ta.presentation.dentalt.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.presentation.dentalt.operation.model.OperationDTO;
import ta.presentation.dentalt.user.model.UserDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Integer id;
    private UserDTO patient;
    private UserDTO doctor;
    private OperationDTO operation;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private CompletionStatus completionStatus;
    private PaymentStatus paymentStatus;
}
