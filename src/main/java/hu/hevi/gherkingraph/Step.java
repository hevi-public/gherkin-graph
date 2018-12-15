package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Step {


    private static final String UNKNOWN_GROUP = "UNKNOWN";
    public static final String FEATURE_GROUP = "FEATURE";
    public static final String SCENARIO_GROUP = "SCENARIO";

    private static int idSequence = 0;

    @Getter
    private int id;
    @Getter
    private String label;
    @Getter
    private Type type;

    public Step(String scenarioName, String type) {
        this.id = Step.idSequence++;
        this.label = scenarioName;
        this.type = Type.valueOf(type.toUpperCase().trim());
    }

    public Step(Messages.Step ofStep) {
        this.id = Step.idSequence++;
        this.label = ofStep.getKeyword().toUpperCase() + ofStep.getText();
        this.type = Type.valueOf(ofStep.getKeyword().toUpperCase().trim());
    }

    public int getValue() {
        return 9 - type.ordinal();
    }

    public int getLevel() {
        return type.ordinal();
    }

    public String getGroup() {
        switch (type) {
            case FEATURE:
                return FEATURE_GROUP;
            case SCENARIO:
                return SCENARIO_GROUP;
            default:
                if (type != null) {
                    return Integer.toString(type.ordinal());
                }
                return UNKNOWN_GROUP;
        }

    }

    private enum Type {
        FEATURE, SCENARIO, GIVEN, WHEN, THEN, AND, BUT
    }
}
