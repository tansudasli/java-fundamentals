package com.core.fp;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class TestStream4 {
    static int[] values = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};
    static Integer[] valuesObj = {2, 9, 5, 0, 3, 7, 1, 4, 8, 6};

    private static void ops() {

        System.out.println("min: " + IntStream.of(values).min().getAsInt());
        System.out.println("max: " + IntStream.of(values).max().getAsInt());
        System.out.printf("average: %.2f%n", IntStream.of(values).average().getAsDouble());

        System.out.println("sum: " + IntStream.of(values).sum());
        System.out.println("sum via reduce: " + IntStream.of(values).reduce((x, y) -> x + y).getAsInt());
        System.out.println("product of values: " + IntStream.of(values).reduce((x, y) -> x * y).getAsInt());
        System.out.println("sum of squares: " + IntStream.of(values).map(x -> x * x).sum());

        System.out.println("awesome statistics" + IntStream.of(values).summaryStatistics());

        System.out.println("length: " + IntStream.of(values).sorted().toArray().length);
    }

    public static void main(String[] args) {

        System.out.println("Integer[] values: " + Arrays.stream(valuesObj).sorted().collect(Collectors.toList()));
        System.out.println("int[] values sorted: " + IntStream.of(values).sorted().mapToObj(String::valueOf).collect(Collectors.joining(" ")));


        //Arrays to com.core.example.stream, com.core.example.stream to set map or list, or toArray !!
        //ops();

        //filter greater than 4
        System.out.println("filter greater than 4: " + Arrays.stream(valuesObj).filter(x -> x > 4).sorted().collect(Collectors.toList()));

    }
}