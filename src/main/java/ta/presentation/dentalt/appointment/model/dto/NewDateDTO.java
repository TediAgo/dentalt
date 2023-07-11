package ta.presentation.dentalt.appointment.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDateDTO {

    @FutureOrPresent
    private LocalDateTime startDateTime;
    @FutureOrPresent
    private LocalDateTime endDateTime;
}
