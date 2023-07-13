package ta.presentation.dentalt.offers.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffersNewDateDTO {

    @NonNull
    @FutureOrPresent
    private LocalDate startDate;
    @NonNull
    @FutureOrPresent
    private LocalDate endDate;
}
