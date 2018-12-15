package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GraphController {

    @GetMapping(path = "/steps")
    public GraphResponse getSteps() {
        List<Step> responseSteps = new ArrayList<>();
        List<Edge> responseEdges = new ArrayList<>();

        Messages.Feature feature = GherkinFileReader.getFeature();

        Step featureNode = new Step(feature.getName(), Step.FEATURE_GROUP);
        responseSteps.add(featureNode);

        feature.getChildrenList().forEach(featureChild -> {
            Messages.Scenario scenario = featureChild.getScenario();

            Step newScenario = null;
            Optional<Step> scenarioAlreadyCreated = responseSteps.stream().filter(s -> s.getLabel().equals(scenario.getName())).findFirst();
            if (scenarioAlreadyCreated.isPresent()) {
                newScenario = scenarioAlreadyCreated.get();
            } else {
                newScenario = new Step(scenario.getName(), Step.SCENARIO_GROUP);
            }

            List<Messages.Step> steps = scenario.getStepsList();

            List<Step> stepsForEdges = steps.stream().map(step -> {
                Optional<Step> stepAlreadyCreated = responseSteps.stream().filter(s -> s.getLabel().equals(step.getKeyword().toUpperCase() + step.getText())).findFirst();
                if (stepAlreadyCreated.isPresent()) {
                    return stepAlreadyCreated.get();
                } else {
                    return new Step(step);
                }
            }).collect(Collectors.toList());
            stepsForEdges.add(0, newScenario);
            stepsForEdges.add(0, featureNode);

            List<Edge> edges = new ArrayList<>();

            for (int i = 1; i < stepsForEdges.size(); i++) {
                Edge edge = new Edge(stepsForEdges.get(i - 1).getId(), stepsForEdges.get(i).getId());
                edges.add(edge);
            }

            List<Step> stepsToAdd = stepsForEdges.stream().filter(step -> {
                return !responseSteps.stream().anyMatch(s -> s.getLabel().equals(step.getLabel()));
            }).collect(Collectors.toList());

            responseSteps.addAll(stepsToAdd);
            responseEdges.addAll(edges);
        });

        return new GraphResponse(responseSteps, responseEdges);
    }

    @Value
    private class GraphResponse {

        private List<Step> nodes;
        private List<Edge> edges;
    }

    @Value
    private class Edge {

        private int from;
        private int to;
    }
}


