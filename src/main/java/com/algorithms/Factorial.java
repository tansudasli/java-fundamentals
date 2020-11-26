package com.algorithms;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Factorial with streams
 * 0 = 1
 * 1 = 1
 * n*(n-1)*(n-2)*....1
 */
public class Factorial {
    static int count;

    private static double factorial(int n) {

        return n <= 1 ? 1 : n * factorial(n-1);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number:\n");
        count = scanner.nextInt();

        Long begin = System.nanoTime();

        //switch to test
        //System.out.println(factorial(count));
        System.out.println(IntStream.rangeClosed(1, count).reduce(1, (x,y) -> x*y));

        Long end = System.nanoTime() - begin;
        System.out.printf("took %d milliseconds", end/1000000);
    }
}
