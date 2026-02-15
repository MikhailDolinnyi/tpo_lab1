package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void isSceneObjectAndStoresName() {
        Place pavement = new Place("Мостовая");
        assertTrue(pavement instanceof SceneObject);
        assertEquals("Мостовая", pavement.getName());
    }
}
