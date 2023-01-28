package core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.EnumSet;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumTest {

    public enum SIZE {XS, S, M, L, XL, XXL}

    //for multiple enums, use EnumSet
    public static Supplier<EnumSet<SIZE>> SIZEs = () -> EnumSet.of(SIZE.S, SIZE.XS);

    @ParameterizedTest
    @EnumSource(value = SIZE.class,
                names = {"S", "XS"})      //enum + filtering w/ names attribute
    void testEnumSize(SIZE s) {
        assertTrue(SIZEs.get().contains(s));
    }
}
