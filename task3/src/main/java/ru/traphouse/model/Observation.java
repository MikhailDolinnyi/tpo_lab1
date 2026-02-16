package ru.traphouse.model;

public class Observation {
    private Person observer;
    private SceneObject target;

    public Observation(Person observer, SceneObject target) {
        this.observer = observer;
        this.target = target;
    }

    public Person getObserver() {
        return observer;
    }

    public SceneObject getTarget() {
        return target;
    }
}
