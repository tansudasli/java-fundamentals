package com.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Callable<String> {
    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);

        return "Hello " + name + " " + Thread.currentThread();
    }
}
public class ThreadReturnValues {
    /*
     * Returning Values from Threads
     *
     *
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Thread controlling
        ExecutorService exec = Executors.newFixedThreadPool(2);

        //a Task that implemented Callable interface
        String result = exec.submit(new Task("Tansu"))
                            .get();

        System.out.println(result);

        //a Task that implemented Callable interface (anonymous class)
//        System.out.println(
//                exec.submit(new Callable<String>() {
//
//                                @Override
//                                public String call() throws Exception {
//                                    Thread.sleep(1000);
//
//                                    return "Hello " + Thread.currentThread();
//                                }
//                            }
//                )                //Future<String
//                .get()            //String
//        );

        //a Task that implemented Callable interface (with lambda )
        System.out.println(
                exec.submit(() -> handleTask("Tansu"))                   //Future<String
                     .get()            //String
        );


    }

    private static String handleTask(String name) throws InterruptedException {
        Thread.sleep(1000);

        return "Hello " + name + " " + Thread.currentThread();
    }
}
