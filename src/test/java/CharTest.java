import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CharTest {

    static char pi () {
        return '\u03C0'; //==π :not use char anymore due to UTF.
    }

    @Test
    @DisplayName("Test π == \u03C0")
    void testPi () {
        assertEquals('π', CharTest.pi());
    }



}
