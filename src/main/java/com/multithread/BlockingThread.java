package com.multithread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingThread {

    public static void BlockingTask() {
        try {
            Thread.sleep(50000);

        } catch (InterruptedException e) { //so this part can be used some clearing staffs, logging etc..
            e.printStackTrace();

            System.out.println(Thread.currentThread().getName() + " Interrupted");
        }
    }

    public static void main(String[] args) {
        //ExecuterService handles better thread management. so we need basic management
//        ExecutorService exec = Executors.newFixedThreadPool(2);
//        exec.execute(BlockingThread::BlockingTask);

        Thread thread = new Thread(BlockingThread::BlockingTask, "Blocking-Task");
        thread.start();     //will run thread. and it blocks main-thread

        thread.interrupt(); //interrupt explicitly.
    }
}
