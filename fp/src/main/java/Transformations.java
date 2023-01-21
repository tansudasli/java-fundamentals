import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Transformations {

    static int[] v = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};       //Arrays.stream(v)
    static Integer[] vO = {2, 2, 9, 5, 5, 0, 3, 7, 1, 1 , 4, 4, 8, 6};   //Arrays.stream(vO)
    static List<Integer> vL = Arrays.asList(vO);            //vL.stream()

    public static void main(String[] args) {

        //List - set - map transformations

        //to List
        List<Integer> evens = vL.stream()
                                .filter(e -> e % 2 == 0)
                                .collect(Collectors.toList());

        evens.forEach(e -> System.out.print(e + " "));

        //to Set
        Set<Integer> uniqueSortedEvens = vL.stream()
                                           .filter(e -> e % 2 == 0)
                                           .collect(Collectors.toSet());

        System.out.println("");
        uniqueSortedEvens.forEach( e -> System.out.print(e + " "));

        System.out.println("");

        //to Map, in 2 ways !!
        //requires a unique set
        //0, 0 | 2, 2 | 4, 4 | 6, 6 | 8, 8 |
        Map<Integer, Integer>
                mapOfEvens = uniqueSortedEvens.stream()
                                              .filter(e -> e % 2 == 0)
                                              .collect(Collectors.toMap(e -> e,    //k
                                                                                e -> e));  //v

        mapOfEvens.forEach((k, v) -> System.out.print(k + ", " + v + " | "));

        System.out.println("");

        //below creates a Map, but it also manages collusion, too !!
        //0, [0] | 2, [2, 2] | 4, [4, 4] | 6, [6] | 8, [8] |
        Map<Integer, List<Integer>>
                mapOfEvens2 = vL.stream()
                                .filter(e -> e % 2 == 0)
                                .collect(Collectors.groupingBy(e -> e));   //groupingBy returns a Map

        mapOfEvens2.forEach((k, v) -> System.out.print(k + ", " + v + " | "));



    }
}
