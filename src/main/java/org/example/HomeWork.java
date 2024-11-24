package org.example;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */
    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {
        Scanner scanner = new Scanner(in);
        PrintWriter writer = new PrintWriter(out);

        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        char[] choreography = new char[N];
        Arrays.fill(choreography, 'L');

        for (int i = 0; i < Q; i++) {
            int position = scanner.nextInt() - 1;

            choreography[position] = (choreography[position] == 'L') ? 'R' : 'L';

            int longestValue = 1;
            for (int j = 1; j < N; j++) {
                if (choreography[j] != choreography[j - 1]) {
                    longestValue++;
                }
            }

            writer.print(longestValue);
            if (i + 1 < Q) {
                writer.println();
            }
        }

        writer.flush();
        scanner.close();
    }
}
