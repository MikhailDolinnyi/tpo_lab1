package ru.traphouse.model.object;

import ru.traphouse.model.event.EventReactive;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.state.ArthurState;
import ru.traphouse.model.state.JawState;

public class Arthur extends SceneObject implements EventReactive {

    private ArthurState state;
    private JawState jawState;
    private int unbelievableThingsSeen;

    public Arthur() {
        super("Arthur");
        this.state = ArthurState.NERVOUS;
        this.jawState = JawState.NORMAL;
        this.unbelievableThingsSeen = 0;
    }

    @Override
    public void applyEvent(InteractionEvent event) {
        switch (event.type()) {
            case ARTHUR_SAW_TWO_HEADED_PERSON -> state = ArthurState.SHOCKED;
            case ARTHUR_NOTICED_ANOTHER_UNBELIEVABLE_THING -> {
                unbelievableThingsSeen++;
                state = ArthurState.DISBELIEF_GROWING;
            }
            case ARTHUR_JAW_DROPPED -> {
                jawState = JawState.DROPPED;
                state = ArthurState.JAW_DROPPED;
            }
            default -> {

            }
        }
    }

    public ArthurState getState() {
        return state;
    }

    public JawState getJawState() {
        return jawState;
    }

    public int getUnbelievableThingsSeen() {
        return unbelievableThingsSeen;
    }
}
