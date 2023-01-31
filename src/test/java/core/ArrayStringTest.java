package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Uses text and shortText to get String[] as words,
 * So that we can do some Array ops. at ArrayStringTest.java and benchmarks at ArrayBenchmark.java
 */
public class ArrayStringTest {


    //get String[] from text and shortText fields.
    public static Supplier<String[]> textAsWords = () -> StringTest.text.get().split("\\W+");
    public static Supplier<String[]> shortTextAsWords = () -> StringTest.shortText.get().split("\\W+");
    public static Function<Boolean, String[]> words = less -> less ? shortTextAsWords.get()
                                                                   : ArrayStringTest.textAsWords.get();


    @ParameterizedTest
    @DisplayName("Test 1st word of String[]")
    @CsvSource({"true, the", "false, it"})
    void testFirstElementOfWords(Boolean less, String e) {
        assertEquals(e, ArrayStringTest.words.apply(less)[0].toLowerCase().trim());
    }

    @ParameterizedTest
    @DisplayName("Test count of String[]")
    @CsvSource({"true, 8", "false, 999"})
    public void countOfWords(boolean less, int length) {
        assertEquals(length, ArrayStringTest.words.apply(less).length);
    }

    public static BiFunction<Boolean, String, Long> frequencyOf =
            (less, target) -> Arrays.stream(ArrayStringTest.words.apply(less))
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .filter(s -> s.contains(target.toLowerCase()))
                    .count();

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, fox, 1", "false, fox, 38"})
    public void frequencyOfFox(boolean less, String target, long e) {
        assertEquals(e, ArrayStringTest.frequencyOf.apply(less, target));

    }

    //todo: make it more clean below via extract function refactoring technique

    //after java9, works better. use results as Stream!
    public static BiFunction<Boolean, String, Long> frequencyOfRegex =
            (less, regex) -> Pattern.compile(regex)
                    .matcher(Arrays.toString(words.apply(less)))
                    .results()
                    .count();

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, [F|f]ox, 1", "false, [F|f]ox, 38"})
    public void frequencyOfFoxWithRegex(boolean less, String regex, long e) {
        assertEquals(e,  frequencyOfRegex.apply(less, regex));
    }

    @ParameterizedTest
    @DisplayName("Show String[] data")
    @ValueSource(strings = {"true", "false"})
    public void shoutStrings(boolean less) {

        System.out.println(
                Arrays.stream(ArrayStringTest.words.apply(less))
                        .limit(10)
                        .collect(Collectors.joining(" ")));

//        Arrays.stream(Variables.stringsDataGenerator())
//                .forEach(s -> System.out.printf("%s ", s));

//        for (String x : Variables.stringsDataGenerator())
//            System.out.format("%s ", x) ;

    }





    /*
     * for (variable : collection) statement
     *
     * Iterable interface has to be implemented, such as ArrayList
     *
     * Arrays.copyOf(arrayX, 2 * arrayXLength);  So, old array values + added new elements to the new easily.
     * Arrays.sort(), QuickSort.. it returns void!
     * Arrays.binarySearch(), BinarySearch algorithm, it returns a value!
     * Arrays.stream.sorted() is also possible!!
     *
     * double[][] balances = new double[3][4]; multi-dimensional arrays. Multidimensional arrays are faked as “arrays of arrays.” in Java
     * */



}
