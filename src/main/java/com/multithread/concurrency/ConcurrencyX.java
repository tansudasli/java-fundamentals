package com.multithread.concurrency;

public class ConcurrencyX {

    /*
     * Thread safety
     *
     * below is not atomic as 3 operations happen below
     * what will happen 3 threads trying to increment? it is not thread-safe
     *
     * So, we can
     * 1- synchronized: but only 1 thread can use this class. even there are different methods!! not performant.
     * 2- lock
     *
     *
     */

    public static void main(String[] args) {
        Counter c = new Counter();

        c.incrementI();
        c.incrementI();
        c.incrementI();

        System.out.println(c.getI());
    }
}
