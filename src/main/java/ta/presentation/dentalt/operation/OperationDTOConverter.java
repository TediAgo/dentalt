package ta.presentation.dentalt.operation;

public class OperationDTOConverter {

    public static OperationDTO convertOperationEntityToDTO(OperationEntity operationEntity) {
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(operationEntity.getId());
        operationDTO.setName(operationEntity.getName());
        operationDTO.setDescription(operationEntity.getDescription());
        operationDTO.setPrice(operationEntity.getPrice());
        return operationDTO;
    }
}
