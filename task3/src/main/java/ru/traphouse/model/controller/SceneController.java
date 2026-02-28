package ru.traphouse.model.controller;

import ru.traphouse.model.event.EventReactive;
import ru.traphouse.model.event.InteractionEvent;
import ru.traphouse.model.event.InteractionEventType;
import ru.traphouse.model.object.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneController {

    private final Arthur arthur;
    private final TwoHeadedPerson twoHeadedPerson;
    private final Chair chair;
    private final ControlPanel controlPanel;
    private final List<InteractionEvent> eventLog;
    private final List<EventReactive> eventReactives;

    public SceneController(Arthur arthur, TwoHeadedPerson twoHeadedPerson, Chair chair, ControlPanel controlPanel) {
        this.arthur = arthur;
        this.twoHeadedPerson = twoHeadedPerson;
        this.chair = chair;
        this.controlPanel = controlPanel;
        this.eventLog = new ArrayList<>();
        this.eventReactives = List.of(arthur, twoHeadedPerson, chair, controlPanel);
    }

    public static SceneController defaultScene() {
        Arthur arthur = new Arthur();
        TwoHeadedPerson twoHeadedPerson = new TwoHeadedPerson();
        Chair chair = new Chair();
        ControlPanel controlPanel = new ControlPanel();
        return new SceneController(arthur, twoHeadedPerson, chair, controlPanel);
    }

    public void playTextEpisode() {
        emit(InteractionEventType.ARTHUR_ENTERED_FOLLOWING, arthur, null);
        emit(InteractionEventType.TWO_HEADED_PERSON_RECLINED_IN_CHAIR, twoHeadedPerson, chair);
        emit(InteractionEventType.FEET_PLACED_ON_CONTROL_PANEL, twoHeadedPerson, controlPanel);
        emit(InteractionEventType.LEFT_HAND_PICKS_RIGHT_HEAD_TEETH, twoHeadedPerson, twoHeadedPerson.getRightHead());
        emit(InteractionEventType.LEFT_HEAD_SMILED_WIDELY, twoHeadedPerson, twoHeadedPerson.getLeftHead());
        emit(InteractionEventType.ARTHUR_SAW_TWO_HEADED_PERSON, arthur, twoHeadedPerson);
        emit(InteractionEventType.ARTHUR_NOTICED_ANOTHER_UNBELIEVABLE_THING, arthur, twoHeadedPerson);
        emit(InteractionEventType.ARTHUR_NOTICED_ANOTHER_UNBELIEVABLE_THING, arthur, twoHeadedPerson);
        emit(InteractionEventType.ARTHUR_JAW_DROPPED, arthur, null);
    }

    public void emit(InteractionEventType type, SceneObject actor, SceneObject target) {
        InteractionEvent event = new InteractionEvent(type, actor, target);
        eventLog.add(event);
        for (EventReactive eventReactive : eventReactives) {
            eventReactive.applyEvent(event);
        }
    }

    public Arthur getArthur() {
        return arthur;
    }

    public TwoHeadedPerson getTwoHeadedPerson() {
        return twoHeadedPerson;
    }

    public Chair getChair() {
        return chair;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public List<InteractionEvent> getEventLog() {
        return Collections.unmodifiableList(eventLog);
    }
}
