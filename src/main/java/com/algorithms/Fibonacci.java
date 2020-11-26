package com.algorithms;

import java.util.Scanner;

/**
 * Fibonacci with streams
 *
 * f(0) = 0
 * f(1) = 1
 * f(n) = f(n-1) + f(n-2)
 */
public class Fibonacci {
    static int count;

    private static int fibonacci(int n) {

        return n == 0 || n == 1 ? n : fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number:\n");
        count = scanner.nextInt();

        Long begin = System.nanoTime();

        //switch to test
        System.out.println(fibonacci(count));
        //TODO with stream ?

        Long end = System.nanoTime() - begin;
        System.out.printf("took %d milliseconds", end/1000000);
    }
}
