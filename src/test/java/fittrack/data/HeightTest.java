package fittrack.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class HeightTest {

    @Test
    void constructor_aboveZero_success() {
        assertDoesNotThrow(() -> new Height(1));
    }

    @Test
    void constructor_zero_assert() {
        assertThrows(AssertionError.class, () -> new Height(0));
        assertThrows(AssertionError.class, () -> new Height(-1));
    }

    @Test
    void equals_same_true() {
        assertEquals(new Height(176.54), new Height(176.54));
    }

    @Test
    void equals_different_false() {
        assertNotEquals(null, new Height(176.32));
        assertNotEquals(new Weight(145.42), new Height(145.32));
        assertNotEquals(new Height(123.56), new Height(123.54));
    }

    @Test
    void toString_h184o32_str184o3cm() {
        assertEquals("184.3cm", new Height(184.32).toString());
    }

    @Test
    void calculateIdealWeight_h180_success() {
        double idealWeight180 = 50 + (0.91 * (180. - 152.4));
        assertEquals(idealWeight180, new Height(180.).calculateIdealWeight());
    }
}
