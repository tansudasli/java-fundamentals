package core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class BigTest {

    @ParameterizedTest
    @CsvSource({"1, 1, 0", "300000, 200000, 100000"})
    public void testAdd(int e, int x, int y) {

        assertEquals(BigInteger.valueOf(e), Variables.addPrecise(BigInteger.valueOf(x), BigInteger.valueOf(y)));
    }

    // == not used to compare!
    @ParameterizedTest
    @CsvSource({"0, 50, 50"})
    public void testCompare(int e, int x, int y) {

        assertEquals(e, BigInteger.valueOf(x).compareTo(BigInteger.valueOf(y)));
        assertEquals(BigInteger.valueOf(x), BigInteger.valueOf(y));
        assertFalse(BigInteger.valueOf(x).isProbablePrime(90));
    }
}
