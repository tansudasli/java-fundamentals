package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Uses text and shortText to get String[],
 * So that we can do some Array ops. at ArrayStringTest.java and benchmarks at ArrayBenchmark.java
 */
public class ArrayStringTest {


    //get String[] from text and shortText fields.
    public static Supplier<String[]> stringsGenerator = () -> StringTest.text.get().split("\\W+");
    public static Supplier<String[]> stringsShortGenerator = () -> StringTest.shortText.get().split("\\W+");
    public static Function<Boolean, String[]> strings = less -> less ? stringsShortGenerator.get()
                                                                     : ArrayStringTest.stringsGenerator.get();


    @ParameterizedTest
    @DisplayName("Test 1st of String[]")
    @CsvSource({"true, the", "false, it"})
    void testGeneratedStrings(Boolean less, String e) {
        assertEquals(e, ArrayStringTest.strings.apply(less)[0]
                .toLowerCase()
                .trim());
    }

    @ParameterizedTest
    @DisplayName("Test length of String[]")
    @CsvSource({"true, 8", "false, 999"})
    public void testLengthOfStrings(boolean less, int length) {
        assertEquals(length, ArrayStringTest.strings.apply(less).length);
    }

    public static BiFunction<Boolean, String, Long> searchWithStream =
            (less, target) -> Arrays.stream(ArrayStringTest.strings.apply(less))
                    .map(String::toLowerCase)
                    .map(String::trim)
                    .filter(s -> s.contains(target.toLowerCase()))
                    .count();

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, fox, 1", "false, fox, 38"})
    public void testFrequencyOfFox(boolean less, String target, long e) {
        assertEquals(e, ArrayStringTest.searchWithStream.apply(less, target));

        //Arrays.binarySearch( ArrayStringTest.strings.apply(false), "fox");

    }
    //todo: search stream vs search w/ regex
    //todo: make it more clean below via extract function refactoring technique

    public static BiFunction<Boolean, String, Long> searchWithRegex = (less, regex) -> {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(less ? StringTest.shortText.get() : StringTest.text.get());

        long count = 0;
        while (m.find())
            count++;

        return count;
    };

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, [F|f]ox, 1", "false, [F|f]ox, 38"})
    public void testFrequencyOfFoxWithRegex(boolean less, String regex, long e) {
        assertEquals(e,  searchWithRegex.apply(less, regex));
    }

    @ParameterizedTest
    @DisplayName("Show String[] data")
    @ValueSource(strings = {"true", "false"})
    public void shoutStrings(boolean less) {

        System.out.println(
                Arrays.stream(ArrayStringTest.strings.apply(less))
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
