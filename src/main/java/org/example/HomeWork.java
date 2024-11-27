package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */
    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {
        Scanner sc = new Scanner(in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = 'L';
        }

        int choreographyValue = 1;
        int[] diffs = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diffs[i] = (chars[i] != chars[i + 1]) ? 1 : 0;
            choreographyValue += diffs[i];
        }

        StringBuilder output = new StringBuilder();

        for (int k = 0; k < q; k++) {
            int pos = sc.nextInt() - 1;

            chars[pos] = (chars[pos] == 'L') ? 'R' : 'L';

            if (pos > 0) {
                int oldDiff = diffs[pos - 1];
                diffs[pos - 1] = (chars[pos - 1] != chars[pos]) ? 1 : 0;
                choreographyValue += diffs[pos - 1] - oldDiff;
            }
            if (pos < n - 1) {
                int oldDiff = diffs[pos];
                diffs[pos] = (chars[pos] != chars[pos + 1]) ? 1 : 0;
                choreographyValue += diffs[pos] - oldDiff;
            }

            output.append(choreographyValue);
            if (k < q - 1) {
                output.append("\n");
            }
        }

        out.write(output.toString().replaceAll("\n", "").replaceAll("\r", "").getBytes());
    }

}
