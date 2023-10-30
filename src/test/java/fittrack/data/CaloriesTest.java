package fittrack.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class CaloriesTest {

    @Test
    void constructor_zero_success() {
        Assertions.assertDoesNotThrow(() -> new Calories(0));
        Assertions.assertDoesNotThrow(() -> new Calories(1));
    }

    @Test
    void constructor_belowZero_assert() {
        Assertions.assertThrows(AssertionError.class, () -> new Calories(-1));
    }

    @Test
    void equals_same_true() {
        Assertions.assertEquals(new Calories(433.13), new Calories(433.13));
    }

    @Test
    void equals_different_false() {
        Assertions.assertNotEquals(null, new Calories(433.13));
        Assertions.assertNotEquals(new Height(433.13), new Calories(433.13));
        Assertions.assertNotEquals(new Calories(433.93), new Calories(433.13));
    }

    @Test
    void toString_cal433o41_str433kcal() {
        Assertions.assertEquals("433kcal", new Calories(433.41).toString());
    }
}
