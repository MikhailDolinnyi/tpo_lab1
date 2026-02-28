package ru.traphouse.model.object;

import ru.traphouse.model.event.EventReactive;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.state.ChairState;
import ru.traphouse.model.event.InteractionEventType;

public class Chair extends SceneObject implements EventReactive {

    private ChairState state;

    public Chair() {
        super("Chair");
        this.state = ChairState.EMPTY;
    }

    @Override
    public void applyEvent(InteractionEvent event) {
        if (event.type() == InteractionEventType.TWO_HEADED_PERSON_RECLINED_IN_CHAIR) {
            state = ChairState.OCCUPIED;
        }
    }

    public ChairState getState() {
        return state;
    }
}
