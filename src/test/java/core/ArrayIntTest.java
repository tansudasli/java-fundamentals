package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayIntTest implements IVariable {


    @ParameterizedTest
    @DisplayName("Test 1st of less int[]")
    @CsvSource({"true, 3"})
    void testGeneratedInts(boolean less, int e) {
        assertEquals(e, ArrayIntTest.ints.apply(less)[0]);
    }

    @ParameterizedTest
    @DisplayName("Test max of less int[]")
    @CsvSource({"true, 102"})
    public void testMaxOfInts(boolean less, int max) {
        assertEquals(max, Arrays.stream(ArrayIntTest.ints.apply(less))
                .max()
                .orElseThrow());
    }

    @ParameterizedTest
    @DisplayName("Test length of int[]")
    @CsvSource({"true, 20", "false, 1000"})
    public void testLengthOfInts(boolean less, int e) {
        assertEquals(e, ArrayIntTest.ints.apply(less).length);
    }

    @ParameterizedTest
    @DisplayName("Show Int[] data")
    @ValueSource(booleans = {false, true})
    public void shoutInts(boolean less) {

        System.out.println(
                Arrays.stream(ArrayIntTest.ints.apply(less))
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(" ")));
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
    @ParameterizedTest
    @CsvSource({"1, true"})
    public void sortStreamInts(int e, boolean less) {
        assertEquals(e, Arrays.stream(ArrayIntTest.ints.apply(less))
                .sorted()
                .findFirst()
                .orElseThrow());

//        Arrays.sort(Variables.ints(initialized), Comparator.comparingInt( i -> ));
    }


}
