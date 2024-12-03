package org.example;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class HomeWork {

    private static final boolean DIRTY_ALGORITHM = true;

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */
    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {

        Scanner scanner = new Scanner(in);
        StringBuilder result = new StringBuilder();

        // Считываем длину строки и количество изменений
        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        if (DIRTY_ALGORITHM) {
            dirtyAlgorithm(N, Q, scanner, result);
        } else {
            avlTreeAlgorithm(N, Q, scanner, result);
        }

        // Записываем результат
        out.write(result.toString().getBytes());
        out.flush();
    }

    private void avlTreeAlgorithm(int N, int Q, Scanner scanner, StringBuilder result) {
        // Изначально вся строка состоит из 'L'
        char[] choreography = new char[N];
        Arrays.fill(choreography, 'L');

        // Построение AVL-дерева
        AVLTree tree = new AVLTree(choreography);

        // Выполнение операций и запись результатов
        for (int i = 0; i < Q; i++) {
            int pos = scanner.nextInt() - 1; // Индексация с 0
            tree.update(pos, choreography[pos] == 'L' ? 'R' : 'L');
            choreography[pos] = choreography[pos] == 'L' ? 'R' : 'L';
            result.append(tree.getMaxLength()).append('\n');
        }
    }

    private void dirtyAlgorithm(int N, int Q, Scanner scanner, StringBuilder result) {
        // Изначально вся строка состоит из 'L'
        char[] choreography = new char[N];
        Arrays.fill(choreography, 'L');

        // Выполняем модификации
        for (int i = 0; i < Q; i++) {
            int pos = scanner.nextInt() - 1; // Приведение к нулевой индексации
            if (pos < 0 || pos >= N) {
                throw new IllegalArgumentException("Invalid position: " + (pos + 1));
            }

            // Изменяем символ
            choreography[pos] = (choreography[pos] == 'L') ? 'R' : 'L';

            // Подсчитываем максимальную длину
            result.append(calculateMaxLength(choreography)).append('\n');
        }
    }

    private int calculateMaxLength(char[] choreography) {
        int maxLen = 1;
        int currentLen = 1;

        for (int i = 1; i < choreography.length; i++) {
            if (choreography[i] != choreography[i - 1]) {
                currentLen++;
            } else {
                maxLen = Math.max(maxLen, currentLen);
                currentLen = 1;
            }
        }

        return Math.max(maxLen, currentLen);
    }

}
