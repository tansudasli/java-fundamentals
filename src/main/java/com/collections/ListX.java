package com.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//dummy class
class Student {
    public int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
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
     * Use Comparator... static methods for functional staffs
     *
     * get a stream list.stream(). then use functional staffs. instead of classical for loops, sorting
        searching etc..
     *
     * to compare a Class (Student such as), you have to implement Comparable interface.
       But if u use functional programming style you dont need. Just use Comparator functions
       whether ..stream().sorted(Comparable...) or ..sort(Comparator...)
     */

    public static void main(String[] args) {
        //create and init
        List<String> words = List.of("Ali", "Yelli", "Deli", "Ho"); //of method creates immutable
        List<String> wordsA = new ArrayList<>(words);  //mutable, index based
        List<Integer> numbersA = IntStream.range(1, 10).boxed().collect(Collectors.toList());    //mutable, index based
        LinkedList<String> wordsL = new LinkedList<>(words); //mutable, linked (not index-based)

        List<Student> students = new ArrayList<>(List.of(new Student(1, "Ali"),
                                                         new Student(3, "Veli"),
                                                         new Student(2, "Halim")));

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

        students.sort(Comparator.comparing(student -> student.id));
        System.out.println(students);


        //via, functional
        words.stream().forEach(System.out::println);
        words.stream().filter(word -> word.contains("Mali")).forEach(System.out::println);
        words.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);

        //i did not implemented Comparator interface :) in Student class !! no need in functional programming
        students.stream().sorted(Comparator.comparing(student -> student.id)).forEach(System.out::println);



    }
}
