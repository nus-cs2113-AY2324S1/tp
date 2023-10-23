package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;

import java.io.FileWriter;
import java.io.IOException;

public abstract class SaveData {
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
}
