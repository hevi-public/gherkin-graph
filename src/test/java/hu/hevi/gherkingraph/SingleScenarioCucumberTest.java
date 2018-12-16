package hu.hevi.gherkingraph;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

public class SingleScenarioCucumberTest extends SpringIntegrationTest {

    @LocalServerPort
    private int localServerPort;

    private String singleScenarioLocationParam = "filename=src/test/resources/features/retrieving_data_with_one_scenario.feature";

    @Given("^a feature file is loaded with a single scenario$")
    public void a_feature_file_is_loaded_with_a_single_scenario() {
        currentLocationParam = singleScenarioLocationParam;
    }

    @When("^the client calls /api/steps$")
    public void the_client_calls_api_steps() {
        response = restTemplate.getForEntity("http://localhost:" + localServerPort + "/api/steps?" + currentLocationParam, GraphResponse.class);
        System.out.println(response);
    }

    @And("^the client receives response data$")
    public void the_client_receives_response_data() {
        assertNotNull(response);
    }

    @Then("^the client receives status code of 200$")
    public void the_client_receives_status_code_of_200() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @And("^the client receives data for nodes$")
    public void the_client_receives_data_for_nodes() {
        GraphResponse responseBody = response.getBody();
        assertEquals(8, responseBody.getNodes().size());
    }

    @And("^the client receives data for edges$")
    public void the_client_receives_data_for_edges() {
        GraphResponse responseBody = response.getBody();
        assertEquals(7, responseBody.getEdges().size());
    }

}