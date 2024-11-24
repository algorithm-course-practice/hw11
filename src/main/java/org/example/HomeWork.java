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
//        List<Boolean> steps = new ArrayList<>();
        // заполняем хореографию L (false)
        boolean[] steps = new boolean[n];
        // начальная длина хореографии
        int maxNumber = 1;
        int maxNumberIndex = 1;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= q; i++){
            int stepNumber = 1;
            int stepNumberIndex = 1;
            int r = Integer.parseInt(inList.get(i)) - 1;
            // меняем на противоположное значение
            if (steps[r] == false) {
                steps[r] = true;
            }  else {
                steps[r] = false;
            }
            // добавляем  j элемент и смотрим длину вокруг этого элемента. Если больше, то обновляем макс
            //смотрим вверх
            for (int j = r; j < n && j > 0; j++){
                if (steps[j] != steps[j - 1]){
                    stepNumber += 1;
                }
                else {
                    break;
                }
            }
            // 2 раз сравнение с предыдущим поэтому -1
            stepNumber -= 1;
            //смотрим вниз
            for (int j = r; j > 0 && j < n; j--){
                if (steps[j] != steps[j - 1]){
                    stepNumber += 1;
                }
                else {
                    stepNumberIndex = j + 1;
                    break;
                }
            }
            // заново просчитать предыдущий макс если мы его затронули
            maxNumber = 1;
            for (int j = maxNumberIndex; j < n && j > 0; j++){
                if (steps[j] != steps[j - 1]){
                    maxNumber += 1;
                }
                else {
                    break;
                }
            }

            if (stepNumber > maxNumber){
                maxNumber = stepNumber;
                maxNumberIndex = stepNumberIndex;
            }
            result.add(maxNumber);
        }
        String resultString = result.stream().map(String::valueOf).collect(Collectors.joining("\n"));
        byte[] bytes = resultString.getBytes(StandardCharsets.UTF_8);
        out.write(bytes);
    }



}
