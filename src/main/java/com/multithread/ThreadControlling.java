package com.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadControlling {
    /*
     * Thread Controlling
     * Basically, creates a thread pool with specific thread count
     *
     *  - Executors.newSingleThreadExecutor() : 1 thread in pool
     *  - Executors.newFixedThreadPool(n)    : n threads in pool
     *
     * How to return value from threads ?
     *
     */

    public static void main(String[] args) {
//        ExecutorService exec = Executors.newSingleThreadExecutor();  //execs single thread at a time
        ExecutorService exec = Executors.newFixedThreadPool(3);

        exec.execute(new Task1());
        exec.execute(new Task2());  //exec.execute(new Thread(new Task2()));  no need a separate thread allocation first

        exec.execute(() -> {
            System.out.println("Task-3 started");

            for (int i = 301 ; i < 351; i++)
                System.out.println(i + " " + Thread.currentThread());

            System.out.println("Task-3 done");
        });

        exec.shutdown();

    }
}
