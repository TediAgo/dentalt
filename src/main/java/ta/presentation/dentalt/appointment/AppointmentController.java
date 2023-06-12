package ta.presentation.dentalt.appointment;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.appointment.model.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.NewDateDTO;
import ta.presentation.dentalt.appointment.service.AppointmentService;
import ta.presentation.dentalt.security.token.TokenDetails;
import ta.presentation.dentalt.security.token.TokenUtility;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
//@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read', 'patient:read')")
    public ResponseEntity<AppointmentDTO> getAppointment(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.getAppointment(id));
    }

    @GetMapping("/allUncompleted/{patientId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read')")
    public ResponseEntity<List<AppointmentDTO>> getPatientUncompletedAppointments(@NonNull @PathVariable(value = "patientId") Integer patientId) {
        return ResponseEntity.ok(appointmentService.getPatientUncompletedAppointments(patientId));
    }

    @GetMapping("/allUnpaid/{patientId}")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:read', 'doctor:read')")
    public ResponseEntity<List<AppointmentDTO>> getPatientUnpaidAppointments(@NonNull @PathVariable(value = "patientId") Integer patientId) {
        return ResponseEntity.ok(appointmentService.getPatientUnpaidAppointments(patientId));
    }

    @GetMapping("/allMyAppointments")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyAppointments() {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyAppointments(token.getEmail()));
    }

    @GetMapping("/allMyCompleted")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyCompletedAppointments() {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyCompletedAppointments(token.getEmail()));
    }

    @GetMapping("/allMyUncompleted")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyUncompletedAppointments() {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyUncompletedAppointments(token.getEmail()));
    }

    @GetMapping("/allMyPaid")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyPaidAppointments() {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyPaidAppointments(token.getEmail()));
    }

    @GetMapping("/allMyUnpaid")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyUnpaidAppointments() {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyUnpaidAppointments(token.getEmail()));
    }

    @GetMapping("/all/{date}")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyAppointmentsByDate(
            @NonNull @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyAppointmentsByDate(token.getEmail(), date));
    }

    @GetMapping("/allMylNext")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyNextAppointments() {
        TokenDetails token = TokenUtility.getDetails();
        return ResponseEntity.ok(appointmentService.getAllMyNextAppointments(token.getEmail()));
    }

    @PostMapping("/createByPatient")
    //@PreAuthorize("hasAnyRole('PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('patient:create')")
    public ResponseEntity<AppointmentDTO> applyForAppointmentByPatient(@NonNull @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.applyForAppointmentByPatient(appointmentDTO));
    }

    @PostMapping("/createByDoctor")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:create')")
    public ResponseEntity<AppointmentDTO> createAppointmentByDoctor(@NonNull @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointmentByDoctor(appointmentDTO));
    }

    @PutMapping("{id}/changeDate")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<AppointmentDTO> createAppointmentByDoctor(@NonNull @PathVariable(value = "id") Integer id,
                                                                    @NonNull @RequestBody NewDateDTO newDate) {
        return ResponseEntity.ok(appointmentService.changeDate(id, newDate));
    }

    @DeleteMapping("/{id}/delete")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:delete', 'doctor:delete', 'patient:delete')")
    public ResponseEntity<Integer> deleteAppointment(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
