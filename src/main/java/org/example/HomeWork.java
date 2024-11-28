package org.example;


import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        String input = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        List<String> inList = Arrays.stream(input.split("\n"))
                .collect(Collectors.toList());
        String firstLine  = inList.get(0);
        List<Integer> firstLineListInt = Arrays.stream(firstLine.split(" "))
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        int n = firstLineListInt.get(0);
        if (n < 1 || n > 200000){
            new IllegalArgumentException("Не корректный N");
        }
        int q = firstLineListInt.get(1);
        if (q < 1 || q > 200000){
            new IllegalArgumentException("Не корректный Q");
        }
        List<Integer> secondLine = Arrays.stream(inList.get(1).split(" "))
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        // создаем и заполняем хореографию L (false)
        boolean[] steps = new boolean[n];
        // начальная длина хореографии
        int maxNumber = 1;
        int maxNumberIndex = 1;
        List<Integer> result = new ArrayList<>();
        // импровизация танцоров
        for (int i = 1; i <= q; i++){
            int stepNumber = 1;
            int stepNumberIndex = 1;
            int r = Integer.parseInt(inList.get(i)) - 1;
            // меняем на противоположное значение
                steps[r] = !steps[r];
            // меняем  r элемент и смотрим длину вокруг этого элемента. Если больше, то обновляем макс
            // 1) идем вверх по индексу пока не кончится последовательность
            for (int j = r; j < n  - 1 && j > 0; j++){
                if (steps[j] != steps[j +1]){
                    stepNumber += 1;
                }
                else {
                    break;
                }
            }
            //2) идем вниз по индексу пока не кончится последовательность
            for (int j = r; j > 0 && j < n - 1; j--){
                if (steps[j] != steps[j - 1]){
                    stepNumber += 1;
                }
                else {
                    stepNumberIndex = j;
                    break;
                }
            }
            // если новый индекс входит в диапозон [maxNumberIndex, maxNumberIndex + maxNumber]
            if (maxNumberIndex <= r && r <= maxNumberIndex + Math.max(maxNumber, stepNumber)) {
                if (stepNumber > maxNumber) {
                    // нашли больше длину на этом участке и поэтому обновляем максимум
                    maxNumber = stepNumber;
                    maxNumberIndex = stepNumberIndex;
                } else {
                    // вставка испортила диапозон maxNumber и поэтому сканируем весь диапозон заново
                    // начальное значение для макса из диапозона maxNumberIndex (для оптимизации)
                    maxNumber = stepNumber;
                    maxNumberIndex = stepNumberIndex;
                    stepNumber = 1;
                    // уменьшаем индекс чтобы найти начало диапозона
                    for (int j = n - 1; j > 0; j--) {
                        if (steps[j] != steps[j - 1]) {
                            stepNumber += 1;
                        } else {
                            if (stepNumber > maxNumber) {
                                maxNumber = stepNumber;
                                maxNumberIndex = j;
                            }
                            stepNumber = 1;
                        }
                    }
                    if (stepNumber > maxNumber) {
                        maxNumber = stepNumber;
                    }
                }
            }
            else {
                if (stepNumber > maxNumber) {
                    // нашли больше длину на этом участке и поэтому обновляем максимум
                    maxNumber = stepNumber;
                    maxNumberIndex = stepNumberIndex;
                }
            }
            result.add(maxNumber);
        }
        String resultString = result.stream().map(String::valueOf).collect(Collectors.joining("\n")) + "\n";
        byte[] bytes = resultString.getBytes(StandardCharsets.UTF_8);
        out.write(bytes);
    }
}
