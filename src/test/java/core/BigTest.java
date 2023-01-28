package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@FunctionalInterface
interface addInterface {
    /*
    1 define an interface and a function (func(...))
    2 define a variable (interface m) and  lambda definition + get a stream !
    3 call m.func(...)
     */
    BigInteger addPrecise(BigInteger...args);
}

public class BigTest {

    // for more precision, BigInteger...
    public static addInterface p = (big) -> Arrays.stream(big)
            .reduce(BigInteger.ZERO, BigInteger::add);


    @ParameterizedTest
    @CsvSource({"1, 1, 0", "300000, 200000, 100000"})
    public void testAdd(int e, int x, int y) {

        assertEquals(BigInteger.valueOf(e),
                p.addPrecise(BigInteger.valueOf(x), BigInteger.valueOf(y)));
    }

    @ParameterizedTest
    @DisplayName("Multiple parameters added")
    @CsvSource({"601000, 1000, 300000, 200000, 100000"})
    public void testAdd(int e, int x, int y, int z, int k) {

        assertEquals(BigInteger.valueOf(e),
                p.addPrecise(BigInteger.valueOf(x),
                        BigInteger.valueOf(y),
                        BigInteger.valueOf(z),
                        BigInteger.valueOf(k)));

    }

    // == not used to compare!
    @ParameterizedTest
    @DisplayName("BigInteger comparison")
    @CsvSource({"0, 50, 50"})
    public void testCompare(int e, int x, int y) {

        assertEquals(e, BigInteger.valueOf(x).compareTo(BigInteger.valueOf(y)));
        assertEquals(BigInteger.valueOf(x), BigInteger.valueOf(y));
        assertFalse(BigInteger.valueOf(x).isProbablePrime(90));
    }
}
