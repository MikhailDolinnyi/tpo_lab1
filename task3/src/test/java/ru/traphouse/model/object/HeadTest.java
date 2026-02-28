package ru.traphouse.model.object;

import org.junit.jupiter.api.Test;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.event.InteractionEventType;
import ru.traphouse.model.state.HeadSide;
import ru.traphouse.model.state.HeadState;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadTest {

    @Test
    void rightHeadChangesStateOnTeethPickingEventOnly() {
        Head rightHead = new Head(HeadSide.RIGHT);

        assertEquals("Right head", rightHead.getName());

        assertEquals(HeadSide.RIGHT, rightHead.getSide());
        assertEquals(HeadState.CALM, rightHead.getState());

        rightHead.applyEvent(new InteractionEvent(InteractionEventType.LEFT_HEAD_SMILED_WIDELY, null, null));
        assertEquals(HeadState.CALM, rightHead.getState());

        rightHead.applyEvent(new InteractionEvent(InteractionEventType.LEFT_HAND_PICKS_RIGHT_HEAD_TEETH, null, rightHead));
        assertEquals(HeadState.BUSY_WITH_TEETH, rightHead.getState());
    }

    @Test
    void leftHeadChangesStateOnSmileEventOnly() {
        Head leftHead = new Head(HeadSide.LEFT);

        assertEquals("Left head", leftHead.getName());

        assertEquals(HeadSide.LEFT, leftHead.getSide());
        assertEquals(HeadState.CALM, leftHead.getState());

        leftHead.applyEvent(new InteractionEvent(InteractionEventType.LEFT_HAND_PICKS_RIGHT_HEAD_TEETH, null, null));
        assertEquals(HeadState.CALM, leftHead.getState());

        leftHead.applyEvent(new InteractionEvent(InteractionEventType.LEFT_HEAD_SMILED_WIDELY, null, leftHead));
        assertEquals(HeadState.SMILING_BROADLY, leftHead.getState());
    }
}
