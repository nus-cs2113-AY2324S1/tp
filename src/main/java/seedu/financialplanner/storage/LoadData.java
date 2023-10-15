package seedu.financialplanner.storage;

import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.utils.Ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public abstract class LoadData {
    public static void load(CashflowList cashflowList, Ui ui, String filePath) throws FinancialPlannerException {
        try {
            Scanner inputFile = new Scanner(new FileReader(filePath));
            String line;
            ui.showMessage("Loading existing file...");

            while(inputFile.hasNext()) {
                line = inputFile.nextLine();
                final Cashflow entry = getEntry(line);

                cashflowList.load(entry);
            }
            inputFile.close();
        } catch (IOException e) {
            ui.showMessage("File not found. Creating new file...");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | FinancialPlannerException e) {
            ui.showMessage("File appears to be corrupted. Do you want to create a new file? (Y/N)");
            if (createNewFile(ui)) {
                cashflowList.list.clear();
            } else {
                throw new FinancialPlannerException("Please fix the corrupted file, " +
                        "which can be found in data/data.txt.");
            }
        }
    }

    private static boolean createNewFile(Ui ui) {
        String line = ui.input();
        while (!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n")) {
            ui.showMessage("Unknown input. Please enter Y or N only.");
            line = ui.input();
        }

        return line.equalsIgnoreCase("y");
    }

    private static Cashflow getEntry(String line) throws FinancialPlannerException {
        String[] split = line.split("\\|");
        String type = split[0].trim();
        double value;
        int recur;
        Cashflow entry;

        switch (type) {
        case "I":
            value = Double.parseDouble(split[1].trim());
            recur = Integer.parseInt(split[3].trim());
            entry = new Income(value, split[2].trim(), recur);
            break;
        case "E":
            value = Double.parseDouble(split[1].trim());
            recur = Integer.parseInt(split[3].trim());
            entry = new Expense(value, split[2].trim(), recur);
            break;
        default:
            throw new FinancialPlannerException("Error loading file");
        }

        return entry;
    }
}
