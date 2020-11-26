package com.core.fp;

import java.util.stream.IntStream;

public class TestStream2 {

/* selects the even integers in the range 1–10, multiplies each by 3 and sums
 * */
    public static void main(String[] args) {
        long begin;
        long end;
        int total;

        begin = System.nanoTime();
        total = 0;
        for (int x=1; x<= 10; x++) {
            if (x%2 == 0)
                total += x * 3;
        }
        end = System.nanoTime() - begin;

        System.out.println("o: elapsed time: " + end + "\tresult: " + total);

        //with streams
        total = 0;
        begin = 0;
        end = 0;
        begin = System.nanoTime();
/*
        total = IntStream.rangeClosed(1, 10)
                .filter(x -> x%2 == 0)
                .map(x -> x*3)
                .sum();
*/
        //or

        total = IntStream.rangeClosed(1, 10)
                .map(x -> x % 2 == 0 ?  x * 3 :  0)
                .sum();

        end = System.nanoTime() - begin;

        System.out.println("s: elapsed time: " + end + "\tresult: " + total);

    }
}
