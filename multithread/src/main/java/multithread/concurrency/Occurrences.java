package multithread.concurrency;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

public class Occurrences {

    public static void main(String[] args) {
        String line = "This was not me, Me was not me. Then who was I am?";

        //in case of collections, we must replace with thread safe versions
        //such as ConcurrentHashMap or
        //second, Map<Character, Integer> must be AtomicInteger or LongAdder
        //third, occur is a local variable. so it is not shared by threads
//        Map<Character, Integer> occur = new HashMap<>();   //not thread safe

//        Map<Character, LongAdder> occur = new Hashtable<>(); //less performant
        ConcurrentMap<Character, LongAdder> occur = new ConcurrentHashMap<>();

        for (Character ch: line.toLowerCase(Locale.ROOT).toCharArray()) {

            //below is not thread safe.
//            if (occur.containsKey(ch)) {
//                occur.put(ch, occur.get(ch) + 1);
//            } else {
//                occur.put(ch, 1);
//            }

            //below is partially thread-safe!!
            // .get() is not still thread safe in Hashtable. it comes from Map interface
            //so, ConcurrentHashmap provide more methods and more thread-safe
//            LongAdder count = occur.get(ch);
//            if ( count == null)
//                count = new LongAdder();
//
//            count.increment();
//            occur.put(ch, count);

            occur.computeIfAbsent(ch, c -> new LongAdder())
                 .increment();
        }


        System.out.println(occur);
    }
}
