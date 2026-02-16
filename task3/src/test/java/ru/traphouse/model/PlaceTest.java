package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    @Test
    void isSceneObjectAndStoresName() {
        Place pavement = new Place("Мостовая");
        assertEquals("Мостовая", pavement.getName());
    }
}
