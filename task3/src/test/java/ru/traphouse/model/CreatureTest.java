package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    @Test
    void storesDangerousFlag() {
        Creature fish = new Creature("Рыбина", true);
        assertEquals("Рыбина", fish.getName());
        assertTrue(fish.isDangerous());
    }
}
