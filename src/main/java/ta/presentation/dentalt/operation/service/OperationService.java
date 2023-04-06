package ta.presentation.dentalt.operation.service;

import ta.presentation.dentalt.operation.model.OperationDTO;

import java.util.List;

public interface OperationService {

    OperationDTO getOperation(Integer id);

    List<OperationDTO> getAllOperations();

    OperationDTO createOperation(OperationDTO operationDTO);

    Integer deleteOperation(Integer id);

    OperationDTO restoreOperation(Integer id);

    OperationDTO changeOperationName(Integer id, String name);

    OperationDTO changeOperationDescription(Integer id, String description);

    OperationDTO changeOperationPrice(Integer id, Double price);
}
