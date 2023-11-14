package seedu.financialplanner.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Represents the saving of data to storage.
 */
public abstract class SaveData {
    private static final String FILE_PATH = "data/watchlist.json";
    private static final CashflowList cashflowList = CashflowList.getInstance();
    private static final ReminderList reminderList = ReminderList.getInstance();
    private static final WishList wishList = WishList.getInstance();

    public static void save(String filePath) throws FinancialPlannerException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Cashflow entry : cashflowList.list) {
                fw.write(entry.formatString() + "\n");
            }
            if (Budget.hasBudget()) {
                fw.write(Budget.formatString() + "\n");
            }
            for (int i = 0; i < reminderList.list.size(); i++) {
                fw.write(reminderList.list.get(i).formatString() + "\n");
            }
            for (int i = 0; i < wishList.list.size(); i++) {
                fw.write(wishList.list.get(i).formatString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FinancialPlannerException("Error saving file.");
        }
    }

    /**
     * Method to save the current watchlist to watchlist.json file
     *
     */
    public static void saveWatchList() {
        Ui ui = Ui.getInstance();
        WatchList wl = WatchList.getInstance();
        setHashCode(wl.getStocks());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            gson.toJson(wl.getStocks(), fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            ui.showMessage("Unable to save watchlist to file");
        }
    }

    private static void setHashCode(HashMap<String, Stock> stocks) {
        for (HashMap.Entry<String, Stock> stock : stocks.entrySet()) {
            stock.getValue().setHashCode();
        }
    }
}
