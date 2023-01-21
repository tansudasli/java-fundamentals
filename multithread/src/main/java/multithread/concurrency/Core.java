package multithread.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Core {

    /*
     * Thread safety
     *
     * below is not atomic as 3 operations happen below
     * what will happen 3 threads trying to increment? it is not thread-safe
     *
     * So, we can
     * 1- synchronized: but only 1 thread can use this class. even there are different methods!! not performant.
     * 2- lock
     *
     *
     */

    public static void main(String[] args) {
        Counter c = new Counter();
        CounterAtomic ca = new CounterAtomic();

//        c.incrementI();
//        c.incrementI();
//        c.incrementI();
//        System.out.println(c.getI());

        //below threads in the pool, accessing same local variable.
        //shared resources
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //synchronized
        try {

            for (int i = 0; i < 30; i++) {
                executorService.submit(c::incrementI).get();
            }

            System.out.println(c.getI());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //atomic
        try {

            for (int j = 0; j < 30; j++) {
                executorService.submit(ca::incrementJ).get();
            }

            System.out.println(ca.getI());
            System.out.println(ca.getJ());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        executorService.shutdown();

    }
}
