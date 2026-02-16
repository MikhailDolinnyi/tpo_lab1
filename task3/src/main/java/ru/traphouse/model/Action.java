package ru.traphouse.model;

public class Action {

    private ActionType type;
    private SceneObject actor;   // кто делает (Person или Creature)
    private SceneObject target;  // что затронуто (Item и т.п.)
    private Place from;
    private Place to;

    public Action(ActionType type, SceneObject actor, SceneObject target, Place from, Place to) {
        this.type = type;
        this.actor = actor;
        this.target = target;
        this.from = from;
        this.to = to;
    }

    public ActionType getType() {
        return type;
    }

    public SceneObject getActor() {
        return actor;
    }

    public SceneObject getTarget() {
        return target;
    }

    public Place getFrom() {
        return from;
    }

    public Place getTo() {
        return to;
    }
}
