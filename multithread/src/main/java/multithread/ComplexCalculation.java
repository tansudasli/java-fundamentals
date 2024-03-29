package multithread;

import java.math.BigInteger;
import java.util.concurrent.*;

public class ComplexCalculation {

    public static BigInteger calculatePower(int base, int power) {

        BigInteger result = BigInteger.ONE;

        System.out.println(Thread.currentThread().getName() + "..started  ");

        if (power == 0) return result;

        for (int i = 0; i < power; i++) {
            System.out.println(Thread.currentThread().getName() + "  " + i);

            //handle interruption
            if (Thread.currentThread().isInterrupted()) {

                result = BigInteger.ZERO;
                break;
            }

            result = result.multiply(BigInteger.valueOf(base));
        }

        System.out.println(Thread.currentThread().getName() + "  " + result);

        return result;
    }

    public static BigInteger sumOfPowers(int base1, int power1, int base2, int power2) {

        BigInteger r1 = calculatePower(base1, power1);
        BigInteger r2 = calculatePower(base2, power2);

        return  r1.add(r2);
    }

    public static void main(String[] args) {

        /*
         * calculate sum of two power's result
         *
         * - write serial calculation                       DONE
         * - Thread controlling (Thread vs Executors)       Executors
         * - Throughput vs Latency (sub-task)               LATENCY
         * - Shared resource usage                          NONE
         * - Interruption handling (Manuel vs Auto)         MANUEL
         *
         */

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        try {
            /*
             * if we use get() like below, then it blocks!! and also guarantee that all results
             * will be ready at sum step. Bad way to do it
             * BigInteger r1 = executorService.submit(() -> calculatePower(200,1000)).get();
             *
             * when we split lines, it becomes non-blocking
             * Callable<BigInteger> t1 = () -> calculatePower(2,1000);
             * Future<BigInteger> r1 = executorService.submit(t1);
             */

            //nonblocking style. separate task, future and get calls.
            //and we need 2 results to calculate sum, and get() is guarantee that.
            //below lines blend the thread1 and thread2 in the pool.
            //and f1.get().add(f2.get()) guarantees that all results will be ready at sum step
            //So, do we need invokeAll ?
            Callable<BigInteger> t1 = () -> calculatePower(2,1000);
            Callable<BigInteger> t2 = () -> calculatePower(3,1000);

            Future<BigInteger> f1 = executorService.submit(t1);
            Future<BigInteger> f2 = executorService.submit(t2);

            System.out.println(f1.get().add(f2.get()));

            //using invokeAll().
//            List<Callable> tasks = List.of(t1, t2);
//            Collection<Callable<BigInteger>> tasks = List.of(t1, t2);
//            executorService.invokeAll(tasks).stream().map(f -> {
//                try {
//                    return f.get();
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                }
//                return BigInteger.ZERO;
//            }).collect();


            //below is blocking. thread2 does not start till thread1 ends. due to get() call
//           BigInteger r1 = executorService.submit(() -> calculatePower(200,1000)).get();
//           BigInteger r2 = executorService.submit(() -> calculatePower(3,1000)).get();
//
//            System.out.println(r1.add(r2));


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


        //serial version
//        BigInteger sum = sumOfPowers(2, 1, 3, 1);
//
//        assert (sumOfPowers(2, 1, 3, 1).equals(BigInteger.valueOf(5)));
//        assert (sumOfPowers(2, 2, 3, 2).equals(BigInteger.valueOf(13)));

//        System.out.println(sum);

        executorService.shutdown();
    }
}
