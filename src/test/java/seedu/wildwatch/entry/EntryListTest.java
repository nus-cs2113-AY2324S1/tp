//@@woodenclock
package seedu.wildwatch.entry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EntryListTest {

    private final Entry entry1 = new Entry("02-03-2023", "Annam Leaf Turtle", "Ariel",
            "Injured left flipper");
    private final Entry entry2 = new Entry("04-04-2023", "Bengal Tiger", "Rajah",
            "Missing canine tooth");

    @BeforeEach
    public void setUp() {
        // This is executed before each test.
        EntryList.clearEntryList();
    }

    @AfterEach
    public void tearDown() {
        // This is executed after each test.
        EntryList.clearEntryList();
    }

    @Test
    public void addEntry_singleEntry_entryAdded() {
        EntryList.addEntry(entry1);
        assertFalse(EntryList.isArrayEmpty(), "EntryList should not be empty after adding an entry.");
        assertEquals(1, EntryList.getArraySize(), "EntryList size should be 1 after adding an entry.");
    }

    @Test
    public void clearEntryList_noInput_entryListCleared() {
        EntryList.clearEntryList();
        assertTrue(EntryList.getArray().isEmpty());
    }

    @Test
    public void deleteEntry_singleEntry_entryRemoved() {
        EntryList.addEntry(entry1);
        EntryList.deleteEntry(0);
        assertTrue(EntryList.isArrayEmpty(), "EntryList should be empty after deleting the entry.");
    }

    @Test
    public void editEntry_validIndex_entryUpdated() {
        EntryList.addEntry(entry1);
        EntryList.addEntry(entry2);
        EntryList.editEntry(0, entry2);
        Entry retrievedEntry = EntryList.getEntry(0);
        assertEquals(entry2, retrievedEntry, "The retrieved entry should be the updated entry.");
    }

    @Test
    public void getArray_noInput_arrayListReturned() {
        EntryList.addEntry(entry1);
        EntryList.addEntry(entry2);
        ArrayList<Entry> entries = EntryList.getArray();
        assertNotNull(entries);
        assertEquals(2, entries.size());
        assertTrue(entries.contains(entry1));
        assertTrue(entries.contains(entry2));
    }

    @Test
    public void getArraySize_noInput_arraySizeReturned() {
        EntryList.addEntry(entry1);
        EntryList.addEntry(entry2);
        assertEquals(2, EntryList.getArraySize());
    }

    @Test
    public void getEntry_validIndex_correctEntryReturned() {
        EntryList.addEntry(entry1);
        Entry retrievedEntry = EntryList.getEntry(0);
        assertEquals(entry1, retrievedEntry, "The retrieved entry should be the same as the one added.");
    }

    @Test
    public void getEntryDate_validIndex_correctDateReturned() {
        EntryList.addEntry(entry1);
        LocalDate date = EntryList.getEntryDate(0);
        assertEquals(LocalDate.parse("2023-03-02"), date, "The date of the entry should be 2023-07-07.");
    }

    @Test
    public void getEntrySpecies_validIndex_correctSpeciesReturned() {
        EntryList.addEntry(entry1);
        String species = EntryList.getEntrySpecies(0);
        assertEquals("Annam Leaf Turtle", species);
    }

    @Test
    public void getEntryName_validIndex_correctNameReturned() {
        EntryList.addEntry(entry1);
        String name = EntryList.getEntryName(0);
        assertEquals("Ariel", name);
    }

    @Test
    public void getEntryRemark_validIndex_correctRemarkReturned() {
        EntryList.addEntry(entry1);
        String remark = EntryList.getEntryRemark(0);
        assertEquals("Injured left flipper", remark);
    }

    @Test
    public void isArrayEmpty_emptyList_trueReturned() {
        assertTrue(EntryList.isArrayEmpty(), "EntryList should be empty initially.");
    }
}
