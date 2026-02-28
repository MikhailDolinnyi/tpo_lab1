package ru.traphouse.model.object;

import org.junit.jupiter.api.Test;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.state.ArthurState;
import ru.traphouse.model.event.InteractionEventType;
import ru.traphouse.model.state.JawState;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArthurTest {

    @Test
    void changesStateOnEvents() {
        Arthur arthur = new Arthur();

        assertEquals("Arthur", arthur.getName());

        assertEquals(ArthurState.NERVOUS, arthur.getState());
        assertEquals(JawState.NORMAL, arthur.getJawState());
        assertEquals(0, arthur.getUnbelievableThingsSeen());

        arthur.applyEvent(new InteractionEvent(InteractionEventType.ARTHUR_ENTERED_FOLLOWING, arthur, null));
        assertEquals(ArthurState.NERVOUS, arthur.getState());

        arthur.applyEvent(new InteractionEvent(InteractionEventType.ARTHUR_SAW_TWO_HEADED_PERSON, arthur, null));
        assertEquals(ArthurState.SHOCKED, arthur.getState());

        arthur.applyEvent(new InteractionEvent(
            InteractionEventType.ARTHUR_NOTICED_ANOTHER_UNBELIEVABLE_THING,
            arthur,
            null
        ));
        assertEquals(ArthurState.DISBELIEF_GROWING, arthur.getState());
        assertEquals(1, arthur.getUnbelievableThingsSeen());

        arthur.applyEvent(new InteractionEvent(InteractionEventType.ARTHUR_JAW_DROPPED, arthur, null));
        assertEquals(ArthurState.JAW_DROPPED, arthur.getState());
        assertEquals(JawState.DROPPED, arthur.getJawState());
    }
}
