package ta.presentation.dentalt.operation.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    OperationDTO operationToDTO(OperationEntity operation);

    OperationEntity operationDTOToOperation(OperationDTO operationDTO);
}
