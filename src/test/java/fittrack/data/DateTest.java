package fittrack.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class DateTest {

    @Test
    void constructor_throws() {
        Assertions.assertThrows(DateTimeParseException.class, () -> new Date(""));
        Assertions.assertThrows(DateTimeParseException.class, () -> new Date("10.30"));
        Assertions.assertThrows(DateTimeParseException.class, () -> new Date("10/3"));
        Assertions.assertThrows(DateTimeException.class, () -> new Date(2023, 13, 4));
        Assertions.assertThrows(DateTimeException.class, () -> new Date(2023, 10, 41));
    }

    @Test
    void equals_same_true() {
        Assertions.assertEquals(new Date("2023-10-29"), new Date("2023-10-29"));
        Assertions.assertEquals(new Date("2023-10-30"), new Date(2023, 10, 30));
    }

    @Test
    void equals_different_false() {
        Assertions.assertNotEquals(null, new Date("2023-10-31"));
        Assertions.assertNotEquals(LocalDate.parse("2023-11-01"), new Date("2023-11-01"));
        Assertions.assertNotEquals(new Date("2023-11-02"), new Date("2023-11-03"));
    }

    @Test
    void toString_date20231104_str20231104() {
        Assertions.assertEquals("2023-11-04", new Date("2023-11-04").toString());
    }

    @Test
    void compareTo() {
        Assertions.assertTrue(new Date("2023-11-05").compareTo(new Date("2023-11-06")) < 0);
        Assertions.assertTrue(new Date("2023-11-08").compareTo(new Date("2023-11-07")) > 0);
    }
}
