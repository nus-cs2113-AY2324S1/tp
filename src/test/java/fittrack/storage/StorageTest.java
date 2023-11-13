package fittrack.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class StorageTest {

    @Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> new Storage(null, null, null, null));
    }

    @Test
    public void save_nullAddressBook_exceptionThrown() {
        Storage storage = new Storage();
        assertThrows(AssertionError.class, () -> storage.saveProfile(null));
        assertThrows(AssertionError.class, () -> storage.saveMeals(null));
        assertThrows(AssertionError.class, () -> storage.saveWorkouts(null));
    }
}
