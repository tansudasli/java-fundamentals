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
     * Approaches (throughput vs latency). Choose mostly 2nd approach over 1st!!
       Threading has initial penalty. So many short tasks does not have advantage when we use multithreading.
       Evaluate the situations.
     * a- One task that is divided into sub-tasks, and multi-threads
     * b- Multiple requests, and one task handled by one thread
     *
     *
     * Thread controlling
     * - Normal way (new Thread() ..) has very limited capabilities for grouping threads (.join), wait(),
         exception handling (esp. explicitly) etc..

         new Thread(Core::kickTask).start();
     * - So we need better controlling capabilities. Use ExecutorService ..= Executors.!!
          * Function types (void or returns something)
             - for void types: extends Thread or implement Runnable or use lambda.
                Use Executors.execute() (standalone) method
            - to return something: implement Callable<> or lambda, which returns Future<>
                Use Executors.submit() (standalone) method.

            Use, Executors.invokeAll() (for logical grouping the tasks and waits all) method.
     *
     *
     * Handling Interruptions, we have 2 options.
     * a- some methods forces to catch InterruptedException (thread.sleep()), so in case of calling explicitly
          thread.interrupt(), you can do something.
     * b- in case of, (in real cases, you do normal do staffs), no InterruptedException catch block mandatory,
          so, you have to listen to Thread.currentThread().isInterrupted(), and handle properly.
          to do so, you need to pinpoint possible long running steps
     *
     *
     *
     * Thread safety (Check shared resources usages)
     * - synchronized : only 1 thread would be able to execute all synced methods
         at that time in that class. Others are wait, even if they need other methods in the class.
     * - lock: separate locks possible at line level. better approach
     * - atomic classes: only for basic arithmetics (add, sub, etc...)
     *     - AtomicInteger ( Atomic....)
     *     - LongAdder
     * - concurrent collections: for specific scenarios (where we used Collections)
     *     - HashTable (thread safe version). But uses synchronized keyword. not performant
     *     - ConcurrentHashMap (more methods and more threadsafe and more performant than Hashtable)
     *
     * so use, lock and atomic classes and ConcurrentHashMap where applicable
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
        //till than below lines wont run !!!
        task1.join();

        //Thread-2 : using anonymous class or lambda
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

        //below lambda, we can also provide thread name and group
        new Thread(Core::kickTask).start();

        //Thread-main : below line is completed  after task1 completion and before kickTask lines !!
        System.out.println("main done " + Thread.currentThread());
    }

}
