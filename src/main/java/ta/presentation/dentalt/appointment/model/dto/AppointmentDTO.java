package ta.presentation.dentalt.appointment.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.presentation.dentalt.appointment.model.enums.CompletionStatus;
import ta.presentation.dentalt.appointment.model.enums.PaymentStatus;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.user.model.dto.UserDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Integer id;
    private UserDTO patient;
    private UserDTO doctor;
    private OperationDTO operation;
    @FutureOrPresent
    private LocalDateTime startDateTime;
    @FutureOrPresent
    private LocalDateTime endDateTime;
    private CompletionStatus completionStatus;
    private PaymentStatus paymentStatus;
}
