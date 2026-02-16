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

        System.out.println("Сцена");
        System.out.println("Места:" + pavement.getName() + ", " + sky.getName());
        System.out.println("Персонажи:" + arthur.getName() + ", " + ford.getName());

        System.out.println("\nЗвук");
        System.out.println(pipesSqueal.getName() + " (контекст: " + pipesSqueal.getContext() + ")");

        System.out.println("\nПончики");
        System.out.println(donuts.getName() + " | горячие=" + donuts.isHot()
                + " | цена=" + donuts.getPrice().getAmount() + " " + donuts.getPrice().getCurrency() + " за штуку");

        System.out.println("\nНаблюдения");
        for (Observation o : observations) {
            System.out.println(o.getObserver().getName() + " наблюдает: " + o.getTarget().getName());
        }

        System.out.println("\nДействия");
        for (Action a : actions) {
            String actor = a.getActor() == null ? "?" : a.getActor().getName();
            String target = a.getTarget() == null ? "-" : a.getTarget().getName();
            String from = a.getFrom() == null ? "-" : a.getFrom().getName();
            String to = a.getTo() == null ? "-" : a.getTo().getName();

            System.out.println(a.getType() + " | actor=" + actor + " | target=" + target + " | from=" + from + " | to=" + to);
        }
    }
}
