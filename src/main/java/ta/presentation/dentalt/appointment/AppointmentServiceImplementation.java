package ta.presentation.dentalt.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ta.presentation.dentalt.operation.OperationRepository;

@Service
public class AppointmentServiceImplementation implements AppointmentService{

    @Autowired
    private OperationRepository operationRepository;


}
