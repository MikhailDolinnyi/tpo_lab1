package ru.traphouse.model.object;

import java.util.Objects;

public abstract class SceneObject {

    private final String name;

    protected SceneObject(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getName() {
        return name;
    }
}
