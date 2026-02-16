package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void storesAmountAndCurrency() {
        Price p = new Price(10, "пенсов");
        assertEquals(10, p.getAmount());
        assertEquals("пенсов", p.getCurrency());
    }
}
