package ta.presentation.dentalt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import ta.presentation.dentalt.offer.model.dto.CategoryDTO;
import ta.presentation.dentalt.security.auth.model.AuthenticationRequest;

@SpringBootTest(classes = DentaltApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryDentaltApplicationTests extends BaseTest{

    @BeforeEach
    public void init() {
        var request = new AuthenticationRequest("email1", "tedi");
        this.authenticationResponse = doLogin(request);
    }

    @Test
    public void testGetCategory() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());
        HttpEntity<CategoryDTO> entity = new HttpEntity<>(null, headers);
        ResponseEntity<CategoryDTO> response = testRestTemplate.exchange(
                createURLWithPort("/category/1"),
                HttpMethod.GET, entity, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("VIP",response.getBody().getName());
    }

    @Test
    public void testCreateCategory() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("testCategory");
        categoryDTO.setDiscountPercentage(735.7);
        HttpEntity<CategoryDTO> entity = new HttpEntity<>(categoryDTO, headers);
        ResponseEntity<CategoryDTO> response = testRestTemplate.exchange(
                createURLWithPort("/category/create"),
                HttpMethod.POST, entity, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testChangeCategoryName() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authenticationResponse.getAccessToken());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<CategoryDTO> response = testRestTemplate.exchange(
                createURLWithPort("/category/1"),
                HttpMethod.GET, entity, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("VIP", response.getBody().getName());
        entity = new HttpEntity<>("changedName", headers);
        response = testRestTemplate.exchange(
                createURLWithPort("/category/1/changeName"),
                HttpMethod.PUT, entity, CategoryDTO.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("changedName", response.getBody().getName());
    }
}
