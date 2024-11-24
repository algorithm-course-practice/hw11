package org.example;


import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */
    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {
        List<Integer> integerList = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8)).lines()
                .flatMap(str -> Arrays.stream(str.split(" "))).map(Integer::parseInt).collect(Collectors.toList());

        List<Integer> res = new ArrayList<>(integerList.get(2));
        AVLTree avlTree = new AVLTree();
        int maxLength = integerList.get(0) + 1;
        IntStream.range(1, maxLength).forEach(avlTree::add);
//        System.out.println(avlTree.inOrder());
        integerList.stream().skip(2).forEach(
                i -> {
                    avlTree.changeFlag(i);
//                    System.out.print("K: " + i + " -> ");
//                    System.out.println(avlTree.inOrder());
                    res.add(avlTree.calculateMaxUnicSeq());

                }
        );


        String resStr = res.stream().map(String::valueOf).collect(Collectors.joining(System.lineSeparator())) + System.lineSeparator();
        out.write(resStr.getBytes(StandardCharsets.UTF_8));
    }



}
