package hu.hevi.gherkingraph;

import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(
        classes = GherkinGraphApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {

    protected RestTemplate restTemplate = new RestTemplate();

    protected ResponseEntity<GraphResponse> response;

    @Before
    public void setUp() {
        response = null;
    }

}