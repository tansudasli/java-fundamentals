package com.core.fp;

import java.util.stream.LongStream;

public class TestStream {

    public static void o(long endExclusive) {
        long startTime = System.nanoTime();

        long total=0;
        for (long i=1; i<=endExclusive; i++)
            total += i;

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime + "nano sec via for loop " + total);
    }

    public static void o2(long endExclusive) {
        long startTime = System.nanoTime();

        long total=0;
        for (long i=2; i<=endExclusive; i+=2)
            total += i;

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime + "nano sec via for loop " + total);
    }

    public static void s(long endExclusive){

        long startTime = System.nanoTime();

        long total = LongStream.rangeClosed(0L, endExclusive)
                .sum();

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime + "nano sec via com.core.example.stream " + total);
    }

    public static void s2(long endExclusive){

        long startTime = System.nanoTime();

        long total = LongStream.rangeClosed(0L, endExclusive)
                .map(x -> x*2)
                .sum();

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println(elapsedTime + "nano sec via com.core.example.stream " + total);
    }

    public static void main(String[] args) {

        s2(1000000);

        o2(2000000);

    }
}
