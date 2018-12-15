package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GherkinGraphApplication {

	public static void main(String[] args) {
		Messages.Feature feature = GherkinFileReader.getFeature();


		Messages.Scenario scenario = feature.getChildren(0).getScenario();
		List<Messages.Step> steps = scenario.getStepsList();

		List<Step> mappedSteps = steps.stream().map(step -> new Step(step)).collect(Collectors.toList());

		List<Step> rootSteps = new ArrayList<>();





		SpringApplication.run(GherkinGraphApplication.class, args);
	}
}

