package ta.presentation.dentalt.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImplementation implements OperationService{

    @Autowired
    private OperationRepository operationRepository;


}
