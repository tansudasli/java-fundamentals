package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTest {

    @ParameterizedTest
    @DisplayName("Test max of int[]")
    @CsvSource({"true, 5", "false, 100"})
    public void testMaxOfInts(boolean initialized, int max) {

        //IntStream
        assertEquals(max, Arrays.stream(Variables.ints(initialized))
                .max()
                .orElse(-1));
    }


}
