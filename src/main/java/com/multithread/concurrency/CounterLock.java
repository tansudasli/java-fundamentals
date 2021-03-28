package com.multithread.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//non-atomic class
public class CounterLock {
    private int i = 0;
    private int j = 0;

    Lock lockI = new ReentrantLock();
    Lock lockJ = new ReentrantLock();

    /* below is not atomic as 3 operations happen below
     * what will happen 3 threads trying to increment? it is not thread-safe
     *
     * So, we can
     * 1- synchronized: but only 1 thread can use this class. even there are different methods!! not performant.
     * 2- lock: separate locks possible.
     */
    public void incrementI() {

        //get lock
        lockI.lock(); i++; lockI.unlock();
        //release lock

    }

    public void incrementJ() {

        lockJ.lock(); j++; lockJ.unlock();

        //get i
        //increment i
        //set i
    }

    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }
}
