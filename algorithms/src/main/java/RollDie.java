import java.security.SecureRandom;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Roll a die, and count the numbers in (1 to 6)
 *  
 * Face Frequency
 *    1         0
 *    2         0
 *    3         0
 *    4         2
 *    5         0
 *    6         1
 */
public class RollDie {

    static SecureRandom random = new SecureRandom();
    static int[] frequency = new int[7];
    static int count;

    private static void roll() {
        //so elegant
        System.out.printf("%s%10s%n", "Face", "Frequency");

        for (int f = 0; f < count; f++)
            ++frequency[1 + random.nextInt(6)];

        for (int face = 1; face < frequency.length; face++)
            System.out.printf("%4d%10d%n", face, frequency[face]);
    }

    private static void roll2() {
        System.out.printf("%s%10s%n", "Face", "Frequency");

        random.ints(count, 1, 7) //IntStream
                .boxed() //Stream<Integer>  , mapToObj(Integer::valueOf) is old style
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) //Map<Integer, Long>
                .forEach((face, frequency) -> System.out.printf("%4d%10d%n", face, frequency));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter roll count\n");
        count = scanner.nextInt();

        long begin = System.nanoTime();

        //switch to test
        //roll();
        roll2();

        long end = System.nanoTime() - begin;
        System.out.printf("took %d milliseconds", end/1000000);
    }
}