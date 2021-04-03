package com.multithread;

import java.math.BigInteger;

import static java.lang.Thread.sleep;

public class HandlingInterruption {

    public static void BlockingTask() {
        try {
            sleep(50000);

        } catch (InterruptedException e) { //so this part can be used some clearing staffs, logging etc..
            e.printStackTrace();

            System.out.println(Thread.currentThread().getName() + " Interrupted");
        }
    }

    public static void LongRunningTask(Integer base, Integer power) {

        BigInteger result = BigInteger.ONE;

        for (int i = 0; i < power; i++) {
            //do some staffs, depending on parameters, it may take long time
            //so we have to listen to explicit interruptions !!
            //otherwise, it goes on to run !!
            if (Thread.currentThread().isInterrupted()) {
                //
                System.out.println(Thread.currentThread().getName() + " interrupted");
                result = BigInteger.ZERO;

                break;
            }

            result = result.multiply(BigInteger.valueOf(base));
        }

        System.out.print( base + "^" + power + " = " + result);
    }

    /* Handling Interruption
     *
     * a- some methods forces to catch InterruptedException, so if explicitly call thread.interrupt(),
          you can do something.
     * b- in case of, (in real cases, you do normal do staffs), no catch block mandatory, you have to
          listen to Thread.currentThread().isInterrupted(), and handle properly.
     */
    public static void main(String[] args) throws InterruptedException {
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
        //LongRunningTask(3, 2);
        Thread thread = new Thread(() -> LongRunningTask(3, 20000),
                            "LongRunning-Thread");

        thread.start();

        thread.interrupt();  //explicitly interrupt, and handle in the task. otherwise,
                             // it ain't responds and continues to run which may lead to blocking somethings

    }
}
