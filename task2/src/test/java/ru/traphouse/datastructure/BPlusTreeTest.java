package ru.traphouse.datastructure;

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

    private void insertAll(int[] keys) {
        for (int x : keys) {
            tree.insert(x, "V" + x);
        }
    }

    private void assertAllFound(int[] keys) {
        for (int x : keys) {
            assertEquals("V" + x, tree.search(x), "Key " + x + " not found");
        }
    }

    // Несколько вставок без переполнения — все элементы находятся по поиску.
    @Test
    void testInsertWithoutSplit() {
        int[] data = {10, 20, 30};

        insertAll(data);

        assertAllFound(data);
        assertEquals(List.of(10, 20, 30), tree.getKeysInOrder());
    }

    // Ровно MAX_KEYS элементов — лист заполнен до предела, все находятся.
    @Test
    void testInsertExactlyMaxKeys() {
        int[] data = {10, 20, 30, 40, 50, 60};

        insertAll(data);

        assertAllFound(data);
        assertEquals(List.of(10, 20, 30, 40, 50, 60), tree.getKeysInOrder());
    }

    // MAX_KEYS + 1 элемент: лист переполняется, дерево вырастает на уровень.
    // После сплита все элементы должны по-прежнему находиться.
    @Test
    void testLeafSplitAndRootGrowth() {
        int[] data = {10, 20, 30, 40, 50, 60, 70};

        insertAll(data);

        assertAllFound(data);
        assertEquals(List.of(10, 20, 30, 40, 50, 60, 70), tree.getKeysInOrder());
    }

    // Большой набор данных — дерево вырастает до трёх уровней.
    // Все элементы должны находиться, порядок в листьях — строго возрастающий.
    @Test
    void testInternalSplit() {
        int[] data = IntStream.rangeClosed(1, 42)
                .map(i -> i * 10)
                .toArray();

        insertAll(data);

        assertAllFound(data);

        List<Integer> expected = IntStream.rangeClosed(1, 42).map(i -> i * 10).boxed().toList();
        assertEquals(expected, tree.getKeysInOrder());
    }

    // Повторная вставка с тем же ключом — значение обновляется, лишних элементов нет.
    @Test
    void testDuplicateKeyUpdate() {
        tree.insert(10, "A");
        tree.insert(10, "B");

        assertEquals("B", tree.search(10));
        assertEquals(List.of(10), tree.getKeysInOrder());
    }

    // Поиск существующего ключа возвращает значение, несуществующего — null.
    @Test
    void testSearch() {
        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(30, "C");

        assertEquals("B", tree.search(20));
        assertNull(tree.search(100));
    }

    // Убывающий порядок вставки — дерево всё равно хранит ключи в отсортированном виде.
    @Test
    void testInsertDescendingOrder() {
        int[] data = {70, 60, 50, 40, 30, 20, 10};

        insertAll(data);

        assertAllFound(data);
        assertEquals(List.of(10, 20, 30, 40, 50, 60, 70), tree.getKeysInOrder());
    }

    // Обновление одного ключа среди нескольких — старое значение заменяется, остальные не трогаются.
    @Test
    void testUpdateExistingKey() {
        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(30, "C");
        tree.insert(20, "B_updated");

        assertEquals("B_updated", tree.search(20));
        assertEquals("A", tree.search(10));
        assertEquals("C", tree.search(30));
        assertEquals(List.of(10, 20, 30), tree.getKeysInOrder());
    }

    // Чередование вставок и обновлений — обновления не создают новых ключей.
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
        assertEquals(List.of(10, 20, 30, 40, 50), tree.getKeysInOrder());
    }

    // Случайный порядок вставки — ключи должны быть отсортированы в дереве.
    @Test
    void testRandomOrderInsert() {
        int[] data = {35, 15, 55, 25, 65, 45, 5, 75, 85};

        insertAll(data);

        assertAllFound(data);
        assertEquals(List.of(5, 15, 25, 35, 45, 55, 65, 75, 85), tree.getKeysInOrder());
    }

    // Поиск после множества сплитов — существующие ключи находятся, несуществующие возвращают null.
    @Test
    void testSearchAfterComplexModifications() {
        int[] data = IntStream.rangeClosed(1, 20)
                .map(i -> i * 5)
                .toArray();

        for (int x : data) {
            tree.insert(x, "Value_" + x);
        }

        List<Integer> expectedKeys = IntStream.rangeClosed(1, 20).map(i -> i * 5).boxed().toList();
        assertEquals(expectedKeys, tree.getKeysInOrder());

        for (int x : data) {
            assertEquals("Value_" + x, tree.search(x));
        }

        // ключи между существующими
        assertNull(tree.search(3));
        assertNull(tree.search(7));
        assertNull(tree.search(52));

        // за границами
        assertNull(tree.search(0));
        assertNull(tree.search(101));
    }

    // Поиск в пустом дереве — не должно быть исключений.
    @Test
    void testSearchInEmptyTree() {
        assertNull(tree.search(10));
        assertNull(tree.search(0));
    }

    // Один элемент — минимально возможное дерево.
    @Test
    void testSingleInsert() {
        tree.insert(42, "Answer");

        assertEquals("Answer", tree.search(42));
        assertEquals(List.of(42), tree.getKeysInOrder());
    }

    // Обновление ключей после сплита — они разбросаны по разным листьям, поиск должен работать.
    @Test
    void testUpdatesAfterSplit() {
        for (int i = 1; i <= 7; i++) {
            tree.insert(i * 10, "V" + i);
        }

        tree.insert(10, "Updated1");
        tree.insert(40, "Updated4");
        tree.insert(70, "Updated7");

        assertEquals("Updated1", tree.search(10));
        assertEquals("Updated4", tree.search(40));
        assertEquals("Updated7", tree.search(70));
        assertEquals("V2", tree.search(20));
        assertEquals(List.of(10, 20, 30, 40, 50, 60, 70), tree.getKeysInOrder());
    }
}
