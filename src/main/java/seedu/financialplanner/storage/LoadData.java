package seedu.financialplanner.storage;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.list.Expense;
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
                String[] split = line.split("\\|");
                String type = split[0].trim();
                switch (type) {
                case "I":
                case "E":
                    final Cashflow entry = getEntry(type, split);
                    cashflowList.load(entry);
                    break;
                case "B":
                    loadBudget(split);
                    break;
                default:
                    throw new FinancialPlannerException("Error loading file");
                }

            }
            inputFile.close();
        } catch (IOException e) {
            ui.showMessage("File not found. Creating new file...");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | FinancialPlannerException e) {
            handleCorruptedFile(cashflowList, ui);
        }
    }

    private static void handleCorruptedFile(CashflowList cashflowList, Ui ui) throws FinancialPlannerException {
        ui.showMessage("File appears to be corrupted. Do you want to create a new file? (Y/N)");
        if (createNewFile(ui)) {
            cashflowList.list.clear();
        } else {
            throw new FinancialPlannerException("Please fix the corrupted file, " +
                    "which can be found in data/data.txt.");
        }
    }

    private static void loadBudget(String[] split) {
        double initial = Double.parseDouble(split[1].trim());
        double current = Double.parseDouble(split[2].trim());
        Budget.load(initial, current);
    }

    private static boolean createNewFile(Ui ui) {
        String line = ui.input();
        while (!line.equalsIgnoreCase("y") && !line.equalsIgnoreCase("n")) {
            ui.showMessage("Unknown input. Please enter Y or N only.");
            line = ui.input();
        }

        return line.equalsIgnoreCase("y");
    }

    private static Cashflow getEntry(String type, String[] split)
            throws FinancialPlannerException, IllegalArgumentException {
        double value;
        int recur;
        Cashflow entry;

        switch (type) {
        case "I":
            value = Double.parseDouble(split[1].trim());
            recur = Integer.parseInt(split[3].trim());
            try {
                IncomeType incomeType = IncomeType.valueOf(split[2].trim().toUpperCase());
                entry = new Income(value, incomeType, recur);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
            break;
        case "E":
            value = Double.parseDouble(split[1].trim());
            recur = Integer.parseInt(split[3].trim());
            try {
                ExpenseType expenseType = ExpenseType.valueOf(split[2].trim().toUpperCase());
                entry = new Expense(value, expenseType, recur);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
            break;
        default:
            throw new FinancialPlannerException("Error loading file");
        }

        return entry;
    }
}
