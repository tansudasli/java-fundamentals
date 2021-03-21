package com.generics;

import java.util.ArrayList;
import java.util.List;

/*
 * Some restrictions about generics
 */
public class Restrictions {

    public static void main(String[] args) {

        //List<int> i = new ArrayList<>();  //primitives not allowed

        //no instance        T name = new T();
        //no static field    private static T age

        //no casting
        List<Number> n1 = new ArrayList<>();
        //List<Integer> n2 = (List<Integer>) n1;

        //no instanceof allowed
        //n1 instanceof ArrayList<Number> ? ((ArrayList<Number>) n1) : null;

        //

    }

}
//no Exception or Throwable
//class ABC<T extends Exception> {
//    T a;
//}

class XYZ<T> {
    T name;
//        T name = new T();  //no instance

    private static String surname;
//    private static T age;  //no static field
}
