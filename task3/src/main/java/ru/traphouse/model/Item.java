package ru.traphouse.model;

public class Item extends SceneObject {

    private boolean hot;
    private Price price;

    public Item(String name, boolean hot, Price price) {
        super(name);
        this.hot = hot;
        this.price = price;
    }

    public boolean isHot() {
        return hot;
    }

    public Price getPrice() {
        return price;
    }
}
