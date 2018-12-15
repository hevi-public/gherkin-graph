package hu.hevi.gherkingraph;

import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;

import java.util.List;

import static java.util.Collections.singletonList;

public class GherkinFileReader {

    public static Messages.Feature getFeature() {
        List<Messages.Wrapper> messages = Gherkin.fromPaths(singletonList("test.feature"), false, true, false);


        Messages.GherkinDocument gherkinDocument = messages.get(0).getGherkinDocument();


        return gherkinDocument.getFeature();

    }

}