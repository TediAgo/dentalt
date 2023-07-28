package ta.presentation.dentalt.operation.service.services;

import ta.presentation.dentalt.operation.model.dto.OperationDTO;

import java.util.List;

public interface OperationService {

    OperationDTO getOperation(Integer id);

    List<OperationDTO> getAllOperations();

    OperationDTO createOperation(OperationDTO operationDTO);

    OperationDTO changeOperation(OperationDTO operationDTO);

    OperationDTO changeName(Integer id, String name);

    OperationDTO changeDescription(Integer id, String description);

    OperationDTO changePrice(Integer id, Double price);

    Integer deleteOperation(Integer id);

    OperationDTO restoreOperation(Integer id);
}
