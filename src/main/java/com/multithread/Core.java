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
     * - new (created, but not started)
     * - runnable (was running but not finished yet)
     * - running (actively running)
     * - blocked/waiting
     * - terminated/dead (done)
     *
     * Depends another thread complete or gives a chance
     * - Task1.join()
     * - Thread.wait() or Thread.yield()
     *
     * Thread safety
     * - synchronized keyword : only 1 thread would be able to execute all synced methods
         at that time in that class. Others are wait
     *
     * Thread controlling
     * - very limited capabilities. So we need ExecutorService !!
     *
     */

    static void kickTask() {
        System.out.println("Task-3 started");

        for (int i = 201 ; i < 250; i++)
            System.out.println(i + " " + Thread.currentThread());

        System.out.println("Task-3 done");
    }

    public static void main(String[] args) throws InterruptedException {
        //Thread-0
        Task1 task1 = new Task1();
        task1.start();

        //Thread-main
        /*
        for (int i = 101 ; i < 111; i++)
            System.out.println(i + " " + Thread.currentThread());
        */

        //Thread-1
        new Thread(new Task2()).start();

        //wait Task1 complete, then run below lines/threading things
        task1.join();

        //Thread-3 : using anonymous class or lambda
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task-3 started");

                for (int i = 201 ; i < 250; i++)
                    System.out.println(i + " " + Thread.currentThread());

                System.out.println("Task-3 done");
            }
        }).start(); */
        /*
        new Thread(() -> {
            System.out.println("Task-3 started");

            for (int i = 201 ; i < 250; i++)
                System.out.println(i + " " + Thread.currentThread());

            System.out.println("Task-3 done");
        }).start();
         */
        new Thread(Core::kickTask).start();

        //Thread-main : above threads are done after main thread is done !!
        System.out.println("main done " + Thread.currentThread());
    }

}
