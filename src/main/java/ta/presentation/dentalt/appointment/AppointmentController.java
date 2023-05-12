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

    /*@GetMapping("/allMyAppointments")
    public ResponseEntity<List<AppointmentDTO>> getAllUserAppointments() {
        return ResponseEntity.ok(appointmentService.getAllMyAppointments());
    }

    @GetMapping("/allUncompleted/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAllUncompletedUserAppointments() {
        return ResponseEntity.ok(appointmentService.getAllUncompletedUserAppointments());
    }

    @GetMapping("/allCompleted/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAllCompletedUserAppointments() {
        return ResponseEntity.ok(appointmentService.getAllCompletedUserAppointments());
    }*/

    @GetMapping("/allDeleted")
    public ResponseEntity<List<AppointmentDTO>> getAllDeletedAppointments() {
        return ResponseEntity.ok(appointmentService.getAllDeletedAppointments());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping("/createForPatient")
    public ResponseEntity<AppointmentDTO> applyForAppointmentByPatient(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.applyForAppointmentByPatient());
    }

    @PostMapping("/createForDoctor")
    public ResponseEntity<AppointmentDTO> createAppointmentByDoctor(@RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointmentByDoctor());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Integer> deleteAppointment(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
