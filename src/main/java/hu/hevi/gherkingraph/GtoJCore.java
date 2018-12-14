package hu.hevi.gherkingraph;

import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;

import java.util.List;

import static java.util.Collections.singletonList;

public class GtoJCore {

    public static void asdf() {
        List<Messages.Wrapper> messages = Gherkin.fromPaths(singletonList("test.feature"), false, true, false);

        // Get the AST
        Messages.GherkinDocument gherkinDocument = messages.get(0).getGherkinDocument();

        // Get the Feature node of the AST
        Messages.Feature feature = gherkinDocument.getFeature();

        // Get the first Scenario node of the Feature node
        Messages.Scenario scenario = feature.getChildren(0).getScenario();
    }

}