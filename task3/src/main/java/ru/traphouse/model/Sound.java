package ru.traphouse.model;

public class Sound extends SceneObject {

    private String context; // "шум ветра"

    public Sound(String name, String context) {
        super(name);
        this.context = context;
    }

    public String getContext() {
        return context;
    }
}