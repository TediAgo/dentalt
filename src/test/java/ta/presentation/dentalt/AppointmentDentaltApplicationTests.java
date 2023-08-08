package ta.presentation.dentalt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import ta.presentation.dentalt.appointment.model.dto.AppointmentDTO;
import ta.presentation.dentalt.appointment.model.enums.CompletionStatus;
import ta.presentation.dentalt.appointment.model.enums.PaymentStatus;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;
import ta.presentation.dentalt.security.auth.model.AuthenticationRequest;
import ta.presentation.dentalt.user.model.dto.UserDTO;

import java.time.LocalDateTime;

@SpringBootTest(classes = DentaltApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentDentaltApplicationTests extends BaseTest{

    @BeforeEach
    public void init() {
        var request = new AuthenticationRequest("email7", "tedi");
        this.authenticationResponse = doLogin(request);
    }

    @Test
    public void testApplyAppointmentByPatient() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());

        UserDTO patientDTO = new UserDTO();
        patientDTO.setEmail(authenticationResponse.getEmail());
        UserDTO doctorDTO = new UserDTO();
        doctorDTO.setEmail("email3");
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(5);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setDoctor(doctorDTO);
        appointmentDTO.setOperation(operationDTO);
        appointmentDTO.setStartDateTime(LocalDateTime.now().plusYears(1).plusHours(1));
        appointmentDTO.setEndDateTime(LocalDateTime.now().plusYears(1).plusHours(2));
        appointmentDTO.setCompletionStatus(CompletionStatus.UNCOMPLETED);
        appointmentDTO.setPaymentStatus(PaymentStatus.UNPAID);

        HttpEntity<AppointmentDTO> entity = new HttpEntity<>(appointmentDTO, headers);
        ResponseEntity<AppointmentDTO> response = testRestTemplate.exchange(
                createURLWithPort("/appointments/createByPatient"),
                HttpMethod.POST, entity, AppointmentDTO.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFailApplyAppointmentByPatient() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());

        UserDTO patientDTO = new UserDTO();
        patientDTO.setEmail(authenticationResponse.getEmail());
        UserDTO doctorDTO = new UserDTO();
        doctorDTO.setEmail("email3");
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setId(5);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setPatient(patientDTO);
        appointmentDTO.setDoctor(doctorDTO);
        appointmentDTO.setOperation(operationDTO);
        appointmentDTO.setStartDateTime(LocalDateTime.now().plusYears(1).plusHours(1));
        appointmentDTO.setEndDateTime(LocalDateTime.now().plusYears(1).plusHours(2));
        appointmentDTO.setCompletionStatus(CompletionStatus.UNCOMPLETED);
        appointmentDTO.setPaymentStatus(PaymentStatus.UNPAID);

        HttpEntity<AppointmentDTO> entity = new HttpEntity<>(appointmentDTO, headers);
        ResponseEntity<AppointmentDTO> response = testRestTemplate.exchange(
                createURLWithPort("/appointments/createByPatient"),
                HttpMethod.POST, entity, AppointmentDTO.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
