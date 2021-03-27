package com.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadControlling {
    /*
     * Thread Controlling
     *
     *
     *
     */

    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();  //execs single thread at a time

        exec.execute(new Task1());
        exec.execute(new Thread(new Task2()));  // exec.execute(new Task2());

    }
}
