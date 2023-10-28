package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.utils.Ui;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class SaveData {
    private static final String FILE_PATH = "data/watchlist.txt";

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
        try {
            ObjectOutputStream watchListStocksOutput
                    = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

            WatchList wl = WatchList.getInstance();
            watchListStocksOutput.writeObject(wl.getStocks());

            watchListStocksOutput.close();
        } catch (IOException e) {
            ui.showMessage("Unable to save watchlist to file");
        }
    }
}
