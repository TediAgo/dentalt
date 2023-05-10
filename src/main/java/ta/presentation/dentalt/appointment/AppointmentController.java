package ta.presentation.dentalt.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.getAppointment(id));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<AppointmentDTO>> getUserAppointments(@PathVariable(value = "id") Integer userId) {
        return ResponseEntity.ok(appointmentService.getUserAppointment(userId));
    }

    @PostMapping("/createForPatient")
    public ResponseEntity<AppointmentDTO> createAppointmentForPatient() {
        return ResponseEntity.ok(appointmentService.createAppointmentForPatient());
    }

    @PostMapping("/createForDoctor")
    public ResponseEntity<AppointmentDTO> createAppointmentForDoctor() {
        return ResponseEntity.ok(appointmentService.createAppointmentForDoctor());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteAppointment(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<AppointmentDTO> restoreAppointment(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.restoreAppointment(id));
    }
}
