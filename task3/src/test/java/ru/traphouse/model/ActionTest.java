package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {

    @Test
    void storesAllFields() {
        Place pavement = new Place("Мостовая");
        Place sky = new Place("Небо");
        Person ford = new Person("Форд");

        Action a = new Action(ActionType.ESCAPE, ford, null, pavement, null);

        assertEquals(ActionType.ESCAPE, a.getType());
        assertSame(ford, a.getActor());
        assertNull(a.getTarget());
        assertSame(pavement, a.getFrom());
        assertNull(a.getTo());
    }

    @Test
    void scenarioFromTextIsRepresentable() {
        Place pavement = new Place("Мостовая");
        Place sky = new Place("Небо");

        Person arthur = new Person("Артур");
        Person ford = new Person("Форд");

        Price donutPrice = new Price(10, "пенсов");
        Item donuts = new Item("Пончики", true, donutPrice);

        Creature fish = new Creature("Рыбина", true);

        Action erupt = new Action(ActionType.ERUPT, pavement, donuts, pavement, null);
        assertEquals(ActionType.ERUPT, erupt.getType());
        assertSame(pavement, erupt.getActor());
        assertSame(donuts, erupt.getTarget());
        assertSame(pavement, erupt.getFrom());

        Action dive = new Action(ActionType.DIVE, fish, null, sky, pavement);
        assertEquals(ActionType.DIVE, dive.getType());
        assertSame(fish, dive.getActor());
        assertSame(sky, dive.getFrom());
        assertSame(pavement, dive.getTo());

        Action escapeArthur = new Action(ActionType.ESCAPE, arthur, null, pavement, null);
        Action escapeFord = new Action(ActionType.ESCAPE, ford, null, pavement, null);
        assertSame(arthur, escapeArthur.getActor());
        assertSame(ford, escapeFord.getActor());
    }
}
