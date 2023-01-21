import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Calculate unique char counts.
 */
public class CharacterCounter {

    private final static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        /* System.in is the keyboard itself */
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 1 word !!!!");
        String param = in.next();

        //todo : use stream

//        Stream.of(param.toCharArray())
//                .map(c -> 1)
//                .reduce(1, Integer::sum)
//                .forEach(System.out::println);


        for (char c: param.toCharArray()) {

            //map.put(c, map.getOrDefault(c, 0) + 1);
            map.merge(c, 1, Integer::sum);
//            if (!map.containsKey(c))
//                map.put(c, 1);
//            else {
//                int value = map.get(c);
//                map.put(c, ++value);
//            }
        }

        System.out.printf("%d { %d } unique chars of %s%n", map.size(),
                param.length(),
                param);
    }
}