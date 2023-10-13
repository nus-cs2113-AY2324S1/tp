package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.FinancialList;

import java.io.FileWriter;
import java.io.IOException;

public abstract class SaveData {
    public static void save(FinancialList financialList, String filePath) throws FinancialPlannerException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Cashflow entry : financialList.list) {
                fw.write(entry.formatString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FinancialPlannerException("Error saving file.");
        }
    }
}
