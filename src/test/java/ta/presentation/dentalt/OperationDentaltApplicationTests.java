package ta.presentation.dentalt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import ta.presentation.dentalt.security.auth.model.AuthenticationRequest;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;

@SpringBootTest(classes = DentaltApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperationDentaltApplicationTests extends BaseTest{

	@BeforeEach
	public void init() {
		var request = new AuthenticationRequest("email1", "tedi");
		this.authenticationResponse = doLogin(request);
	}

	@Test
	public void testGetOperation() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());
		HttpEntity<OperationDTO> entity = new HttpEntity<>(null, headers);
		ResponseEntity<OperationDTO> response = testRestTemplate.exchange(
				createURLWithPort("/operations/1"),
				HttpMethod.GET, entity, OperationDTO.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("name",response.getBody().getName());
	}

	@Test
	public void testCreateOperation() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());
		OperationDTO operationDTO = new OperationDTO();
		operationDTO.setName("testOperation");
		operationDTO.setDescription("testDescription");
		operationDTO.setPrice(50.0);
		HttpEntity<OperationDTO> entity = new HttpEntity<>(operationDTO, headers);
		ResponseEntity<OperationDTO> response = testRestTemplate.exchange(
				createURLWithPort("/operations/create"),
				HttpMethod.POST, entity, OperationDTO.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testChangeOperationName() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());
		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		ResponseEntity<OperationDTO> response = testRestTemplate.exchange(
				createURLWithPort("/operations/1"),
				HttpMethod.GET, entity, OperationDTO.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("name", response.getBody().getName());
		entity = new HttpEntity<>("changedName", headers);
		response = testRestTemplate.exchange(
				createURLWithPort("/operations/1/changeName"),
				HttpMethod.PUT, entity, OperationDTO.class);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("changedName", response.getBody().getName());
	}
}
