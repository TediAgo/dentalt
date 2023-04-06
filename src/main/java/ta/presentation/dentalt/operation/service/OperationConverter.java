package ta.presentation.dentalt.operation.service;

import ta.presentation.dentalt.operation.model.OperationDTO;
import ta.presentation.dentalt.operation.model.OperationEntity;

public class OperationConverter {

    public static OperationDTO convertOperationEntityToDTO(OperationEntity operationEntity) {
        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setId(operationEntity.getId());
        operationDTO.setName(operationEntity.getName());
        operationDTO.setDescription(operationEntity.getDescription());
        operationDTO.setPrice(operationEntity.getPrice());

        return operationDTO;
    }

    public static OperationEntity convertOperationDTOToEntity(OperationDTO operationDTO) {
        OperationEntity operationEntity = new OperationEntity();

        operationEntity.setName(operationDTO.getName());
        operationEntity.setDescription(operationDTO.getDescription());

        return operationEntity;
    }
}
