package fittrack.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;

import fittrack.storage.Storage.StorageOperationException;


public class StorageTest {

    public static Path testFolder;
    private static final String TEST_DATA_FOLDER = "test/data/StorageFileTest";

    @Test
    public void constructor_nullFilePath_exceptionThrown() {
        assertThrows(NullPointerException.class,
                () -> new Storage(null, null, null));
    }

    @Test
    public void load_invalidProfileFormat_exceptionThrown() throws Exception {
        // The file contains valid txt data, but does not match the format
        Storage storage = getStorage("InvalidProfileData.txt",
                "InvalidMealListData.txt", "InvalidWorkoutListData.txt");
        assertThrows(StorageOperationException.class, () -> storage.profileLoad());
    }

    @Test
    public void save_nullAddressBook_exceptionThrown() throws Exception {
        Storage storage = new Storage();
        assertThrows(NullPointerException.class, () -> storage.saveProfile(null));
        assertThrows(NullPointerException.class, () -> storage.saveMeals(null));
        assertThrows(NullPointerException.class, () -> storage.saveWorkouts(null));
    }

    private Storage getStorage(String profileFileName, String mealFileName, String workoutFileName)
            throws Exception {
        return new Storage(TEST_DATA_FOLDER + "/" + profileFileName,
                TEST_DATA_FOLDER + "/" + mealFileName,
                TEST_DATA_FOLDER + "/" + workoutFileName);
    }
}
