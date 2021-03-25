package com.collections;

import java.util.HashMap;
import java.util.Map;

public class MapX {
    /*
     * Map (k, v)
     *
     * - HashMap: unsorted, unordered, allows NULL value.
     * - HashTable: synced, thread-safe, unsorted, unordered
     * - LinkedHashMap: order preserved, (so insertion, deletion a bit slower than HashMap)
     * - TreeMap: sorted
     *
     *
     */

    public static void main(String[] args) {

        Map<String, Integer> map = Map.of("01234h", 2, "e5YH778", 4,
                                          "ha782",1, "83h8ihs", 3);

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


    }

}
