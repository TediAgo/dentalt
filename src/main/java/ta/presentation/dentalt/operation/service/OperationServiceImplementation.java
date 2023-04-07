package ta.presentation.dentalt.operation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.operation.OperationRepository;
import ta.presentation.dentalt.operation.model.OperationDTO;
import ta.presentation.dentalt.operation.model.OperationEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImplementation implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public OperationDTO getOperation(Integer id) {
        if (operationRepository.findById(id).isPresent()) {
            return OperationConverter.convertOperationEntityToDTO(operationRepository.findById(id).get());
        }
        return new OperationDTO();
    }

    @Override
    public List<OperationDTO> getAllOperations () {
        return operationRepository.findAll()
                .stream()
                .map(OperationConverter::convertOperationEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO createOperation(OperationDTO operationDTO) {
        OperationEntity operationEntityToPersist = OperationConverter.createOperationEntity(operationDTO);
        operationRepository.save(operationEntityToPersist);
        return OperationConverter.convertOperationEntityToDTO(operationEntityToPersist);
    }

    @Override
    public OperationDTO changeOperationName(Integer id, String name) {
        if (operationRepository.findById(id).isPresent()) {
            OperationEntity operationEntityToChangeName = operationRepository.findById(id).get();
            operationEntityToChangeName.setName(name);
            operationRepository.save(operationEntityToChangeName);
            return OperationConverter.convertOperationEntityToDTO(operationEntityToChangeName);
        }
        return new OperationDTO();
    }

    @Override
    public OperationDTO changeOperationDescription(Integer id, String description) {
        if (operationRepository.findById(id).isPresent()) {
            OperationEntity operationEntityToChangeDescription = operationRepository.findById(id).get();
            operationEntityToChangeDescription.setDescription(description);
            operationRepository.save(operationEntityToChangeDescription);
            return OperationConverter.convertOperationEntityToDTO(operationEntityToChangeDescription);
        }
        return new OperationDTO();
    }

    @Override
    public OperationDTO changeOperationPrice(Integer id, Double price) {
        if (operationRepository.findById(id).isPresent()) {
            OperationEntity operationEntityToChangePrice = operationRepository.findById(id).get();
            operationEntityToChangePrice.setPrice(price);
            operationRepository.save(operationEntityToChangePrice);
            return OperationConverter.convertOperationEntityToDTO(operationEntityToChangePrice);
        }
        return new OperationDTO();
    }

    @Override
    public Integer deleteOperation(Integer id) {
        if (operationRepository.findById(id).isPresent()) {
            OperationEntity operationEntityToDelete = operationRepository.findById(id).get();
            operationEntityToDelete.setValidity(Boolean.FALSE);
            operationRepository.save(operationEntityToDelete);
            return id;
        }
        return null;
    }

    @Override
    public OperationDTO restoreOperation(Integer id) {
        if (operationRepository.findById(id).isPresent()) {
            OperationEntity operationEntityToRestore = operationRepository.findById(id).get();
            operationEntityToRestore.setValidity(Boolean.TRUE);
            operationRepository.save(operationEntityToRestore);
            return OperationConverter.convertOperationEntityToDTO(operationEntityToRestore);
        }
        return new OperationDTO();
    }
}
