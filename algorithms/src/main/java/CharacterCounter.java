import java.util.HashMap;
import java.util.Scanner;

/**
 * Calculate unique char counts.
 */
public class CharacterCounter {

    private final static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {

        /* System.in is the keyboard itself */
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 1 word");
        String param = in.next();

        // yazamadım stream ile:)
         //Map<Character, Long> chars = Arrays.asList(param.toCharArray()).stream().forEach();

        for (char c: param.toCharArray()) {

            //map.put(c, map.getOrDefault(c, 0) + 1);
            map.merge(c, 1, Integer::sum);

            /*
            if (!map.containsKey(c))
                map.put(c, 1);
            else {
                int value = map.get(c);
                map.put(c, ++value);
            }*/
        }
        //System.out.println(map.get('u'));

        System.out.println(map.size() + " unique chars of " + param);
    }
}