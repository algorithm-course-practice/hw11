package org.example;

/**
 * Узел AVL-дерева
 */
public class Node {
    int start, end; // Диапазон сегмента
    int maxLength;  // Максимальная длина чередующегося подотрезка
    boolean isUniform; // Является ли сегмент однородным
    char uniformChar;  // Символ однородного сегмента, если применимо
    Node left, right;
    int height;

    Node(int start, int end, char[] arr) {
        this.start = start;
        this.end = end;
        this.height = 1;
        if (start == end) {
            this.maxLength = 1;
            this.isUniform = true;
            this.uniformChar = arr[start];
        }
    }
}
