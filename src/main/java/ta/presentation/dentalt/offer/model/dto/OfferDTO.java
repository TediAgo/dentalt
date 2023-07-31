package ta.presentation.dentalt.offer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ta.presentation.dentalt.category.model.dto.CategoryDTO;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {

    private Integer id;
    private String name;
    private LocalDate begin;
    private LocalDate finish;
    private Double price;
    private List<OperationDTO> operations;
    private CategoryDTO category;
}
