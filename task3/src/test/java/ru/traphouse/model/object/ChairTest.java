package ru.traphouse.model.object;

import org.junit.jupiter.api.Test;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.event.InteractionEventType;
import ru.traphouse.model.state.ChairState;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChairTest {

    @Test
    void changesStateOnlyWhenTwoHeadedPersonReclines() {
        Chair chair = new Chair();

        assertEquals("Chair", chair.getName());

        assertEquals(ChairState.EMPTY, chair.getState());

        chair.applyEvent(new InteractionEvent(InteractionEventType.ARTHUR_SAW_TWO_HEADED_PERSON, null, null));
        assertEquals(ChairState.EMPTY, chair.getState());

        chair.applyEvent(new InteractionEvent(InteractionEventType.TWO_HEADED_PERSON_RECLINED_IN_CHAIR, null, chair));
        assertEquals(ChairState.OCCUPIED, chair.getState());
    }
}
