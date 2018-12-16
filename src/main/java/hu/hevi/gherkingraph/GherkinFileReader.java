package hu.hevi.gherkingraph;

import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.Messages;

import java.util.List;

public class GherkinFileReader {

    public static Messages.Feature getFeature(List<String> paths) {
        List<Messages.Wrapper> messages = Gherkin.fromPaths(paths, false, true, false);
        Messages.GherkinDocument gherkinDocument = messages.get(0).getGherkinDocument();
        return gherkinDocument.getFeature();
    }

}