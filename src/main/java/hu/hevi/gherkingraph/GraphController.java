package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@RestController
@RequestMapping("/api")
public class GraphController {

    @Autowired
    private ApplicationProperties applicationProperties;

    @GetMapping(path = "/steps")
    public GraphResponse getSteps(@RequestParam(value = "filename", required = false) String filename) {
        List<Step> responseSteps = new ArrayList<>();
        List<Edge> responseEdges = new ArrayList<>();

        List<String> fileNames = new ArrayList<>();
        if (StringUtils.isNotBlank(filename)) {
            fileNames.addAll(singletonList(filename));
        } else {
            applicationProperties.getFeatureLocations().stream().forEach(location -> {
                Path path = Paths.get(location);
                if (path.toFile().isDirectory()) {
                    List<File> files = Arrays.asList(path.toFile().listFiles());
                    fileNames.addAll(files.stream().filter(file -> file.getName().endsWith(".feature")).map(f -> path.toString() + "/" + f.getName()).collect(Collectors.toList()));
                }
            });
        }

        fileNames.stream().forEach(f-> {
            Messages.Feature feature = GherkinFileReader.getFeature(singletonList(f));
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
        });

        return new GraphResponse(responseSteps, responseEdges);
    }
}


