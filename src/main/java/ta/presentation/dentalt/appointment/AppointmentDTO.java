package ta.presentation.dentalt.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Integer id;
    private Integer patientId;
    private Integer doctorId;
    private Integer operationId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String completionStatus;
    private String paymentStatus;
}
