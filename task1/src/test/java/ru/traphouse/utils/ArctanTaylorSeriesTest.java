package ru.traphouse.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArctanTaylorSeriesTest {

    private static final double DELTA = 1e-6;


    /**
     * Проверка значения при x = 0.
     */
    @Test
    void testZero() {
        assertEquals(0.0, ArctanTaylorSeries.arctan(0), DELTA);
    }

    /**
     * Проверка сходимости при 0 < |x| < 1.
     */
    @Test
    void testSmallPositive() {
        assertEquals(Math.atan(0.1), ArctanTaylorSeries.arctan(0.1), DELTA);
    }

    /**
     * Проверка корректности знака при отрицательном |x| < 1.
     */
    @Test
    void testSmallNegative() {
        assertEquals(Math.atan(-0.1), ArctanTaylorSeries.arctan(-0.1), DELTA);
    }

    /**
     * Проверка граничного значения x = 1.
     */
    @Test
    void testBoundaryOne() {
        assertEquals(Math.atan(1), ArctanTaylorSeries.arctan(1), DELTA);
    }

    /**
     * Проверка граничного значения x = -1.
     */
    @Test
    void testBoundaryMinusOne() {
        assertEquals(Math.atan(-1), ArctanTaylorSeries.arctan(-1), DELTA);
    }

    /**
     * Проверка ветви преобразования при x > 1.
     */
    @Test
    void testGreaterThanOne() {
        assertEquals(Math.atan(2), ArctanTaylorSeries.arctan(2), DELTA);
    }

    /**
     * Проверка ветви преобразования при x < -1.
     */
    @Test
    void testLessThanMinusOne() {
        assertEquals(Math.atan(-2), ArctanTaylorSeries.arctan(-2), DELTA);
    }

    /**
     * Проверка корректности вычисления при большом x.
     */
    @Test
    void testLargeValue() {
        assertEquals(Math.atan(100), ArctanTaylorSeries.arctan(100), DELTA);
    }

    /**
     * Проверка выброса исключения для NaN.
     */
    @Test
    void testNaN() {
        assertThrows(IllegalArgumentException.class,
                () -> ArctanTaylorSeries.arctan(Double.NaN));
    }

    /**
     * Проверка выброса исключения для бесконечности.
     */
    @Test
    void testInfinity() {
        assertThrows(IllegalArgumentException.class,
                () -> ArctanTaylorSeries.arctan(Double.POSITIVE_INFINITY));
    }
}
