package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void storesHotAndPrice() {
        Price price = new Price(10, "пенсов");
        Item donuts = new Item("Пончики", true, price);

        assertEquals("Пончики", donuts.getName());
        assertTrue(donuts.isHot());
        assertSame(price, donuts.getPrice());
    }
}
