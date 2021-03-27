package com.multithread;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadReturnAnyMultipleValues {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //executor
        ExecutorService exec = Executors.newFixedThreadPool(3);

        List<Task> tasks = List.of(new Task("Tansu"),
                new Task("Zabid"),
                new Task("Abidin"),
                new Task("Zabid"),
                new Task("Kabil"),
                new Task("Habil"),
                new Task("Mabel"),
                new Task("halo"),
                new Task("malo"),
                new Task("cano"),
                new Task("kano"));

        /*
         * invokeAll executes all but also waits all!
         * invokeAny executes just 1 of them :)
         */

        //trigger
//        List<Future<String>> results = exec.invokeAll(tasks);   //List<Future<String>>
//        for (Future<String> r : results)
//            System.out.println(r.get());

        System.out.println(exec.invokeAny(tasks));


        //shutdown
        exec.shutdown();
    }
}
