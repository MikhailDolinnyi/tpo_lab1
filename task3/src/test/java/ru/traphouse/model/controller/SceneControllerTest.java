package ru.traphouse.model.controller;

import org.junit.jupiter.api.Test;
import ru.traphouse.model.object.Arthur;
import ru.traphouse.model.object.TwoHeadedPerson;
import ru.traphouse.model.state.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SceneControllerTest {

    @Test
    void reproducesNarrativeStateTransitions() {
        SceneController scene = SceneController.defaultScene();

        scene.playTextEpisode();

        Arthur arthur = scene.getArthur();
        TwoHeadedPerson person = scene.getTwoHeadedPerson();

        assertEquals(ArthurState.JAW_DROPPED, arthur.getState());
        assertEquals(JawState.DROPPED, arthur.getJawState());
        assertEquals(2, arthur.getUnbelievableThingsSeen());

        assertEquals(TwoHeadedPersonState.LOUNGING_IN_CHAIR, person.getState());
        assertEquals(FeetState.ON_CONTROL_PANEL, person.getFeetState());
        assertEquals(LeftHandState.PICKING_RIGHT_HEAD_TEETH, person.getLeftHandState());
        assertEquals(HeadState.SMILING_BROADLY, person.getLeftHead().getState());
        assertEquals(HeadState.BUSY_WITH_TEETH, person.getRightHead().getState());

        assertEquals(ChairState.OCCUPIED, scene.getChair().getState());
        assertEquals(ControlPanelState.BLOCKED_BY_FEET, scene.getControlPanel().getState());
        assertEquals(9, scene.getEventLog().size());
    }
}
