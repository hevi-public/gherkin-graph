package hu.hevi.gherkingraph;

import io.cucumber.messages.Messages;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Step {

    protected static int idSequence = 0;

    @Getter
    private int id;
    @Getter
    private String text;
    @Getter
    private List<Step> children = new ArrayList<>();

    public Step(Messages.Step ofStep) {
        this.text = ofStep.getKeyword() + ofStep.getText();
        this.id = Step.idSequence++;
    }

    public void addChild(Step step) {
        children.add(step);
    }
}
