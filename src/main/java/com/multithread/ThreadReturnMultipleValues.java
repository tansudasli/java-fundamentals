package com.multithread;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;



public class ThreadReturnMultipleValues {

    private static String handleTask(String name) throws InterruptedException {
        Thread.sleep(1000);

        return "Hello " + name + " " + Thread.currentThread();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //executor
        ExecutorService exec = Executors.newFixedThreadPool(2);

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
         */

        //trigger
//        List<Future<String>> results = exec.invokeAll(tasks);   //List<Future<String>>
//        for (Future<String> r : results)
//            System.out.println(r.get());

        exec.invokeAll(tasks).forEach(result -> {
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
//
//        exec.invokeAll(tasks).stream().map(s -> {
//            try {
//                return s.get();
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }).forEach(System.out::println);

        //shutdown
        exec.shutdown();

    }
}
