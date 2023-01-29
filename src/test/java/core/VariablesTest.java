package core;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;


@FunctionalInterface
interface maxInterface {
    /*
    1 define an interface and a function (func(...))
    2 define a variable (interface m) and  lambda definition + get a stream !
    3 call m.func(...)
     */
    double max(double...args);
    /*
    1 If interface method is default, must have a method body
    2 If interface method is static, must have a method body
     */

}

/**
 * general variable concepts
 */
public class VariablesTest {

    public static final double CM_TO_INCH = 2.54;  //if final, make it static :)
    public static Function<Double, Integer> castToInt = Double::intValue;
    public static Function<Double, Long> roundToLong = Math::round;


    //VarArgs
    public static maxInterface m = (x) -> Arrays.stream(x).max().orElseThrow();
    @ParameterizedTest
    @CsvSource({"56, 2829829.04"})
    @DisplayName("Finding max double w/ VarArgs function")
    void testMax(Double e1, Double e2) {

        assertEquals(e1, m.max(12, 34, 25, 56));
        assertEquals(e2, m.max(12, 34, 25, 56, 75, 789, 9999, 1899,
                2829829.04, 2829829, 33, 33, 33333, 8989));
    }

    @Test
    void testCMTOINCH() {
        assertEquals(2.54, CM_TO_INCH);
    }

    @Test
    @DisplayName("Appropriate usages of long, double, boolean...")
    void shout() {
        long earthPopulation = 9000000000L;
        boolean done = false;
        int vacationDays = 15;
        double salary = 65000.00;
//        double paperWidth = 8.5;

        // %0,5d   ==> 5 digit zero-padding $ one for , ==> 0,000
        // double = f, scientific = e
        // int & long = d,
        // boolean = b
        System.out.printf("Some of variables....%b %,d %d %f \n", done, earthPopulation, vacationDays, salary);
    }

    @Test
    @DisplayName("Double value -> Integer cast")
    void testCastDoubleToInt() {
        assertEquals(9, castToInt.apply(9.943));
    }

    @ParameterizedTest
    @CsvSource( {"9, 8.545", "10, 9.943"})       //many input and many expected values
    @DisplayName("Double values -> Long round")
    void testRoundDoubleToLong(long e, double v) {
        assertEquals(e, roundToLong.apply(v));
    }


}
