package ru.traphouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundTest {

    @Test
    void storesNameAndContext() {
        Sound s = new Sound("Визг дудок", "Шум ветра");
        assertEquals("Визг дудок", s.getName());
        assertEquals("Шум ветра", s.getContext());
    }
}
