package com.algorithms;

import java.util.Scanner;

/*
 * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
 * */
public class LotteryOdds {

    public static void main(String[] args) {

        /* System.in is the keyboard itself */
        Scanner in = new Scanner(System.in);

        /* Read Data */
        System.out.println("Max Number in bucket ?");
        int n = in.nextInt();

        System.out.println("Numbers you need ?");
        int k = in.nextInt();

        /* Write your code */
        int odds = 1;
        for (int i=1; i <= k; i++)
            odds = odds * (n - i + 1) / i;

        System.out.println("odds are 1 in a " + odds);
    }
}
