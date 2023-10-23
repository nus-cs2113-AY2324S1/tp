package seedu.financialplanner.storage;

import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;
import seedu.financialplanner.list.CashflowList;
import seedu.financialplanner.list.Income;
import seedu.financialplanner.list.Expense;
import seedu.financialplanner.utils.Ui;

import java.io.StreamCorruptedException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class LoadData {
    private static final String FILE_PATH = "data/watchlist.txt";
    private static final CashflowList cashflowList = CashflowList.getInstance();
    private static final Ui ui = Ui.getInstance();

    public static void load(String filePath) throws FinancialPlannerException {
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
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | FinancialPlannerException e) {
            String message = e.getMessage();
            handleCorruptedFile(message);
        }
    }

    private static void handleCorruptedFile(String message) throws FinancialPlannerException {
        ui.showMessage("File appears to be corrupted. Do you want to create a new file? (Y/N)");
        if (createNewFile()) {
            cashflowList.list.clear();
            Cashflow.clearBalance();
        } else {
            throw new FinancialPlannerException("Please fix the corrupted file, " +
                    "which can be found in data/data.txt.\nError message: " + message);
        }
    }

    private static void loadBudget(String[] split) throws IllegalArgumentException {
        double initial = Double.parseDouble(split[1].trim());
        double current = Double.parseDouble(split[2].trim());
        if (initial < 0 || current < 0) {
            throw new IllegalArgumentException("Negative values for budget");
        }
        if (initial > Cashflow.getBalance() || current > Cashflow.getBalance()) {
            throw new IllegalArgumentException("Budget exceeds balance");
        }
        if (initial < current) {
            throw new IllegalArgumentException("Current budget exceeds initial budget");
        }
        Budget.load(initial, current);
    }

    private static boolean createNewFile() {
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
            checkValidInput(value, recur);
            IncomeType incomeType;
            try {
                incomeType = IncomeType.valueOf(split[2].trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid income type");
            }
            entry = new Income(value, incomeType, recur);
            break;
        case "E":
            value = Double.parseDouble(split[1].trim());
            recur = Integer.parseInt(split[3].trim());
            checkValidInput(value, recur);
            ExpenseType expenseType;
            try {
                expenseType = ExpenseType.valueOf(split[2].trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid expense type");
            }
            entry = new Expense(value, expenseType, recur);
            break;
        default:
            throw new FinancialPlannerException("Error loading file");
        }

        return entry;
    }

    public static ArrayList<Stock> loadWatchList() {
        Ui ui = Ui.getInstance();
        ArrayList<Stock> stocksData = new ArrayList<>();
        try {
            ObjectInputStream watchListStocksInputStream
                    = new ObjectInputStream(
                    new FileInputStream(FILE_PATH)
            );
            stocksData = (ArrayList<Stock>) watchListStocksInputStream.readObject();
            watchListStocksInputStream.close();
        } catch (StreamCorruptedException e) {
            ui.showMessage("Watchlist file corrupted.. Rebuilding");
        } catch (IOException e) {
            ui.showMessage("Watchlist file not found... Creating");
        } catch (ClassNotFoundException e) {
            ui.showMessage("FIle appears to be corrupted...");
        }
        return stocksData;
    }

    private static void checkValidInput(double value, int recur) throws FinancialPlannerException {
        if (value < 0 || recur < 0) {
            throw new FinancialPlannerException("Amount and number of days cannot be negative");
        }
    }
}
