package com.multithread;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexCalculation {

    public static BigInteger calculatePower(int base, int power) {

        BigInteger result = BigInteger.ONE;

        if (power == 0)
            return result;

        for (int i = 0; i < power; i++) {
            //todo: handle interruption
            result = result.multiply(BigInteger.valueOf(base));
        }

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
         * - Throughput vs Latency                          Throughput
         *
         */



        BigInteger sum = sumOfPowers(2, 1, 3, 1);

        assert (sumOfPowers(2, 1, 3, 1).equals(BigInteger.valueOf(5)));
        assert (sumOfPowers(2, 2, 3, 2).equals(BigInteger.valueOf(13)));

        System.out.println(sum);

    }
}
