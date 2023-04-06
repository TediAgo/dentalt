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
    private UserDTO patientDTO;
    private UserDTO doctorDTO;
    private OperationDTO operationDTO;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String completionStatus;
    private String paymentStatus;
    private Boolean validity;
}
