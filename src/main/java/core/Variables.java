package core;

import java.math.BigInteger;
import java.util.stream.IntStream;

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

    // for more precision, BigInteger...
    public static BigInteger addPrecise(BigInteger ...args) {
        BigInteger result = BigInteger.ZERO;

        for (BigInteger v : args)
            result = result.add(v);

        return result;
    }

    public static int[] ints(boolean initialized) {
        return initialized ? new int[] {3, 5} : intsDataGenerator();
    }

    public static String[] strings(boolean initialized) {
        return initialized ? new String[] {"The ", "fox ", "was ", "already ","in ", "your ", "chicken ", "house."}
                           : stringsDataGenerator();

    }

    public static int[] intsDataGenerator() {

        return IntStream.rangeClosed(0, 100)
                .map(x -> x * x)
                .toArray();
    }
    public static String[] stringsDataGenerator() {

        return """
        It is an evil sign too see a fox lick a lamb.
        You can have no more of the fox than the skin.
        The fox knew too much, that's how he lost his tail.
        The hounds lost the scent of the fox.
        The quick brown fox jumps over a lazy dog.
        He's a cunning old fox.
        A fox may grow gray, but never good.
        He's a cunning/sly/wily old fox.
        He is cunning as a fox.
        The huntsmen rode fast, chasing after the fox.
        He sets the fox to keep the geese.
        He's a sly old fox.
        He was as cunning as a fox.
        It is a blind silly goose that comes to the fox’s sermon
        """.split("\\W+");

        //todo: get stream and replace s -> is, then return them all
    }

    public static void main(String[] args) {



        /* operators
        *
        *  b ? 1 : 0
        *  zz < zzz ? 1 : 0
        *
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
