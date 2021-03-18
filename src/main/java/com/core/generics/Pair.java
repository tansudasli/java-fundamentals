package com.core.generics;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * Pair will act as a factory class*/
public class Pair<T> {
    private T first;
    private T last;

    public Pair() {
        first = null;
        last = null;
    }

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }
    public void setFirst(T first) {
        this.first = first;
    }
    public T getLast() {
        return last;
    }
    public void setLast(T last) {
        this.last = last;
    }

    public static Pair<String> minmax(String[] input) throws NullPointerException {

        if (input == null) throw new NullPointerException();

        String min = input[0];
        String max = input[0];

        for (String s: input) {
            min = s.compareTo(min) > 0 ? min : s;
            max = s.compareTo(max) > 0 ? s : max;
        }

        return new Pair<>(min, max);
    }

    public static Pair<Integer> minmax(int[] input) throws NullPointerException {

        if (input == null) throw new NullPointerException();

        int min = input[0];
        int max = input[0];

        for (int s: input) {
            min = Math.min(s, min);
            max = Math.max(s, max);
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

        if (input == null) throw new NullPointerException();

        T min = input[0];
        T max = input[0];

        for (T t: input) {
            min = t.compareTo(min) > 0 ? min : t;
            max = t.compareTo(max) > 0 ? t : max;
        }

        return min;
    }

    public static <T extends Comparable> Pair<T> minmax(T[] input) throws NullPointerException {

        if (input == null) throw new NullPointerException();

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

        System.out.println(minmax(words).getFirst()  + ":min | max:" + minmax(words).getLast());
        System.out.println(min(words)  + ":min");

        //System.out.println(TestPair.minmax(String[]::new, "a", "aa", "e", "abc", "ee")  + ":min");

        int[] numbers = { 1, 4, 5, 3, 2 }; //same Pair class for int array

        System.out.println(minmax(numbers).getFirst() + ":min | max:" + minmax(numbers).getLast());
        System.out.println(min(Arrays.stream(numbers).boxed().toArray(Integer[]::new))  + ":min");
        System.out.println(minmax(Arrays.stream(numbers).boxed().toArray(Integer[]::new)).getFirst()  +
                ":min | max: " +
                minmax(Arrays.stream(numbers).boxed().toArray(Integer[]::new)).getLast());

        //test of generic method
        System.out.println(getMiddle("Tansu", "Jn.", "Dasli", 123));
        System.out.println(getMiddle(12, 10, 123.90, 123));
    }
}
