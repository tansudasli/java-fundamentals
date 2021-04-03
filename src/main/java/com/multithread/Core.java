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
     * Multi-threading (IO intensive or Computing intensive)
     *
     * 2 ways
     * - for void types: extends Thread or implement Runnable or use lambda.
         Use Executors.execute() method
     * - to return something: implement Callable<> or lambda, which returns Future<>
         Use Executors.submit() or Executors.invokeAll() (logical grouping tasks and waits all) method.
     *
     * Thread controlling
     * - Normal way has very limited capabilities.
           - Task1.join()
           - Thread.wait() or Thread.yield()
           - threads that raised InterruptedException
           - thread which handles exceptions explicitly
     * - So we need ExecutorService !!
     *
     * to handle Interruptions, we have 2 options.
     * a- some methods forces to catch InterruptedException (thread.sleep()), so in case of explicitly
          thread.interrupt(), you can do something.
     * b- in case of, (in real cases, you do normal do staffs), no InterruptedException catch block mandatory,
          so, you have to listen to Thread.currentThread().isInterrupted(), and handle properly.
          to do so, you need to pinpoint possible long running steps
     *
     *
     *
     * Thread safety
     * - synchronized keyword : only 1 thread would be able to execute all synced methods
         at that time in that class. Others are wait
     * - lock: separate locks possible. better approach
     * - atomic class: only for basic arithmetics
     *     - AtomicInteger
     * - concurrent collections: for specific scenarios (where we used Collections)
     *     - HashTable (thread safe version)
     *     - ConcurrentHashMap (more methods and more threadsafe and more performant than Hashtable)
     *
     * so use, lock and atomic classes where applicable
     *
     *
     * Thread states
     * - new (created, but not started)
     * - runnable (was running but not finished yet)
     * - running (actively running)
     * - blocked/waiting
     * - terminated/dead (done)
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
