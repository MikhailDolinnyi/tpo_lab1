package ru.traphouse.model;


public class Person extends SceneObject {

    private boolean escaping;

    public Person(String name) {
        super(name);
        this.escaping = false;
    }

    public boolean isEscaping() {
        return escaping;
    }

    public void startEscape() {
        this.escaping = true;
    }
}
