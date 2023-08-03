package ta.presentation.dentalt;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ta.presentation.dentalt.security.auth.model.AuthenticationRequest;
import ta.presentation.dentalt.security.auth.model.AuthenticationResponse;

public class BaseTest {

    @LocalServerPort
    protected int port;
    protected TestRestTemplate testRestTemplate = new TestRestTemplate();
    protected AuthenticationResponse authenticationResponse;

    protected AuthenticationResponse doLogin(AuthenticationRequest authenticationRequest) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(authenticationRequest, headers);
        ResponseEntity<AuthenticationResponse> response = testRestTemplate.exchange(
                createURLWithPort("/home/authenticate"),
                HttpMethod.POST, entity, AuthenticationResponse.class);
        return response.getBody();
    }

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
