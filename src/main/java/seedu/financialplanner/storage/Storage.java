package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.FinancialList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Storage {
    private final Path path = Paths.get("data");

    public Storage() {
        if (!Files.exists(path)) {
            try {
                System.out.println("Directory doesn't exist. Creating directory...");
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
    }

    public void load(FinancialList list) {

    }

    public void save(FinancialList list) throws FinancialPlannerException {
        SaveData.save(list);
    }
}
