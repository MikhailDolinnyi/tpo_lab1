package ru.traphouse.model.object;

import org.junit.jupiter.api.Test;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.event.InteractionEventType;
import ru.traphouse.model.state.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoHeadedPersonTest {

    @Test
    void changesBodyLimbAndHeadStatesOnEvents() {
        TwoHeadedPerson person = new TwoHeadedPerson();

        assertEquals("Two-Headed Person", person.getName());

        assertEquals(TwoHeadedPersonState.STANDING, person.getState());
        assertEquals(FeetState.ON_FLOOR, person.getFeetState());
        assertEquals(LeftHandState.FREE, person.getLeftHandState());
        assertEquals(HeadState.CALM, person.getLeftHead().getState());
        assertEquals(HeadState.CALM, person.getRightHead().getState());

        person.applyEvent(new InteractionEvent(InteractionEventType.TWO_HEADED_PERSON_RECLINED_IN_CHAIR, person, null));
        person.applyEvent(new InteractionEvent(InteractionEventType.FEET_PLACED_ON_CONTROL_PANEL, person, null));
        person.applyEvent(new InteractionEvent(InteractionEventType.LEFT_HAND_PICKS_RIGHT_HEAD_TEETH, person, person.getRightHead()));
        person.applyEvent(new InteractionEvent(InteractionEventType.LEFT_HEAD_SMILED_WIDELY, person, person.getLeftHead()));

        assertEquals(TwoHeadedPersonState.LOUNGING_IN_CHAIR, person.getState());
        assertEquals(FeetState.ON_CONTROL_PANEL, person.getFeetState());
        assertEquals(LeftHandState.PICKING_RIGHT_HEAD_TEETH, person.getLeftHandState());
        assertEquals(HeadState.SMILING_BROADLY, person.getLeftHead().getState());
        assertEquals(HeadState.BUSY_WITH_TEETH, person.getRightHead().getState());
    }
}
