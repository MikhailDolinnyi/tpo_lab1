package ru.traphouse.model.object;

import ru.traphouse.model.event.EventReactive;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.state.ControlPanelState;
import ru.traphouse.model.event.InteractionEventType;

public class ControlPanel extends SceneObject implements EventReactive {

    private ControlPanelState state;

    public ControlPanel() {
        super("Control Panel");
        this.state = ControlPanelState.FREE;
    }

    @Override
    public void applyEvent(InteractionEvent event) {
        if (event.type() == InteractionEventType.FEET_PLACED_ON_CONTROL_PANEL) {
            state = ControlPanelState.BLOCKED_BY_FEET;
        }
    }

    public ControlPanelState getState() {
        return state;
    }
}
