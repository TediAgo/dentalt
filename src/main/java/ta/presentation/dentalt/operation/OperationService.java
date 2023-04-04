package ta.presentation.dentalt.operation;

import java.util.List;

public interface OperationService {

    List<OperationDTO> getAllOperations();

    OperationDTO getOperation(Integer id);
}
