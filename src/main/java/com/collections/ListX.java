package com.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListX {
    /* List

     * ArrayList : array based implementation.
                   index based.
                   read fast,
                   add, insert, delete slow (moving items take time)

                   timing is about searching, doing the thing, then moving the rest items.
                   anyway. deep concept. check my c-sandbox for more detail where I implemented those collections in C language.
     * LinkedList: (doubly linked) list implementation
                   read, insert slow (compared to ArrayList).
                   add, insert, delete fast
     *
     * new ArrayList<>()  - mutable creation
     * List.of()  - immutable creation
     *
     */

    public static void main(String[] args) {
        //create and init
        List<String> words = List.of("Ali", "Veli", "Deli"); //of method creates immutable
        List<String> wordsA = new ArrayList<>(words);  //mutable, index based
        List<Integer> numbersA = IntStream.range(1, 10).boxed().collect(Collectors.toList());    //mutable, index based
        LinkedList<String> wordsL = new LinkedList<>(words); //mutable, linked (not index-based)

        List wordRaw = List.of("Ali", 'B', 12, 35);    //raw, heterogen

        //init
//        words.add("Mali"); immutable :)
        wordsA.add("Malibu");
        wordsA.add("Gal");

        numbersA.add(3,130);
        numbersA.add(1,70);
        numbersA.add(30);

        //display
        System.out.println(words);
        System.out.println(wordsA);
        System.out.println(numbersA);
        System.out.println(wordRaw);


        //raw types handles diff. types.
        System.out.println(wordRaw.get(0) instanceof String);

        //accessing
        //via index-based
        System.out.println(wordsA.get(0));
        System.out.println(wordsL.get(1));  //not index-based but accessible by index :) probably not efficient

        System.out.println(wordsA.indexOf("Deli"));

        //via, enhanced for, iterator or stream ..
//        for (String word : words) {
//            System.out.println(word);
//        }

        //via searching
        System.out.println(wordsA.contains("Mali"));

        //via, functional
        words.stream().forEach(System.out::println);
        words.stream().filter(word -> word.contains("Mali")).forEach(System.out::println);

        //sorting, Collections.sort() or ...sort(Comparator<? expands E>
        //class has to implement Comparable interface which overloads compareTo method
//        wordsA.sort();
//        wordRaw.sort(Comp);     //Comparator<? extends String>
//        numbersA.sort();        //Comparator<? extends Integer>

        Collections.sort(numbersA);  //List<T>, Comparator<? extends Object>
        System.out.println(numbersA);

        Collections.sort(wordsA);
        System.out.println(wordsA);

        //anonymous Comparator or lambda
        //Collections.sort can be replaced list.sort()
//        Collections.sort(wordsA, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.length() - o2.length();
//            }
//        });

        //Collections.sort can be replaced list.sort()
        //Comparator.comparingInt() can be used instead of (o1, o2) -> o1.length() - o2.length()
        //for more functional programming ready staffs, use Comparator. static methods
//        Collections.sort(wordsA, (o1, o2) -> o1.length() - o2.length());
//        wordsA.sort((o1, o2) -> o1.length() - o2.length());
        wordsA.sort(Comparator.comparingInt(String::length));

        System.out.println(wordsA);




    }
}
