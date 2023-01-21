import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 *  to summarize the number of occurrences of each word in a file,
 *  then display a summary of the words in alphabetical order grouped by starting letter.
 *
 *  This is commonly called a concordance:
 *
 */
public class Concordances {

    public static void main(String[] args) {
        //System.out.println(Paths.get("out/production/java fundamentals/com/core/example/algorithms/concordances.txt").toAbsolutePath());

        String path = "out/production/java fundamentals/com/core/example/algorithms/";

        try {
            //count each occurrence
            Map<String, Long> wordsCount = Files.lines(Paths.get(path + "concordances.txt"))
                                                .flatMap(Pattern.compile("\\W+")::splitAsStream)
                                                .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));

            /* another split way
            Files.lines(Paths.get(path + "concordances.txt")).flatMap(line -> Stream.of(line.split("\\W+")))
            .forEach(System.out::println);
            */

            //.forEach((s, aLong) -> System.out.println(s + " : " + aLong))

            //display words grouped by starting letter
            wordsCount.entrySet() //Interface Map does not provide stream: R : Set<Map.Entry<String,Long>>
                      .stream()
                      .collect(Collectors.groupingBy(entry -> entry.getKey().charAt(0), TreeMap::new, Collectors.toList())) //R : Map<Character, List<Map.Entry<String, Long>>>
                      .forEach((letter, wordList) -> {
                          System.out.printf("%C%n", letter);

                          wordList.forEach(word -> System.out.printf("%13s:  %d%n", word.getKey(), word.getValue()));
                      });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
