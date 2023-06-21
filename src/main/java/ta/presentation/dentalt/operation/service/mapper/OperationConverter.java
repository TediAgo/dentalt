package ta.presentation.dentalt.operation.service.mapper;

import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;

public class OperationConverter {

    public static OperationDTO convertOperationEntityToDTO(OperationEntity operationEntity) {
        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setId(operationEntity.getId());
        operationDTO.setName(operationEntity.getName());
        operationDTO.setDescription(operationEntity.getDescription());
        operationDTO.setPrice(operationEntity.getPrice());

        return operationDTO;
    }

    public static OperationEntity createOperationEntity(OperationDTO operationDTO) {
        OperationEntity operationEntity = new OperationEntity();

        operationEntity.setName(operationDTO.getName());
        operationEntity.setDescription(operationDTO.getDescription());
        operationEntity.setPrice(operationDTO.getPrice());
        operationEntity.setValidity(Boolean.TRUE);

        return operationEntity;
    }
}
