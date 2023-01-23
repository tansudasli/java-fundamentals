import core.Variables;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumTest {

    @ParameterizedTest
    @EnumSource(value = Variables.SIZE.class,
                names = {"S", "XS"})      //enum + filtering w/ names attribute
    void testEnumSize(Variables.SIZE s) {

        EnumSet<Variables.SIZE> e = EnumSet.of(Variables.SIZE.S, Variables.SIZE.XS); //for multiple inputs

        assertTrue(e.contains(s));
    }
}
