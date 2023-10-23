package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static Storage storage = null;
    private final Path path = Paths.get("data");

    private Storage() {
        if (!Files.exists(path)) {
            try {
                System.out.println("Directory doesn't exist. Creating directory...");
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
    }

    public static Storage getInstance() {
        if (storage == null) {
            storage = new Storage();
        }
        return storage;
    }

    public void load(String filePath) throws FinancialPlannerException {
        LoadData.load(filePath);
    }

    public void save(String filePath) throws FinancialPlannerException {
        SaveData.save(filePath);
    }
}
