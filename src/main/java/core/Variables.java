package core;

import java.math.BigInteger;

public class Variables {

    public static final double CM_TO_INCH = 2.54;  //more appropriate as defined static.
                                                   // because it is final, not changeable

    public static int castToInt (double v) {
        return (int) v;
    }
    public static long roundToLong(double v) {
        return Math.round(v);
    }

    public enum SIZE {XS, S, M, L, XL, XXL}

    // public static void functionX (String s, Object...args) { }

    public static double max(double...args) {
        double max = 0;

        for (double v : args)
            if (v > max)
                max = v;

        return max;
    }

    /* concatenation.
       Use + for small number of concatenation. But, String.concat is better...
       in a Loop or more ops., use StringBuilder or StringBuffer
     */
    public static String greeting() {
        return "The fox was already in your chicken house.";
    }

    public static BigInteger addPrecise(BigInteger ...args) {
        BigInteger result = BigInteger.ZERO;

        for (BigInteger v : args)
            result = result.add(v);

        return result;
    }
    public static void main(String[] args) {



        /* operators
        *
        *  b ? 1 : 0
        *  zz < zzz ? 1 : 0
        *
        * */





        /* for more precision
        *
        * BigInteger a = BigInteger.valueOf(100);
        *
        * a.add(b); not a+b !!!
        * */

        /* Array
        *
        * int[] i = new int[100];
        *
        * i.length
        * */

        /*
        * for (variable : collection) statement
        *
        * Iterable interface has to be implemented, such as ArrayList
        *
        * Arrays.copyOf(arrayX, 2 * arrayXLength);
        * Arrays.sort() , QuickSort
        * Arrays.binarySearch(), BinarySearch algorithm
        *
        * double[][] balances = new double[3][4]; multi-dimensional arrays. Multidimensional arrays are faked as “arrays of arrays.” in Java
        * */

        /* Date
        * Use LocalDate class. Date, GregorianCalendar class are deprecated or more complex operations!
        *
        * LocalDate x = LocalDate.now()
        * x.getYear()
        * y = x.plusDays(1000) , 1000 days later.
        * y.getYear() --> new year you get
        *
        * */

        /* The outer braces make an anonymous subclass of ArrayList
        * {{ object constructor
        * If you don’t need the array list again, it would be nice to make it anonymous

        new ArrayList<String>() {{ add("Harry"); add("Tony"); }};

        * or

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Harry");
        friends.add("Tony");

        * */

        //infinite variables
        System.out.println(max(12, 34, 25, 56));

        System.out.println(max(12, 34, 25, 56, 75, 789, 9999, 1899,2829829.04, 2829829, 33, 33, 33333, 8989));

    }
}
