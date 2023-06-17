package ta.presentation.dentalt.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewDateDTO {

    private LocalDateTime newStartDateTime;
    private LocalDateTime newEndDateTime;
}
