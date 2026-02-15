package ru.traphouse.model;

public class Creature extends SceneObject {

    private boolean dangerous;

    public Creature(String name, boolean dangerous) {
        super(name);
        this.dangerous = dangerous;
    }

    public boolean isDangerous() {
        return dangerous;
    }
}
