package com.multithread.concurrency;

//non-atomic class
public class Counter {
    private int i = 0;
    private int j = 0;

    /* below is not atomic as 3 operations happen below
     * what will happen 3 threads trying to increment? it is not thread-safe
     *
     * So, we can
     * 1- synchronized: but only 1 thread can use this class. even there are different methods!! not performant.
     * 2- lock
     */
    synchronized public void incrementI() {
        i++;
        //get i
        //increment i
        //set i
    }

    synchronized public void incrementJ() {
        j++;
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
