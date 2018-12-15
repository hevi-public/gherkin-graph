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
        feature.getChildrenList().forEach(child -> {
            Messages.Scenario scenario = child.getScenario();
            List<Messages.Step> steps = scenario.getStepsList();

            //steps.stream().map(step -> new Step(step)).collect(Collectors.toList());
            //Optional<Step> first = responseSteps.stream().filter(s -> s.getLabel().equals(step.getKeyword() + step.getText())).findFirst();

            List<Step> stepsForEdges = steps.stream().map(step -> {
                Optional<Step> first = responseSteps.stream().filter(s -> s.getLabel().equals(step.getKeyword() + step.getText())).findFirst();
                if (first.isPresent()) {
                    return first.get();
                } else {
                    return new Step(step);
                }
            }).collect(Collectors.toList());

            List<Edge> edges = new ArrayList<>();

            for (int i = 1; i < stepsForEdges.size(); i++) {
                Edge edge = new Edge(stepsForEdges.get(i - 1).getId(), stepsForEdges.get(i).getId());
                edges.add(edge);
            }

            List<Step> stepsToAdd = stepsForEdges.stream().filter(step -> {
                return !responseSteps.stream().filter(s -> s.getLabel().equals(step.getLabel())).findFirst().isPresent();
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


