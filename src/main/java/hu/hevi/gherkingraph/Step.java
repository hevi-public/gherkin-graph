package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Step {


    private static final String UNKNOWN_GROUP = "UNKNOWN";
    public static final String FEATURE_GROUP = "FEATURE";
    public static final String SCENARIO_GROUP = "SCENARIO";
    protected static int idSequence = 0;

    @Getter
    private int id;
    @Getter
    private String label;
    @Getter
    private Keyword keyword;
    private String type;

    public Step(String scenarioName) {
        this.id = Step.idSequence++;
        this.label = scenarioName;
    }

    public Step(String scenarioName, String type) {
        this.id = Step.idSequence++;
        this.label = scenarioName;
        this.type = type;
    }

    public Step(Messages.Step ofStep) {
        this.id = Step.idSequence++;
        this.label = ofStep.getKeyword() + ofStep.getText();
        this.keyword = Keyword.valueOf(ofStep.getKeyword().toUpperCase().trim());
    }

    public int getValue() {
        if (keyword == Keyword.GIVEN) {
            return 2;
        }
        return 1;
    }

    public String getGroup() {
        if (keyword != null) {
            return Integer.toString(keyword.ordinal());
        }
        switch (type) {
            case "FEATURE":
                return FEATURE_GROUP;
            case "SCENARIO":
                return SCENARIO_GROUP;
            default:
                return UNKNOWN_GROUP;
        }

    }

    private enum Keyword {
        GIVEN, WHEN, THEN, AND, BUT
    }
}
