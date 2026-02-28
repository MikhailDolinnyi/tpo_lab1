package ru.traphouse.model.object;

import ru.traphouse.model.event.EventReactive;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.state.FeetState;
import ru.traphouse.model.state.HeadSide;
import ru.traphouse.model.state.LeftHandState;
import ru.traphouse.model.state.TwoHeadedPersonState;

public class TwoHeadedPerson extends SceneObject implements EventReactive {

    private TwoHeadedPersonState state;
    private FeetState feetState;
    private LeftHandState leftHandState;
    private final Head leftHead;
    private final Head rightHead;

    public TwoHeadedPerson() {
        super("Two-Headed Person");
        this.state = TwoHeadedPersonState.STANDING;
        this.feetState = FeetState.ON_FLOOR;
        this.leftHandState = LeftHandState.FREE;
        this.leftHead = new Head(HeadSide.LEFT);
        this.rightHead = new Head(HeadSide.RIGHT);
    }

    @Override
    public void applyEvent(InteractionEvent event) {
        switch (event.type()) {
            case TWO_HEADED_PERSON_RECLINED_IN_CHAIR -> state = TwoHeadedPersonState.LOUNGING_IN_CHAIR;
            case FEET_PLACED_ON_CONTROL_PANEL -> feetState = FeetState.ON_CONTROL_PANEL;
            case LEFT_HAND_PICKS_RIGHT_HEAD_TEETH -> leftHandState = LeftHandState.PICKING_RIGHT_HEAD_TEETH;
            default -> {

            }
        }

        leftHead.applyEvent(event);
        rightHead.applyEvent(event);
    }

    public TwoHeadedPersonState getState() {
        return state;
    }

    public FeetState getFeetState() {
        return feetState;
    }

    public LeftHandState getLeftHandState() {
        return leftHandState;
    }

    public Head getLeftHead() {
        return leftHead;
    }

    public Head getRightHead() {
        return rightHead;
    }
}
