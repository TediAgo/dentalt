package ta.presentation.dentalt.operation.service.mapper;

import org.mapstruct.Mapper;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;

@Mapper
public interface OperationMapper {

    OperationDTO operationToDTO(OperationEntity operation);
    OperationEntity operationDTOToOperation(OperationDTO operationDTO);
}
