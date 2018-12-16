package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @Getter
    private List<Integer> scenarioIds = new ArrayList<>();

    /**
     * When called with SCENARIO_GROUP, then scenarioId is set to this.id instead of supplied parameter
     *
     * @param scenarioName
     * @param type
     * @param scenarioId
     */
    public Step(String scenarioName, String type, Integer scenarioId) {
        this.id = Step.idSequence++;
        this.label = scenarioName;
        this.type = Type.valueOf(type.toUpperCase().trim());
        if (SCENARIO_GROUP.equals(type)) {
            this.scenarioIds.add(this.id);
        } else {
            if (scenarioId != null) {
                this.scenarioIds.add(scenarioId);
            }
        }
    }

    public Step(Messages.Step ofStep, Integer scenarioId) {
        this.id = Step.idSequence++;
        this.label = ofStep.getKeyword().toUpperCase() + ofStep.getText();
        this.type = Type.valueOf(ofStep.getKeyword().toUpperCase().trim());
        if (scenarioId != null) {
            this.scenarioIds.add(scenarioId);
        }
    }

    public void addScenarioId(int scenarioId) {
        this.scenarioIds.add(scenarioId);
    }

    public void addScenarioIds(List<Integer> scenarioIds) {
        this.scenarioIds.addAll(scenarioIds);
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
