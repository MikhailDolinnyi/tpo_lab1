package ru.traphouse.utils;

public class ArctanTaylorSeries {

    private static final double EPS = 1e-10;
    private static final int MAX_ITERATIONS = 1_000_000;

    /**
     * @param x аргумент
     * @return значение arctg(x)
     */
    public static double arctan(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("Invalid input value");
        }

        if (Math.abs(x) > 1) {
            if (x > 0) {
                return Math.PI / 2 - arctan(1 / x);
            } else {
                return -Math.PI / 2 - arctan(1 / x);
            }
        }

        double result = 0.0;
        double term = x;
        int n = 0;

        while (Math.abs(term) > EPS && n < MAX_ITERATIONS) {
            result += term;
            n++;

            term = -term * x * x * (2 * n - 1) / (2 * n + 1);
        }

        return result;
    }
}
