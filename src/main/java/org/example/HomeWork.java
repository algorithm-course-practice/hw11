package org.example;


import lombok.SneakyThrows;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */

    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))){
            List<Integer> params = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());

           Heap heap = new Heap(params.get(0));
           heap.init(1, 0, Heap.MXN);

            int switchPos = -1;
            for (int i = 0; i < params.get(1); i++) {
                switchPos = Integer.parseInt(reader.readLine());
                int maxUniq = heap.change(switchPos);
                writer.write(maxUniq + "\n");
            }
            writer.flush();
        }
    }



}
