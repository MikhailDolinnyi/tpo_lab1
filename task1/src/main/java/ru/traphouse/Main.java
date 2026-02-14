package ru.traphouse;


import ru.traphouse.utils.ArctanTaylorSeries;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java App <x>");
            return;
        }

        try {
            double x = Double.parseDouble(args[0]);

            double result = ArctanTaylorSeries.arctan(x);

            System.out.printf("arctg(%.6f) = %.10f%n", x, result);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
