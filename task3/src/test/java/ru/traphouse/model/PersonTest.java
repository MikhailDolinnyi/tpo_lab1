package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void storesName() {
        Person arthur = new Person("Артур");
        assertEquals("Артур", arthur.getName());
    }

    @Test
    void escapingStateChangesCorrectly() {
        Person arthur = new Person("Артур");

        assertFalse(arthur.isEscaping());

        arthur.startEscape();
        assertTrue(arthur.isEscaping());
    }
}
