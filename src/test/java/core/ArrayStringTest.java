package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Uses text and shortText to get String[] as words,
 * So that we can do some Array ops. at java and benchmarks at ArrayBenchmark.java
 */
public class ArrayStringTest implements  IVariable {



    @ParameterizedTest
    @DisplayName("Show String[] data")
    @ValueSource(strings = {"true", "false"})
    public void shoutAt(boolean less) {

        System.out.println(
                Arrays.stream(words.apply(less))
                        .limit(10)
                        .collect(Collectors.joining(" "))
                        .concat(" ..."));
    }

    @ParameterizedTest
    @DisplayName("Test 1st word of String[]")
    @CsvSource({"true, the", "false, it"})
    void testFirstElementOfWords(Boolean less, String e) {
        assertEquals(e, words.apply(less)[0].toLowerCase().trim());
    }

    @ParameterizedTest
    @DisplayName("Test count of String[]")
    @CsvSource({"true, 8", "false, 1002"})
    public void countOfWords(boolean less, int length) {
        assertEquals(length, words.apply(less).length);
    }

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, fox, 1", "false, fox, 38"})
    public void frequencyOfFox(boolean less, String target, long e) {
        assertEquals(e, frequencyOf.apply(less, target));

    }

    @ParameterizedTest
    @DisplayName("Test frequency of 'fox' in String[]")
    @CsvSource({"true, [F|f]ox, 1", "false, [F|f]ox, 38"})
    public void frequencyOfFoxWithRegex(boolean less, String regex, long e) {
        assertEquals(e,  frequencyOfRegex.apply(less, regex));
    }


    /*
     * for (variable : collection) statement
     *
     * Iterable interface has to be implemented, such as ArrayList
     *
     * Arrays.copyOf(arrayX, 2 * arrayXLength);  So, old array values + added new elements w/ null or empty values
     * then, Array.fill() to inject meaningful values
     *
     * Arrays.sort(), QuickSort.. it returns void!
     * Arrays.binarySearch(), BinarySearch algorithm, it returns a value!
     * Arrays.stream.sorted() is also possible!!
     *
     * double[][] balances = new double[3][4]; multi-dimensional arrays. Multidimensional arrays are faked as “arrays of arrays.” in Java
     * */



}
