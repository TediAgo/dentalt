package ta.presentation.dentalt.offer.model.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferNewDateDTO {

    @NonNull
    @FutureOrPresent
    private LocalDate begin;
    @NonNull
    @FutureOrPresent
    private LocalDate finish;
}
