package org.example;

/**
 * AVL-дерево
 */
public class AVLTree {
    Node root;

    AVLTree(char[] arr) {
        this.root = build(0, arr.length - 1, arr);
    }

    /**
     * Построение дерева
     * @param start
     * @param end
     * @param arr
     * @return
     */
    private Node build(int start, int end, char[] arr) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(start, end, arr);
        if (start != end) {
            node.left = build(start, mid, arr);
            node.right = build(mid + 1, end, arr);
            updateNode(node);
        }
        return node;
    }

    /**
     * Обновление значения в дереве
     * @param index
     * @param newValue
     */
    void update(int index, char newValue) {
        update(root, index, newValue);
    }

    private void update(Node node, int index, char newValue) {
        if (node.start == node.end) {
            node.isUniform = true;
            node.uniformChar = newValue;
            return;
        }
        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            update(node.left, index, newValue);
        } else {
            update(node.right, index, newValue);
        }
        updateNode(node);
    }

    /**
     * Обновление информации в узле
     * @param node
     */
    private void updateNode(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        node.isUniform = false;
        node.maxLength = Math.max(getMaxLength(node.left), getMaxLength(node.right));

        // Слияние сегментов
        if (node.left != null && node.right != null &&
                node.left.isUniform && node.right.isUniform &&
                node.left.uniformChar != node.right.uniformChar) {
            node.maxLength = Math.max(node.maxLength,
                    node.left.maxLength + node.right.maxLength);
        }
    }

    /**
     * Получение максимальной длины
     * @return
     */
    int getMaxLength() {
        return getMaxLength(root);
    }

    private int getMaxLength(Node node) {
        return node == null ? 0 : node.maxLength;
    }

    /**
     * Получение высоты узла
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }
}
