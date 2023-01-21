import java.util.Scanner;

/**
 * amount = principal * (1 + rate)^year
 * */
public class CompoundInterest {

    public static void main(String[] args) {

        double principal = 1000.0;
        double rate = 0.05;

        /* System.in is the keyboard itself */
        Scanner in = new Scanner(System.in);

        System.out.println("Enter year: ");

        int year = in.nextInt(10);

        for (int i = 1; i <= year; i++) {
            double amount = principal * Math.pow(1.0 + rate, i);

            System.out.printf("%4d   %10.2f\n", i, amount);
        }



    }
}
