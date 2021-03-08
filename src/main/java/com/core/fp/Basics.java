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

public class Basics {

    static int[] v = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};
    static Integer[] vO = {2, 9, 5, 0, 3, 7, 1, 4, 8, 6};
    static List<Integer> vL = Arrays.asList(vO);


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
                .ifPresent(max -> System.out.println("Max: " + max));

        //combining Optional.of and stream results to print elegantly
        OptionalInt.of(Arrays.stream(v)
                            .filter(greaterThanFIVE)
                            .sum())
                   .ifPresent(total -> System.out.println("Sum: " + total));

//        System.out.println("min: " + IntStream.of(v).min().isPresent());
        //below is better than,
        stream(v)                         //already IntStream. So, no need for IntStream.of(v)
                .parallel()
                .min()                    //switch to OptionalInt
                .ifPresent(min -> System.out.println("Min: " + min));  //use pure functions,
                                                                   //and, call functions instead of many lambda lines !!

        stream(v)
                .parallel()
                .reduce((x, y) -> x * y)    //Optional
                .ifPresentOrElse(productOf -> System.out.println("Sum of: " + productOf),
                                 () -> System.out.println("error/.."));   //Runnable,  separate thread
        stream(v)
                .sorted()
                .forEach(e -> System.out.print(e + " "));

        //or
//        stream(v)
//                .sorted()
//                .mapToObj(String::valueOf)
//                .reduce((carry, str) -> carry.concat(" " + str + " "))
//                .ifPresent(System.out::print);

//        stream(v)
//                .sorted()
//                .collect(Collectors.joining(" "));



    }
}