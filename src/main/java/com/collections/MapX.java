package com.collections;

import java.util.*;

import static java.util.Arrays.stream;

public class MapX {
    /*
     * Map (k, v)
     *
     * - HashMap: unsorted, unordered, allows NULL value.
     * - HashTable: synced, thread-safe, unsorted, unordered
     * - LinkedHashMap: insert-order preserved, (so insertion, deletion a bit slower than HashMap)
     * - TreeMap: sorted (default natural order)
     *
     *
     */

    public static void main(String[] args) {

        Map<String, Integer> map = Map.of("a01234h", 2, "e5YH778", 4,
                                          "ha782",1, "b83h8ihs", 3);

        Map<String, Integer> hashMap = new HashMap<>(map);

        System.out.println(map.get("01234h"));
        System.out.println(map.size());
        System.out.println(map);

        map.forEach((k,v) -> System.out.println(k + ":" + v));

        System.out.println(map.containsValue(4));

        //Map.of is immutable, so below ain't work
//        Integer x = map.computeIfPresent("01234h", (k, v) -> v * v)
//        System.out.println(x);

        System.out.println(hashMap);
        hashMap.put("abc567", 67);
        hashMap.computeIfPresent("01234h", (k, v) -> v * v * v);
        System.out.println(hashMap);

        //insert-order preserved
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>(map);
        System.out.println(linkedHashMap);

        //sorted-order
        TreeMap<String, Integer> treeMap = new TreeMap<>(map);
        TreeMap<String, Integer> treeMap2 = new TreeMap<>(Comparator.reverseOrder());  //sorts by key !
        TreeMap<String, Integer> treeMap3 = new TreeMap<>(Comparator.comparing(String::length).reversed());

        treeMap2.putAll(map);
        treeMap3.putAll(map);

        System.out.println("treemap: " + treeMap);
        System.out.println(treeMap2);
        System.out.println(treeMap3);

        //some methods specific to maps (comes w/ navigable interface)
        System.out.println(treeMap.higherKey("e5YH778"));
        System.out.println(treeMap.ceilingKey("e5YH778"));

        String line = "This is not me, Me is not me";

        Map<Character, Integer> occurrences = new HashMap<>();
        for (Character ch: line.toLowerCase(Locale.ROOT).toCharArray()) {

            if (occurrences.containsKey(ch)) {
                occurrences.put(ch, occurrences.get(ch) + 1);
            } else {
                occurrences.put(ch, 1);
            }
        }

        System.out.println(occurrences);

    }

}
