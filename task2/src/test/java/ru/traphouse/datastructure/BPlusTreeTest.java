package ru.traphouse.datastructure;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BPlusTreeTest {

    private BPlusTree<Integer, String> tree;

    @BeforeEach
    void setup() {
        tree = new BPlusTree<>();
    }

    // Вставка до достижения MAX_KEYS без разбиения.
    @Test
    void testInsertWithoutSplit() {

        int[] data = {10, 20, 30, 40, 50, 60};

        for (int x : data) {
            tree.insert(x, "V");
        }

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1"
        );

        assertEquals(expected, tree.getTrace());
    }

    // Разбиение листа и рост дерева при вставке 7-го элемента.
    @Test
    void testLeafSplitAndRootGrowth() {

        int[] data = {10, 20, 30, 40, 50, 60, 70};

        for (int x : data) {
            tree.insert(x, "V");
        }

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1",
                "T1", "T2", "T5"
        );

        assertEquals(expected, tree.getTrace());
    }

    // Полная проверка последовательности при переполнении внутреннего узла и росте дерева.
    @Test
    void testInternalSplitStrict() {

        int[] data = IntStream.rangeClosed(1, 42)
                .map(i -> i * 10)
                .toArray();

        Arrays.stream(data)
                .forEach(x -> tree.insert(x, "V"));

        List<String> expected = List.of(
                // начальная фаза
                "T1", "T1", "T1", "T1", "T1", "T1", "T1",
                "T2", "T5",

                // регулярные сплиты листьев
                "T3", "T1", "T3", "T1", "T3", "T1", "T2",
                "T3", "T1", "T3", "T1", "T3", "T1", "T2",
                "T3", "T1", "T3", "T1", "T3", "T1", "T2",
                "T3", "T1", "T3", "T1", "T3", "T1", "T2",
                "T3", "T1", "T3", "T1", "T3", "T1", "T2",
                "T3", "T1", "T3", "T1", "T3", "T1", "T2",

                // рост уровня
                "T4", "T5",

                // стабильная фаза
                "T3", "T3", "T1",
                "T3", "T3", "T1",
                "T3", "T3", "T1", "T2",
                "T3", "T3", "T1",
                "T3", "T3", "T1",
                "T3", "T3", "T1", "T2",
                "T3", "T3", "T1",
                "T3", "T3", "T1",
                "T3", "T3", "T1", "T2",
                "T3", "T3", "T1",
                "T3", "T3", "T1",
                "T3", "T3", "T1", "T2",

                // финальный сплит
                "T4",

                // финальная стабилизация
                "T3", "T3", "T1",
                "T3", "T3", "T1",
                "T3", "T3", "T1", "T2",
                "T3", "T3", "T1",
                "T3", "T3", "T1"
        );

        assertEquals(expected, tree.getTrace());
    }


    // Обновление значения существующего ключа.
    @Test
    void testDuplicateKeyUpdate() {

        tree.insert(10, "A");
        tree.insert(10, "B");

        assertEquals("B", tree.search(10));
    }

    // Поиск существующего и отсутствующего ключа.
    @Test
    void testSearch() {

        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(30, "C");

        assertEquals("B", tree.search(20));
        assertNull(tree.search(100));
    }

    // Вставка в обратном порядке
    @Test
    void testInsertDescendingOrder() {

        int[] data = {70, 60, 50, 40, 30, 20, 10};

        for (int x : data) {
            tree.insert(x, "V" + x);
        }

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1",
                "T1", "T2", "T5"
        );

        assertEquals(expected, tree.getTrace());

        assertEquals("V70", tree.search(70));
        assertEquals("V40", tree.search(40));
        assertEquals("V10", tree.search(10));
    }

    // Обновление существующих ключей с проверкой trace
    @Test
    void testUpdateExistingKeysTrace() {

        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(30, "C");

        tree.insert(20, "B_updated");

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1"
        );

        assertEquals(expected, tree.getTrace());
        assertEquals("B_updated", tree.search(20));
    }

    // Смешанная вставка с обновлениями в процессе
    @Test
    void testMixedInsertAndUpdate() {

        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(10, "A2"); // обновление
        tree.insert(30, "C");
        tree.insert(20, "B2"); // обновление
        tree.insert(40, "D");
        tree.insert(50, "E");

        assertEquals("A2", tree.search(10));
        assertEquals("B2", tree.search(20));
        assertEquals("C", tree.search(30));
        assertEquals("D", tree.search(40));
        assertEquals("E", tree.search(50));

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1", "T1"
        );

        assertEquals(expected, tree.getTrace());
    }

    // Вставка в случайном порядке
    @Test
    void testRandomOrderInsert() {

        int[] data = {35, 15, 55, 25, 65, 45, 5, 75, 85};

        for (int x : data) {
            tree.insert(x, "V" + x);
        }

        // 6 вставок, 7-я вызывает split листа и рост дерева
        // затем 8-я и 9-я проходят через внутренний узел
        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1",
                "T1", "T2", "T5",
                "T3", "T1",
                "T3", "T1"
        );

        assertEquals(expected, tree.getTrace());

        for (int x : data) {
            assertEquals("V" + x, tree.search(x));
        }
    }

    // Поиск после множественных сплитов и модификаций
    @Test
    void testSearchAfterComplexModifications() {

        int[] data = IntStream.rangeClosed(1, 20)
                .map(i -> i * 5)
                .toArray();

        for (int x : data) {
            tree.insert(x, "Value_" + x);
        }

        for (int x : data) {
            assertEquals("Value_" + x, tree.search(x),
                    "Элемент " + x + " должен быть найден");
        }

        // Проверяем поиск несуществующих элементов между существующими
        assertNull(tree.search(3));
        assertNull(tree.search(7));
        assertNull(tree.search(52));
        assertNull(tree.search(103));

        // Проверяем границы
        assertNull(tree.search(0));
        assertNull(tree.search(101));
    }

    // Корнер кейс: поиск в пустом дереве
    @Test
    void testSearchInEmptyTree() {
        assertNull(tree.search(10));
        assertNull(tree.search(0));
    }

    // Корнер кейс: вставка одного элемента
    @Test
    void testSingleInsert() {
        tree.insert(42, "Answer");

        List<String> expected = List.of("T1");
        assertEquals(expected, tree.getTrace());
        assertEquals("Answer", tree.search(42));
    }

    // Корнер кейс: вставка ровно MAX_KEYS элементов
    @Test
    void testInsertExactlyMaxKeys() {
        int[] data = {10, 20, 30, 40, 50, 60};

        for (int x : data) {
            tree.insert(x, "V" + x);
        }

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1"
        );

        assertEquals(expected, tree.getTrace());

        for (int x : data) {
            assertEquals("V" + x, tree.search(x));
        }
    }

    // Вставка с повторяющимися ключами на разных этапах
    @Test
    void testRepeatedUpdatesAtDifferentStages() {
        // Вставляем 7 элементов
        for (int i = 1; i <= 7; i++) {
            tree.insert(i * 10, "V" + i);
        }

        tree.insert(10, "Updated1");
        tree.insert(40, "Updated4");
        tree.insert(70, "Updated7");

        List<String> expected = List.of(
                "T1", "T1", "T1", "T1", "T1", "T1",
                "T1", "T2", "T5",
                "T3", "T1", "T3", "T1", "T3", "T1"
        );

        assertEquals(expected, tree.getTrace());

        assertEquals("Updated1", tree.search(10));
        assertEquals("Updated4", tree.search(40));
        assertEquals("Updated7", tree.search(70));
        assertEquals("V2", tree.search(20));
    }
}
