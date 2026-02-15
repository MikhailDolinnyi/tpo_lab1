package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObservationTest {

    @Test
    void linksObserverAndTarget() {
        Person arthur = new Person("Артур");
        Sound sound = new Sound("Визг дудок", "Шум ветра");

        Observation o = new Observation(arthur, sound);

        assertSame(arthur, o.getObserver());
        assertSame(sound, o.getTarget());
    }
}
