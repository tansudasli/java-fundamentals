package com.multithread.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

//non-atomic class
public class CounterAtomic {
    private AtomicInteger i = new AtomicInteger(0);
    private AtomicInteger j = new AtomicInteger(0);

    /* below is not atomic as 3 operations happen below
     * what will happen 3 threads trying to increment? it is not thread-safe
     *
     * So, we can
     * 1- synchronized: but only 1 thread can use this class. even there are different methods!! not performant.
     * 2- lock
     * 3- atomic class : for basic arithmetic ops only
     * 4- Concurrent collections: for specific scenarios
     *
     */
    public void incrementI() {
        i.incrementAndGet();

        //get i
        //increment i
        //set i
    }

    public void incrementJ() {
        j.incrementAndGet();

        //get i
        //increment i
        //set i
    }

    public int getI() {
        return i.get();
    }
    public int getJ() {
        return j.get();
    }
}
