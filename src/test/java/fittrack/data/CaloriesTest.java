package fittrack.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class CaloriesTest {

    @Test
    void constructor_zero_success() {
        assertDoesNotThrow(() -> new Calories(0));
        assertDoesNotThrow(() -> new Calories(1));
    }

    @Test
    void constructor_belowZero_assert() {
        assertThrows(AssertionError.class, () -> new Calories(-1));
    }

    @Test
    void equals_same_true() {
        assertEquals(new Calories(433.13), new Calories(433.13));
    }

    @Test
    void equals_different_false() {
        assertNotEquals(null, new Calories(433.13));
        assertNotEquals(new Height(433.13), new Calories(433.13));
        assertNotEquals(new Calories(433.93), new Calories(433.13));
    }

    @Test
    void toString_433o41_433kcal() {
        assertEquals("433kcal", new Calories(433.41).toString());
    }
}