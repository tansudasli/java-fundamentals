package com.core.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection {

    public static void main(String[] args) {

        List<String> s = List.of("Ali", "Veli", "Malik", "Salik"); //not resizable

        ArrayList<String> names = new ArrayList<>();  //resizable
        names.add("Ali");
        names.add("Kali");
        names.add("Alime");

        for (String o : names) {
            System.out.println(o);
        }

        //this usages can be changed w/ functional-programming using streams. and better optimized using reactive streams too.



    }
}
