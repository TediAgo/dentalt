package ta.presentation.dentalt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import ta.presentation.dentalt.auth.model.AuthenticationRequest;
import ta.presentation.dentalt.operation.model.dto.OperationDTO;

@SpringBootTest(classes = DentaltApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DentaltApplicationTests extends BaseTest{

	@BeforeEach
	public void init() {
		var request = new AuthenticationRequest("email1", "tedi");
		this.authenticationResponse = doLogin(request);
	}

	@Test
	public void testGetOperation() {
		var request = new AuthenticationRequest("email1", "tedi");
		this.authenticationResponse = doLogin(request);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authenticationResponse.getAccessToken());
		HttpEntity<OperationDTO> entity = new HttpEntity<>(null, headers);

		ResponseEntity<OperationDTO> response = testRestTemplate.exchange(
				createURLWithPort("/operations/1"),
				HttpMethod.GET, entity, OperationDTO.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals("name",response.getBody().getName());
	}
}
