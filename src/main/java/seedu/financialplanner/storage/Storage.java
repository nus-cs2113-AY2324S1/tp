package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * Represents the saving and loading of data.
 */
public class Storage {
    private static Storage storage = null;
    private final Path path = Paths.get("data");

    private Storage() {
        if (!Files.exists(path)) {
            try {
                Ui.getInstance().showMessage("Directory doesn't exist. Creating directory...");
                Files.createDirectory(path);
            } catch (IOException e) {
                Ui.getInstance().showMessage("Error creating directory: " + e.getMessage());
            }
        }
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void load(String filePath, LocalDate date) throws FinancialPlannerException {
        LoadData.load(filePath, date);
    }

    public void save(String filePath) throws FinancialPlannerException {
        SaveData.save(filePath);
        SaveData.saveWatchList();
    }

}
