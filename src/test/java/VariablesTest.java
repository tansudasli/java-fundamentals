import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class VariablesTest {

    @Test
    void testCMTOINCH() {
        assertEquals(2.54, Variables.CM_TO_INCH);
    }

    void shout() {
        //appropriate usages
        long earthPopulation = 9000000000L;
        boolean done = false;
        int vacationDays = 15;
        double salary = 65000.00;
        double paperWidth = 8.5;

        // %0,5d   ==> 5 digit zero-padding $ one for , ==> 0,000
        // double = f, scientific = e
        // int & long = d,
        // boolean = b
        System.out.printf("%b %,d %d %f %n", done, earthPopulation, vacationDays, salary);
    }

    @Test
    @DisplayName("Double value -> int cast")
    void testCastDoubleToInt() {
        assertEquals(9, Variables.castToInt(9.943));
    }

    @ParameterizedTest
    @CsvSource( {"9, 8.545", "10, 9.943"})       //many input and many expected values
    @DisplayName("Double values -> long round")
    void testRoundDoubleToLong(double e, double v) {
        assertEquals(e, Variables.roundToLong(v));
    }




}
