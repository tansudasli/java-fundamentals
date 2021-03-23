package com.collections;

import java.util.HashSet;
import java.util.Set;

public class SetX {
    /*
     * Set
     *
     * - unique items, no index-based access
     *
     *
     *
     *
     *
     */

    public static void main(String[] args) {
        Set<String> set = Set.of("Ali", "Veli", "Malim", "Halim");
        Set<String> setHashed = new HashSet<>(set);

        //Set does not care about initialized order ! Use hashset if it is critical.
        System.out.println(set);
        System.out.println(setHashed);
    }
}
