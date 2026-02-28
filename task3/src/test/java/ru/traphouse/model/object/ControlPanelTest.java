package ru.traphouse.model.object;

import org.junit.jupiter.api.Test;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.event.InteractionEventType;
import ru.traphouse.model.state.ControlPanelState;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControlPanelTest {

    @Test
    void changesStateOnlyWhenFeetArePlacedOnPanel() {
        ControlPanel controlPanel = new ControlPanel();

        assertEquals("Control Panel", controlPanel.getName());

        assertEquals(ControlPanelState.FREE, controlPanel.getState());

        controlPanel.applyEvent(new InteractionEvent(InteractionEventType.TWO_HEADED_PERSON_RECLINED_IN_CHAIR, null, null));
        assertEquals(ControlPanelState.FREE, controlPanel.getState());

        controlPanel.applyEvent(new InteractionEvent(InteractionEventType.FEET_PLACED_ON_CONTROL_PANEL, null, controlPanel));
        assertEquals(ControlPanelState.BLOCKED_BY_FEET, controlPanel.getState());
    }
}
