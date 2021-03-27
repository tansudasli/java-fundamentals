package com.multithread.concurrency;

public class ConcurrencyX {

    /*
     *
     *
     *
     *
     */

    public static void main(String[] args) {
        Counter c = new Counter();

        c.increment();
        c.increment();
        c.increment();

        System.out.println(c.getI());
    }
}
