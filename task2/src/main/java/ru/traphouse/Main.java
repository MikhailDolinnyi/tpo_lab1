package ru.traphouse;

import ru.traphouse.datastructure.BPlusTree;

public class Main {

    public static void main(String[] args) {

        BPlusTree<Integer, String> tree = new BPlusTree<>();

        int[] values = {10, 20, 5, 6, 12, 30, 7, 17, 3, 25, 40};

        System.out.println("Inserting values:");
        for (int v : values) {
            System.out.print(v + " | ");
            tree.insert(v, "Value-" + v);
        }

        System.out.println("\n\nTree structure:");
        tree.printTree();

        tree.printLeaves();

        System.out.println("\nSearch tests:");
        System.out.println("Search 12 -> " + tree.search(12));
        System.out.println("Search 100 -> " + tree.search(100));
    }
}
