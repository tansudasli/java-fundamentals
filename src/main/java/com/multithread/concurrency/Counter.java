package com.multithread.concurrency;

//non atomic class
public class Counter {
    int i = 0;

    /* below is not atomic as 3 operations happen below
     * what will happen 3 threads trying to increment? it is not thread-safe
     */
    public void increment() {
        i++;
        //get i
        //increment i
        //set i
    }

    public int getI() {
        return i;
    }
}
