package ru.traphouse.datastructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Все данные хранятся только в листьях, внутренние узлы служат только для навигации.
 * Листья связаны между собой для быстрого прохода по всем элементам.
 */
public class BPlusTree<K extends Comparable<K>, V> {

    // Максимум ключей в одном узле, если больше, то узел делится пополам
    private static final int MAX_KEYS = 6;

    private Node root;

    public BPlusTree() {
        root = new LeafNode();
    }

    public void insert(K key, V value) {
        // Идем вниз по дереву и вставляем элемент
        Split result = root.insert(key, value);

        // Если корень переполнился и разделился - создаем новый корень выше
        if (result != null) {
            InternalNode newRoot = new InternalNode();
            newRoot.keys.add(result.key);
            newRoot.children.add(root);
            newRoot.children.add(result.rightNode);
            root = newRoot;
        }
    }

    public V search(K key) {
        return root.search(key);
    }

    // Возвращает все ключи в отсортированном порядке, обходя листья по цепочке
    public List<K> getKeysInOrder() {
        List<K> result = new ArrayList<>();
        Node current = root;
        while (!current.isLeaf()) {
            current = ((InternalNode) current).children.getFirst();
        }
        LeafNode leaf = (LeafNode) current;
        while (leaf != null) {
            result.addAll(leaf.keys);
            leaf = leaf.next;
        }
        return result;
    }

    /**
     * Базовый класс для узла.
     * Может быть либо внутренним (только ключи), либо листом (ключи + значения)
     */
    private abstract class Node {
        List<K> keys = new ArrayList<>();

        // Возвращает Split, если узел разделился после вставки, иначе null
        abstract Split insert(K key, V value);

        abstract V search(K key);

        abstract boolean isLeaf();
    }

    // Внутренний узел: хранит только ключи для навигации и ссылки на детей
    private class InternalNode extends Node {
        List<Node> children = new ArrayList<>();

        @Override
        Split insert(K key, V value) {
            // binarySearch возвращает индекс, если нашел или -(insertion point) - 1, если не нашел
            int index = Collections.binarySearch(keys, key);
            // Если ключ >= keys[i], идем в child[i+1]
            int childIndex = index >= 0 ? index + 1 : -index - 1;

            // Рекурсивно вставляем в нужного ребенка
            Split split = children.get(childIndex).insert(key, value);

            if (split == null) {
                return null; // Ребенок не разделился - все ок
            }

            // Ребенок разделился, теперь нужно добавить новый ключ и новую ссылку
            keys.add(childIndex, split.key);
            children.add(childIndex + 1, split.rightNode);

            // Проверяем, не переполнился ли теперь текущий узел
            if (keys.size() > MAX_KEYS) {
                return splitInternal();
            }

            return null;
        }

        private Split splitInternal() {
            // Делим узел пополам
            int mid = keys.size() / 2;
            // Средний ключ поднимается. Он не остается ни в левом, ни в правом узле
            K upKey = keys.get(mid);

            InternalNode right = new InternalNode();

            // Правый узел забирает ключи и детей после середины
            right.keys.addAll(keys.subList(mid + 1, keys.size()));
            right.children.addAll(children.subList(mid + 1, children.size()));

            // Удаляем из текущего узла все, что ушло направо, включая средний ключ
            keys.subList(mid, keys.size()).clear();
            children.subList(mid + 1, children.size()).clear();

            return new Split(upKey, right);
        }

        @Override
        V search(K key) {
            // Находим нужного ребенка и спускаемся дальше
            int index = Collections.binarySearch(keys, key);
            int childIndex = index >= 0 ? index + 1 : -index - 1;
            return children.get(childIndex).search(key);
        }

        @Override
        boolean isLeaf() {
            return false;
        }
    }

    /**
     * Листовой узел. Содержит реальные данные (ключи + значения)
     * Все листья связаны в односвязный список через next. Можно пройти по всем данным по порядку
     */
    private class LeafNode extends Node {

        List<V> values = new ArrayList<>();
        LeafNode next; // ссылка на следующий лист справа

        @Override
        Split insert(K key, V value) {
            int index = Collections.binarySearch(keys, key);

            // Если ключ уже есть, то просто обновляем значение
            if (index >= 0) {
                values.set(index, value);
                return null;
            }

            // Вставляем ключ и значение в нужную позицию, чтобы сохранить сортировку
            int insertIndex = -index - 1;

            keys.add(insertIndex, key);
            values.add(insertIndex, value);

            // Лист переполнился? Делим его
            if (keys.size() > MAX_KEYS) {
                return splitLeaf();
            }

            return null;
        }

        private Split splitLeaf() {
            // Делим данные пополам
            int mid = keys.size() / 2;

            LeafNode right = new LeafNode();

            // Правая половина забирает вторую часть ключей и значений
            right.keys.addAll(keys.subList(mid, keys.size()));
            right.values.addAll(values.subList(mid, values.size()));

            // Удаляем из текущего листа то, что ушло направо
            keys.subList(mid, keys.size()).clear();
            values.subList(mid, values.size()).clear();

            // Вставляем новый лист в цепочку: this -> right -> old_next
            right.next = this.next;
            this.next = right;

            // В отличие от внутренних узлов, первый ключ правого листа копируется наверх, т.е не удаляется
            // Это нужно, чтобы можно было найти правый лист по этому ключу
            return new Split(right.keys.getFirst(), right);
        }

        @Override
        V search(K key) {
            int index = Collections.binarySearch(keys, key);
            return index >= 0 ? values.get(index) : null;
        }

        @Override
        boolean isLeaf() {
            return true;
        }
    }

    /**
     * Результат разделения узла.
     * Возвращается родителю, чтобы он добавил новый ключ и ссылку
     */
    private class Split {
        K key;          // ключ, который нужно добавить в родителя
        Node rightNode; // новый узел справа от разделившегося

        Split(K key, Node rightNode) {
            this.key = key;
            this.rightNode = rightNode;
        }
    }

    // Для отладки выводит дерево по уровням
    public void printTree() {
        printNode(root, 0);
    }

    private void printNode(Node node, int level) {
        System.out.println("Level " + level + " " + node.keys);

        if (!node.isLeaf()) {
            InternalNode internal = (InternalNode) node;
            for (Node child : internal.children) {
                printNode(child, level + 1);
            }
        }
    }

    // Выводит все листья в порядке слева направо, используя связный список
    public void printLeaves() {
        Node current = root;
        while (!current.isLeaf()) {
            current = ((InternalNode) current).children.getFirst();
        }

        LeafNode leaf = (LeafNode) current;

        System.out.print("Leaves: ");
        while (leaf != null) {
            System.out.print(leaf.keys + " ");
            leaf = leaf.next;
        }
        System.out.println();
    }

}
