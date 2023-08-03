package ta.presentation.dentalt.operation.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.appointment.service.implementation.AppointmentServiceImplementation;
import ta.presentation.dentalt.operation.repository.OperationRepository;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.operation.model.entity.OperationEntity;
import ta.presentation.dentalt.operation.model.mapper.OperationConverter;
import ta.presentation.dentalt.operation.service.OperationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImplementation implements OperationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImplementation.class);

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public OperationDTO getOperation(Integer id) {
        if (operationRepository.findById(id).isPresent() && operationRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            return OperationConverter.convertOperationEntityToDTO(operationRepository.findById(id).get());
        }
        LOGGER.info("Operation not found.");
        return new OperationDTO();
    }

    @Override
    public List<OperationDTO> getAllOperations () {
        return operationRepository.findAll()
                .stream()
                .filter(operation -> operation.getValidity().equals(Boolean.TRUE))
                .map(OperationConverter::convertOperationEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDTO createOperation(OperationDTO operationDTO) {
        OperationEntity operation = new OperationEntity();
        operation.setName(operationDTO.getName());
        operation.setDescription(operationDTO.getDescription());
        operation.setPrice(operationDTO.getPrice());
        operation.setValidity(Boolean.TRUE);
        operationRepository.save(operation);
        return OperationConverter.convertOperationEntityToDTO(operation);
    }

    @Override
    public OperationDTO changeOperation(OperationDTO operationDTO) {
        if (operationRepository.findById(operationDTO.getId()).isPresent() && operationRepository.findById(operationDTO.getId()).get().getValidity().equals(Boolean.TRUE)) {
            OperationEntity operation = operationRepository.findById(operationDTO.getId()).get();
            operation.setName(operationDTO.getName());
            operation.setDescription(operationDTO.getDescription());
            operation.setPrice(operationDTO.getPrice());
            operationRepository.save(operation);
            return OperationConverter.convertOperationEntityToDTO(operation);
        }
        LOGGER.info("Operation not found.");
        return new OperationDTO();
    }

    @Override
    public OperationDTO changeName(Integer id, String name) {
        if (operationRepository.findById(id).isPresent() && operationRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OperationEntity operation = operationRepository.findById(id).get();
            operation.setName(name);
            operationRepository.save(operation);
            return OperationConverter.convertOperationEntityToDTO(operation);
        }
        LOGGER.info("Operation not found.");
        return new OperationDTO();
    }

    @Override
    public OperationDTO changeDescription(Integer id, String description) {
        if (operationRepository.findById(id).isPresent() && operationRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OperationEntity operation = operationRepository.findById(id).get();
            operation.setDescription(description);
            operationRepository.save(operation);
            return OperationConverter.convertOperationEntityToDTO(operation);
        }
        LOGGER.info("Operation not found.");
        return new OperationDTO();
    }

    @Override
    public OperationDTO changePrice(Integer id, Double price) {
        if (operationRepository.findById(id).isPresent() && operationRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OperationEntity operation = operationRepository.findById(id).get();
            operation.setPrice(price);
            operationRepository.save(operation);
            return OperationConverter.convertOperationEntityToDTO(operation);
        }
        LOGGER.info("Operation not found.");
        return new OperationDTO();
    }

    @Override
    public Integer deleteOperation(Integer id) {
        if (operationRepository.findById(id).isPresent() && operationRepository.findById(id).get().getValidity().equals(Boolean.TRUE)) {
            OperationEntity operation = operationRepository.findById(id).get();
            operation.setValidity(Boolean.FALSE);
            operationRepository.save(operation);
            return id;
        }
        LOGGER.info("Operation not found.");
        return null;
    }

    @Override
    public OperationDTO restoreOperation(Integer id) {
        if (operationRepository.findById(id).isPresent() && operationRepository.findById(id).get().getValidity().equals(Boolean.FALSE)) {
            OperationEntity operation = operationRepository.findById(id).get();
            operation.setValidity(Boolean.TRUE);
            operationRepository.save(operation);
            return OperationConverter.convertOperationEntityToDTO(operation);
        }
        LOGGER.info("Operation not found.");
        return new OperationDTO();
    }
}
