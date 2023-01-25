package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTest {

    @ParameterizedTest
    @DisplayName("Test max of int[]")
    @CsvSource({"true, 5", "false, 10000"})
    public void testMaxOfInts(boolean initialized, int max) {

        assertEquals(max, Arrays.stream(Variables.ints(initialized))
                .max()
                .orElse(-1));
    }

    @ParameterizedTest
    @DisplayName("Test length of String[]")
    @CsvSource({"true, 8", "false, 122"})
    public void testLengthOfStrings(boolean initialized, int length) {

        assertEquals(length, Variables.strings(initialized).length);
    }

    @ParameterizedTest
    @DisplayName("Test length of String[]")
    @CsvSource({"true, already", "false, hounds"})
    public void testLengthOfFiveStrings(boolean initialized, String e) {

//        String[] -> stream         Stream<String>
//                      ..filter
//                      ..map
//                      ..findFirst  Optional<String>

        assertEquals(e, Arrays.stream(Variables.strings(initialized))
                .filter(s -> s.length() > 5)
                .map(String::trim)
                .findFirst()
                .orElse(null));
    }

}
