package ta.presentation.dentalt.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ta.presentation.dentalt.appointment.service.AppointmentService;

@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


}
