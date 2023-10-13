package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.FinancialList;
import seedu.financialplanner.utils.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    public static final Storage INSTANCE = new Storage();
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

    public void load(FinancialList list, Ui ui, String filePath) throws FinancialPlannerException {
        LoadData.load(list, ui, filePath);
    }

    public void save(FinancialList list, String filePath) throws FinancialPlannerException {
        SaveData.save(list, filePath);
    }
}
