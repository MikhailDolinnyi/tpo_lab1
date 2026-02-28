package ru.traphouse.model.object;

import ru.traphouse.model.event.EventReactive;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.state.HeadSide;
import ru.traphouse.model.state.HeadState;

public class Head extends SceneObject implements EventReactive {

    private final HeadSide side;
    private HeadState state;

    public Head(HeadSide side) {
        super(side == HeadSide.LEFT ? "Left head" : "Right head");
        this.side = side;
        this.state = HeadState.CALM;
    }

    @Override
    public void applyEvent(InteractionEvent event) {
        switch (event.type()) {
            case LEFT_HAND_PICKS_RIGHT_HEAD_TEETH -> {
                if (side == HeadSide.RIGHT) {
                    state = HeadState.BUSY_WITH_TEETH;
                }
            }
            case LEFT_HEAD_SMILED_WIDELY -> {
                if (side == HeadSide.LEFT) {
                    state = HeadState.SMILING_BROADLY;
                }
            }
            default -> {

            }
        }
    }

    public HeadSide getSide() {
        return side;
    }

    public HeadState getState() {
        return state;
    }
}
