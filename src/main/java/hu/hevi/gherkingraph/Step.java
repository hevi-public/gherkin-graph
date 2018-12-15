package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class Step {

    protected static int idSequence = 0;

    @Getter
    private int id;
    @Getter
    private String label;
    @Getter
    private Keyword keyword;

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

    public int getGroup() {
        return keyword.ordinal();
    }

    private enum Keyword {
        GIVEN, WHEN, THEN, AND, BUT
    }
}
