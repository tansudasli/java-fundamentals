package com.core.generic;


import java.util.Arrays;
import java.util.function.IntFunction;

/** The static minmax method traverses an array and simultaneously computes the minimum and maximum value.
 * It uses a Pair object to return both results.
 * version: normal
 */
public class TestPair {
    public static Pair<String> minmax(String[] input) throws NullPointerException {

        if (input == null || input.length == 0)
            throw new NullPointerException();

        String min = input[0];
        String max = input[0];

        for (String s: input) {
            min = s.compareTo(min) > 0 ? min : s;
            max = s.compareTo(max) > 0 ? s : max;
        }

        /* no need anymore
        for (int i = 1; i<input.length; i++) {
            min = input[i].compareTo(min) > 0 ? min : input[i];
            max = input[i].compareTo(max) > 0 ? input[i] : max;
        }
        */

        return new Pair<>(min, max);
    }

    public static Pair<Integer> minmax(int[] input) throws NullPointerException {

        if (input == null || input.length == 0)
            throw new NullPointerException();

        int min = input[0];
        int max = input[0];

        for (int s: input) {
            min = s > min ? min : s;
            max = s > max ? s : max;
        }

        return new Pair<>(min, max);
    }

    /* The variable smallest has type T, which means that it could be an object of an arbitrary class.
      How do we know that the class to which T belongs has a compareTo method?
      The solution is to restrict T to a class that implements the Comparable interface
      A type variable or wildcard can have multiple bounds. For example:
      T extends Comparable & Serializable
    */
    public static <T extends Comparable> T min(T[] input) throws NullPointerException {

        if (input == null || input.length == 0)
            throw new NullPointerException();

        T min = input[0];
        T max = input[0];

        for (T t: input) {
            min = t.compareTo(min) > 0 ? min : t;
            max = t.compareTo(max) > 0 ? t : max;
        }

        return min;
    }

    public static <T extends Comparable> Pair<T> minmax(T[] input) throws NullPointerException {

        if (input == null || input.length == 0)
            throw new NullPointerException();

        T min = input[0];
        T max = input[0];

        for (T t: input) {
            min = t.compareTo(min) > 0 ? min : t;
            max = t.compareTo(max) > 0 ? t : max;
        }

        return new Pair<>(min, max);
    }

    public static <T extends Comparable> T[] minmax(IntFunction<T[]> f, T...inputs) throws NullPointerException {

        //TODO: bunu düzelt :)
        if (inputs == null || inputs.length == 0 || f == null)
            throw new NullPointerException();

        T[] mm = f.apply(2);

        T min = mm[0];
        T max = mm[0];

        for (T t: mm) {
            min = t.compareTo(min) > 0 ? min : t;
            max = t.compareTo(max) > 0 ? t : max;
        }

        return mm;
    }

    //generic method
    public static <T>T getMiddle(T...a) {
        return a[a.length/2];

    }

    public static void main(String[] args) {
        //String[] words = {"White", "fox", "can not", "jump", "over", "the", "trees"};
        String[] words = { "a", "aa", "e", "abc", "ee" }; //same Pair class for String array

        System.out.println(TestPair.minmax(words).getFirst()  + ":min | max:" + TestPair.minmax(words).getLast());
        System.out.println(TestPair.min(words)  + ":min");

        //System.out.println(TestPair.minmax(String[]::new, "a", "aa", "e", "abc", "ee")  + ":min");

        int[] numbers = { 1, 4, 5, 3, 2 }; //same Pair class for int array

        System.out.println(TestPair.minmax(numbers).getFirst() + ":min | max:" + TestPair.minmax(numbers).getLast());
        System.out.println(TestPair.min(Arrays.stream(numbers).boxed().toArray(Integer[]::new))  + ":min");
        System.out.println(TestPair.minmax(Arrays.stream(numbers).boxed().toArray(Integer[]::new)).getFirst()  +
                ":min | max: " +
                TestPair.minmax(Arrays.stream(numbers).boxed().toArray(Integer[]::new)).getLast());

        //test of generic method
        System.out.println(TestPair.getMiddle("Tansu", "Jn.", "Dasli", 123));
        System.out.println(TestPair.getMiddle(12, 10, 123.90, 123));
    }

}
