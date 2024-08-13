//@@woodenclock
package seedu.wildwatch.entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntryTest {
    private Entry entry;

    @BeforeEach
    void setUp() {
        entry = new Entry("01-01-2020", "Lion", "Simba", "King of the Jungle");
    }

    @Test
    void constructor_validInputs_initializesCorrectly() {
        LocalDate expectedDate = LocalDate.of(2020, 1, 1);
        assertEquals(expectedDate, entry.getDate());
        assertEquals("Lion", entry.getSpecies());
        assertEquals("Simba", entry.getName());
        assertEquals("King of the Jungle", entry.getRemark());
    }

    @Test
    void getters_whenCalled_returnCorrectValues() {
        assertEquals(LocalDate.of(2020, 1, 1), entry.getDate());
        assertEquals("Lion", entry.getSpecies());
        assertEquals("Simba", entry.getName());
        assertEquals("King of the Jungle", entry.getRemark());
    }

    @Test
    void setters_whenCalled_updateValues() {
        entry.setDate("02-02-2020");
        entry.setSpecies("Elephant");
        entry.setName("Dumbo");
        entry.setRemark("Loves to fly");

        assertEquals(LocalDate.of(2020, 2, 2), entry.getDate());
        assertEquals("Elephant", entry.getSpecies());
        assertEquals("Dumbo", entry.getName());
        assertEquals("Loves to fly", entry.getRemark());
    }

    @Test
    void equals_differentEntries_returnsFalse() {
        Entry anotherEntry = new Entry("01-01-2020", "Tiger", "Shere Khan",
                "Lurks in the shadows");
        assertFalse(entry.equals(anotherEntry));
    }

    @Test
    void equals_sameEntries_returnsTrue() {
        Entry sameEntry = new Entry("01-01-2020", "Lion", "Simba", "King of the Jungle");
        assertTrue(entry.equals(sameEntry));
    }
}
