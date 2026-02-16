package ru.traphouse.model;

public abstract class SceneObject {

    protected String name;

    public SceneObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
