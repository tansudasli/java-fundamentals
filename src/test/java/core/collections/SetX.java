package core.collections;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SetX {
    /*
     * Set
     *
     * - unique items, no index-based access
     *                 insertion-order       sorted-order
     * set                      x               x
     * HashSet                  x               x
     * LinkedHashSet           yes              x
     * TreeSet                  x              yes
     */

    public static void main(String[] args) {

        Set<String> set = Set.of("Ali", "Veli", "Malim", "Halim");
        Set<String> setHashed = new HashSet<>(set);
        Set<String> setLinkedHashed = new LinkedHashSet<>(set);
        Set<String> setTree = new TreeSet<>(set);

        //Set does not care about initialized order or search order !
        System.out.println(set);
        System.out.println(setHashed);
        System.out.println(setLinkedHashed);
        System.out.println(setTree);

        //insertion-order and sorted-order diffs.
        Set<Integer> hashed = new HashSet<>(); hashed.add(1); hashed.add(100); hashed.add(10); hashed.add(1000);
        Set<Integer> linkedHashed = new LinkedHashSet<>(); linkedHashed.add(1); linkedHashed.add(100); linkedHashed.add(10); linkedHashed.add(1000);
        Set<Integer> tree = new TreeSet<>(); tree.add(1); tree.add(100); tree.add(10); tree.add(1000);

        System.out.println(hashed);
        System.out.println(linkedHashed);
        System.out.println(tree);

        List<Character> ch = List.of('A', 'Z', 'K', 'M', 'A', 'B', 'F', 'Z');
        Set<Character> chSet = new LinkedHashSet<>(ch);

        System.out.println(chSet); //unique and insert-order preserved
        System.out.println(new TreeSet<Character>(ch));  //unique and sorted-order

        (ch.stream().collect(Collectors.toSet())).forEach(System.out::println);

        //treeset specific navigable interface functions
        TreeSet<Integer> t = new TreeSet<>(Set.of(1, 23, 245, 35, 24, 12, 34, 33));

        System.out.println(t.floor(30));  // the greatest number less than 30

        Predicate<Integer> floor30 = x -> x < 30;
        t.stream()     // the greatest number less than 30 via streams
                .sorted(Comparator.reverseOrder())
                .filter(floor30)   //v -> v <= 30
                .findFirst()
                .ifPresent(System.out::println);

    }
}
