package ta.presentation.dentalt.operation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {

    private Integer id;
    private String name;
    private String description;
    private Double price;
}
