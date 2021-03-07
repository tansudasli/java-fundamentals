package com.core.fp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class Stats {

    static int[] v = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};
    static Integer[] vO = {2, 9, 5, 0, 3, 7, 1, 4, 8, 6};
    List<Integer> vL = Arrays.asList(vO);


    public static void main(String[] args) {
        /*
         *  To understand stream well, we need to understand
         * - stream() or Arrays.stream()
         * - Optional or Optional.of() ....
         */

//        stream(v).filter(e -> e > 5).forEach(System.out::print);

        //defining OOP func. vs predicate func.
        IntPredicate greaterThanFIVE = e -> e > 5;

        Arrays.stream(v)
                .filter(greaterThanFIVE)            //increase clarity w/ predicates
                .forEach(System.out::println);      //lambda vs ::

        //stream and Optional interference
        Arrays.stream(v)
                .filter(greaterThanFIVE)
                .max()                               //switching to Optional
                .ifPresent((e) -> System.out.println("Max: " + e));



//        System.out.println("min: " + IntStream.of(v).min().isPresent());
//        System.out.println("max: " + IntStream.of(v).max().isPresent());
//        System.out.printf("average: %.2f%n", IntStream.of(v).average().getAsDouble());
//
//        System.out.println("sum: " + IntStream.of(v).sum());
//        System.out.println("sum via reduce: " + IntStream.of(v).reduce((x, y) -> x + y).getAsInt());
//        System.out.println("product of values: " + IntStream.of(v).reduce((x, y) -> x * y).getAsInt());
//        System.out.println("sum of squares: " + IntStream.of(v).map(x -> x * x).sum());
//
//        System.out.println("awesome statistics" + IntStream.of(v).summaryStatistics());
//
//        System.out.println("length: " + IntStream.of(v).sorted().toArray().length);
//
//        System.out.println("Integer[] values: " + Arrays.stream(vO).sorted().collect(Collectors.toList()));
//        System.out.println("int[] values sorted: " + IntStream.of(v).sorted().mapToObj(String::valueOf).collect(Collectors.joining(" ")));


        //Arrays to com.core.example.stream, com.core.example.stream to set map or list, or toArray !!
        //ops();

        //filter greater than 4
//        System.out.println("filter greater than 4: " + Arrays.stream(vO).filter(x -> x > 4).sorted().collect(Collectors.toList()));

    }
}