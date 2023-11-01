package seedu.financialplanner.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the saving of data to storage.
 */
public abstract class SaveData {
    private static final String FILE_PATH = "data/watchlist.json";
    private static final CashflowList cashflowList = CashflowList.getInstance();

    public static void save(String filePath) throws FinancialPlannerException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Cashflow entry : cashflowList.list) {
                fw.write(entry.formatString() + "\n");
            }
            fw.write(Budget.formatString() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new FinancialPlannerException("Error saving file.");
        }
    }

    public static void saveWatchList() {
        Ui ui = Ui.getInstance();
        WatchList wl = WatchList.getInstance();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            gson.toJson(wl.getStocks(), fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            ui.showMessage("Unable to save watchlist to file");
        }
    }
}
