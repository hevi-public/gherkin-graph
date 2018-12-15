package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GraphController {

    @GetMapping(path = "/steps")
    public GraphResponse getSteps() {
        Messages.Feature feature = GherkinFileReader.getFeature();


        Messages.Scenario scenario = feature.getChildren(0).getScenario();
        List<Messages.Step> steps = scenario.getStepsList();

        List<Step> mappedSteps = steps.stream().map(step -> new Step(step)).collect(Collectors.toList());

        List<Edge> edges = new ArrayList<>();

        for (int i = 1; i < mappedSteps.size(); i++) {
            Edge edge = new Edge(mappedSteps.get(i - 1).getId(), mappedSteps.get(i).getId());
            edges.add(edge);
        }

        return new GraphResponse(mappedSteps, edges);
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


