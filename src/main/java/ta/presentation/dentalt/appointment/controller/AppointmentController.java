package ta.presentation.dentalt.appointment.controller;

import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.dto.NewDateDTO;
import ta.presentation.dentalt.appointment.service.services.AppointmentService;
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
        return ResponseEntity.ok(appointmentService.getAllMyAppointments(TokenUtility.getUsernameFromToken()));
    }

    @GetMapping("/allMyCompleted")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyCompletedAppointments() {
        return ResponseEntity.ok(appointmentService.getAllMyCompletedAppointments(TokenUtility.getUsernameFromToken()));
    }

    @GetMapping("/allMyUncompleted")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyUncompletedAppointments() {
        return ResponseEntity.ok(appointmentService.getAllMyUncompletedAppointments(TokenUtility.getUsernameFromToken()));
    }

    @GetMapping("/allMyPaid")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyPaidAppointments() {
        return ResponseEntity.ok(appointmentService.getAllMyPaidAppointments(TokenUtility.getUsernameFromToken()));
    }

    @GetMapping("/allMyUnpaid")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyUnpaidAppointments() {
        return ResponseEntity.ok(appointmentService.getAllMyUnpaidAppointments(TokenUtility.getUsernameFromToken()));
    }

    @GetMapping("/all/{date}")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyAppointmentsByDate(
            @NonNull @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return ResponseEntity.ok(appointmentService.getAllMyAppointmentsByDate(TokenUtility.getUsernameFromToken(), date));
    }

    @GetMapping("/allMylNext")
    //@PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:read', 'patient:read')")
    public ResponseEntity<List<AppointmentDTO>> getAllMyNextAppointments() {
        return ResponseEntity.ok(appointmentService.getAllMyNextAppointments(TokenUtility.getUsernameFromToken()));
    }

    @PostMapping("/createByPatient")
    //@PreAuthorize("hasAnyRole('PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('patient:create')")
    public ResponseEntity<AppointmentDTO> applyForAppointmentByPatient(@Valid @NonNull @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.applyForAppointmentByPatient(TokenUtility.getUsernameFromToken(), appointmentDTO));
    }

    @PostMapping("/createByDoctor")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:create')")
    public ResponseEntity<AppointmentDTO> createAppointmentByDoctor(@Valid @NonNull @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.createAppointmentByDoctor(TokenUtility.getUsernameFromToken(), appointmentDTO));
    }

    @PutMapping("{id}/changeDate")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<AppointmentDTO> changeDate(@NonNull @PathVariable(value = "id") Integer id,
                                                                   @Valid @NonNull @RequestBody NewDateDTO newDate) {
        return ResponseEntity.ok(appointmentService.changeDate(TokenUtility.getUsernameFromToken(), id, newDate));
    }

    @PutMapping("{id}/changeCompletionStatus")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<AppointmentDTO> changeCompletionStatus(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.changeCompletionStatus(id));
    }

    @PutMapping("{id}/changePaymentStatus")
    //@PreAuthorize("hasAnyRole('DOCTOR')")
    //@PreAuthorize(value = "hasAnyAuthority('doctor:update')")
    public ResponseEntity<AppointmentDTO> changePaymentStatus(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.changePaymentStatus(id));
    }

    @DeleteMapping("/{id}/delete")
    //@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')")
    //@PreAuthorize(value = "hasAnyAuthority('admin:delete', 'doctor:delete', 'patient:delete')")
    public ResponseEntity<Integer> deleteAppointment(@NonNull @PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(appointmentService.deleteAppointment(id));
    }
}
