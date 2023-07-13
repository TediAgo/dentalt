package ta.presentation.dentalt.appointment.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentNewDateDTO {

    @NonNull
    @FutureOrPresent
    private LocalDateTime startDateTime;
    @NonNull
    @FutureOrPresent
    private LocalDateTime endDateTime;
}
