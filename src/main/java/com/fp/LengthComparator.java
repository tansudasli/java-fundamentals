package com.fp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/* lambda notation:  () -> { };
* It is best to think of a lambda expression as a function, not an object,
* and to accept that it can be passed to a functional interface.
* */
public class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        return o1.length() - o2.length();
    }

    public static void main(String[] args) {

        String[] s = new String[] {"Tansu", "Abidin", "Ayşe", "Yiğit"};

        //1th way with implementing compare method ..
        Arrays.sort(s, new LengthComparator());

        /*2nd way: anonymous inner class: Class inside a class
        *  Why
        *  1- access even private fields data
        *  2- anonymous class is handy, when u define a callbacks without writing a lot of code.
        *     (to implement callbacks before Java had lambda expressions.)
        *
        * anonymous block of Object
        * new Object(){}
        */
        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return o1.length() - o2.length();
            }
        });

        //3rd way - lambda
        Arrays.sort(s, (o1, o2) -> o1.length() - o2.length());

        //4th way - lambda & Comparator utility class
        Arrays.sort(s, Comparator.comparingInt(String::length));

        //5th way - lambda & streams
        Stream<String> c = Arrays.stream(s).sorted(Comparator.comparingInt(String::length));


        //1th, 2nd, 3rd, 4th way - show
        for (String x : s)
            System.out.println(x);

        //1th, 2nd, 3rd, 4th way - show
        System.out.println(Arrays.toString(s));

        //5th way - show
        c.forEach(System.out::println);

    }

}
