package fittrack.data;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("AssertBetweenInconvertibleTypes")
class DateTest {

    @Test
    void constructor_throws() {
        assertThrows(DateTimeParseException.class, () -> new Date(""));
        assertThrows(DateTimeParseException.class, () -> new Date("10.30"));
        assertThrows(DateTimeParseException.class, () -> new Date("10/3"));
        assertThrows(DateTimeException.class, () -> new Date(2023, 13, 4));
        assertThrows(DateTimeException.class, () -> new Date(2023, 10, 41));
    }

    @Test
    void equals_same_true() {
        assertEquals(new Date("2023-10-29"), new Date("2023-10-29"));
        assertEquals(new Date("2023-10-30"), new Date(2023, 10, 30));
    }

    @Test
    void equals_different_false() {
        assertNotEquals(null, new Date("2023-10-31"));
        assertNotEquals(LocalDate.parse("2023-11-01"), new Date("2023-11-01"));
        assertNotEquals(new Date("2023-11-02"), new Date("2023-11-03"));
    }

    @Test
    void toString_20231104_20231104() {
        assertEquals("2023-11-04", new Date("2023-11-04").toString());
    }

    @Test
    void compareTo() {
        assertTrue(new Date("2023-11-05").compareTo(new Date("2023-11-06")) < 0);
        assertTrue(new Date("2023-11-08").compareTo(new Date("2023-11-07")) > 0);
    }
}