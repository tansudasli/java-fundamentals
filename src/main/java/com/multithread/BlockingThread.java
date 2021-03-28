package com.multithread;

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
        Thread thread = new Thread(BlockingThread::BlockingTask, "Blocking-Task");

        thread.start();     //will run thread. and it blocks main-thread

        thread.interrupt(); //interrupt explicitly.
    }
}
