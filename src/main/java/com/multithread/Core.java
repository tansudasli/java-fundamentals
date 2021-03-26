package com.multithread;

class Task1 extends Thread {
    @Override
    public void run() {
        System.out.println("Task-1 started");

        for (int i = 0 ; i < 30; i++)
            System.out.println(i + " " + Thread.currentThread());

        System.out.println("Task-1 done");
    }
}

class Task2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Task-2 started");

        for (int i = 101 ; i < 111; i++)
            System.out.println(i + " " + Thread.currentThread());

        System.out.println("Task-2 done");
    }
}

public class Core {
    /*
     * Multi-threading
     *
     * - IO intensive
     * - Computing intensive
     *
     * 2 ways (Use class or interface)
     * - extends Thread: create a Task class, then imp. run(), then new Task().start().
     * - implement Runnable:
     *
     * Thread states
     *
     *
     */

    public static void main(String[] args) {
        //Thread-0
        (new Task1()).start();

        //Thread-main
        /*
        for (int i = 101 ; i < 111; i++)
            System.out.println(i + " " + Thread.currentThread());
        */

        //Thread-1
        new Thread(new Task2()).start();

        //Thread-main : above threads are done after main thread is done !!
        System.out.println("main done " + Thread.currentThread());
    }

}
