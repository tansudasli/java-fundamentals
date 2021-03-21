package com.generics;

import java.util.ArrayList;

public class Behaviour {

    public static void main(String[] args) {

        //we can only add string values!
        //generic type
//        ArrayList<String> s = new ArrayList<>();
//        s.add("abidin");
//        s.add("malibu");

//        action(s);

        /*
         * we can add ArrayList<String>, or any type top ArrayList raw type.
         * reverse is also true. and that is not abnormal !!
         * we ar not adding boolean values to action2 method !!
         */

        ArrayList s = new ArrayList();  //now raw type
        s.add("abidin");
        s.add(10);
        s.add(true);

        action2(s);

        System.out.println(s);
    }

    public static void action(ArrayList list) {  //raw type. not generic. so we can add any type !

        list.add("kanu");
        list.add(10);
        list.add(false);
    }

    public static void action2(ArrayList<String> list) {  //generic type.  so we can add string type !

        list.add("kanu");
    }
}
