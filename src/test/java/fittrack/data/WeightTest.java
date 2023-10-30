package fittrack.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class WeightTest {

    @Test
    void constructor_aboveZero_success() {
        assertDoesNotThrow(() -> new Weight(1));
    }

    @Test
    void constructor_zero_assert() {
        assertThrows(AssertionError.class, () -> new Weight(0));
        assertThrows(AssertionError.class, () -> new Weight(-1));
    }

    @Test
    void equals_same_true() {
        assertEquals(new Weight(76.54), new Weight(76.54));
    }

    @Test
    void equals_different_false() {
        assertNotEquals(null, new Weight(76.32));
        assertNotEquals(new Height(45.42), new Weight(45.32));
        assertNotEquals(new Weight(23.56), new Weight(23.54));
    }

    @Test
    void toString_w84o32_str84o3kg() {
        assertEquals("84.3kg", new Weight(84.32).toString());
    }
}
