package com.multithread;

public class HandlingInterruption {

    public static void BlockingTask() {
        try {
            Thread.sleep(50000);

        } catch (InterruptedException e) { //so this part can be used some clearing staffs, logging etc..
            e.printStackTrace();

            System.out.println(Thread.currentThread().getName() + " Interrupted");
        }
    }

    public static void LongRunningTask(Integer base, Integer power) {

        Integer result = 1;
        for (int i = 0; i < power; i++) {
            //do some staffs, depending on parameters, it may take long time
            //so we have to listen interruption
            if (Thread.currentThread().isInterrupted()) {
                //
                System.out.println(Thread.currentThread().getName() + " interrupted");
                result = 0;
                break;
            }
            result *= base;
        }

        System.out.print( base + "^" + power + " = " + result);
    }

    /* Handling Interruption
     *
     * a- some methods forces to catch InterruptedException, so if explicitly thread.interrupt()
          you can do something.
     * b- in case of, (in real cases, you do normal do staffs), no catch block mandatory, you have to
          listen to Thread.currentThread().isInterrupted(), and handle properly.
     */
    public static void main(String[] args) {
        //ExecutorService handles better thread management. so we need basic management
//        ExecutorService exec = Executors.newFixedThreadPool(2);
//        exec.execute(BlockingThread::BlockingTask);

        //a- where naturally InterruptedException occurs
//        Thread thread = new Thread(HandlingInterruption::BlockingTask, "Blocking-Task");
//        thread.start();     //will run thread. and it blocks main-thread
//        thread.interrupt(); //interrupt explicitly.

        //b-when we run in debug mode, it is running too much time. then we push stop,
        //but it goes to run continually  !!.
        //so we have to handle pinpoints for long running steps
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                LongRunningTask(3, 2);  //
                LongRunningTask(300, 2000000000);
            }
        }, "LongRunning-Thread");

        thread.start();
    }
}
