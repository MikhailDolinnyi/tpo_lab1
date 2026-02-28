package ru.traphouse.model.event;

import ru.traphouse.model.object.SceneObject;

import java.util.Objects;

public record InteractionEvent(InteractionEventType type, SceneObject actor, SceneObject target) {

    public InteractionEvent(InteractionEventType type, SceneObject actor, SceneObject target) {
        this.type = Objects.requireNonNull(type, "type");
        this.actor = actor;
        this.target = target;
    }
}
