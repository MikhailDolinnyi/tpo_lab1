package ru.traphouse;

import ru.traphouse.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Place pavement = new Place("Мостовая");
        Place sky = new Place("Небо");

        Person arthur = new Person("Артур");
        Person ford = new Person("Форд");

        Sound pipesSqueal = new Sound("Визг дудок", "Шум ветра");

        Price donutPrice = new Price(10, "пенсов");
        Item donuts = new Item("Пончики", true, donutPrice);

        // --- Рыбина ---
        Creature fish = new Creature("Рыбина", true);

        List<Observation> observations = new ArrayList<>();
        observations.add(new Observation(arthur, pipesSqueal));
        observations.add(new Observation(ford, pipesSqueal));
        observations.add(new Observation(arthur, donuts));
        observations.add(new Observation(ford, donuts));
        observations.add(new Observation(arthur, fish));
        observations.add(new Observation(ford, fish));

        List<Action> actions = new ArrayList<>();

        actions.add(new Action(ActionType.ERUPT, pavement, donuts, pavement, null));

        actions.add(new Action(ActionType.DIVE, fish, null, sky, pavement));

        actions.add(new Action(ActionType.ESCAPE, arthur, null, pavement, null));
        actions.add(new Action(ActionType.ESCAPE, ford, null, pavement, null));
    }
}
