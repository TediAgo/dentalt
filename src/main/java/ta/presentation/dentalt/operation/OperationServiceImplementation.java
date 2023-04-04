package ta.presentation.dentalt.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImplementation implements OperationService{

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List<OperationDTO> getAllOperations () {
        return operationRepository.findAll()
                .stream()
                .map(OperationDTOConverter::convertOperationEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO getOperation(Integer id) {
        return OperationDTOConverter.convertOperationEntityToDTO(operationRepository.findById(id));
    }
}
