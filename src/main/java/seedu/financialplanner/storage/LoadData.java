package seedu.financialplanner.storage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.utils.Ui;
import seedu.financialplanner.goal.Goal;
import seedu.financialplanner.goal.WishList;
import seedu.financialplanner.reminder.Reminder;
import seedu.financialplanner.reminder.ReminderList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents the loading of data from storage.
 */
public abstract class LoadData {
    private static final CashflowList cashflowList = CashflowList.getInstance();
    private static final Ui ui = Ui.getInstance();
    private static final ReminderList reminderList = ReminderList.getInstance();
    private static final WishList wishList = WishList.getInstance();

    /**
     * Loads existing data from the storage file.
     * Adds recurrences of a cashflow if applicable.
     *
     * @param filePath The file where the data is stored.
     * @param date The current date.
     * @throws FinancialPlannerException If there is an issue loading data from storage.
     */
    public static void load(String filePath, LocalDate date) throws FinancialPlannerException {
        try {
            Scanner inputFile = new Scanner(new FileReader(filePath));
            String line;
            ui.showMessage("Loading existing file...");

            while (inputFile.hasNext()) {
                line = inputFile.nextLine();
                String[] split = line.split("\\|");
                String type = split[0].trim();
                switch (type) {
                case "I":
                    // Fallthrough
                case "E":
                    final Cashflow entry = getEntry(type, split);
                    cashflowList.load(entry);
                    break;
                case "B":
                    loadBudget(split);
                    break;
                case "R":
                    final Reminder reminder = getReminder(split);
                    reminderList.load(reminder);
                    break;
                case "G":
                    final Goal goal = getGoal(split);
                    wishList.load(goal);
                    break;
                default:
                    throw new FinancialPlannerException("Error loading file.");
                }
            }
            inputFile.close();
            deleteFutureCashflows(date);
            addRecurringCashflows(date);
        } catch (IOException e) {
            ui.showMessage("File not found. Creating new file...");
        } catch (IndexOutOfBoundsException e) {
            handleCorruptedFile("Empty/Missing arguments detected.");
        } catch (IllegalArgumentException | FinancialPlannerException e) {
            handleCorruptedFile(e.getMessage());
        } catch (DateTimeParseException e) {
            handleCorruptedFile("Erroneous date format or Wrong position of date detected.");
        }
    }

    private static void deleteFutureCashflows(LocalDate currentDate) {
        ArrayList<Integer> tempCashflowList = new ArrayList<>();
        int indexToDelete = 0;
        for (Cashflow cashflow : cashflowList.list) {
            int recur = cashflow.getRecur();
            LocalDate dateOfAddition = cashflow.getDate();
            if (recur > 0 && currentDate.isBefore(dateOfAddition)) {
                Integer integer = indexToDelete;
                tempCashflowList.add(integer);
            }
            indexToDelete++;
        }
        if (!tempCashflowList.isEmpty()) {
            ui.showMessage("Detected erroneous cashflow entries. Removing future cashflows...");
            for (int i = 0; i < tempCashflowList.size(); i++) {
                indexToDelete = tempCashflowList.get(i) - i;
                // deleteCashflowWithoutCategory takes in list index starting from 1, indexToDelete starts from 0
                int indexStartingFromOne = indexToDelete + 1;
                cashflowList.deleteCashflowWithoutCategory(indexStartingFromOne);
            }
        }
    }

    private static void addRecurringCashflows(LocalDate currentDate) throws FinancialPlannerException {
        ui.showMessage("Adding any recurring cashflows...");
        ArrayList<Cashflow> tempCashflowList = new ArrayList<>();
        for (Cashflow cashflow : cashflowList.list) {
            int recur = cashflow.getRecur();
            LocalDate dateOfAddition = cashflow.getDate();
            boolean hasRecurred = cashflow.getHasRecurred();
            identifyRecurringCashflows(currentDate, cashflow, recur, dateOfAddition, tempCashflowList, hasRecurred);
        }
        for (Cashflow cashflow : tempCashflowList) {
            cashflowList.load(cashflow);
            ui.printAddedCashflowWithoutBalance(cashflow);
        }
        if (!tempCashflowList.isEmpty()) {
            ui.printBalance();
        }
    }

    private static void identifyRecurringCashflows(LocalDate currentDate
            , Cashflow cashflow, int recur, LocalDate dateOfAddition
            , ArrayList<Cashflow> tempCashflowList, boolean hasRecurred) throws FinancialPlannerException {
        if (recur > 0 && !hasRecurred) {
            dateOfAddition = dateOfAddition.plusDays(recur);
            addRecurringCashflowToTempList(currentDate, cashflow, recur, dateOfAddition, tempCashflowList);
        }
    }

    private static void addRecurringCashflowToTempList(LocalDate currentDate
            , Cashflow cashflow, int recur, LocalDate dateOfAddition
            , ArrayList<Cashflow> tempCashflowList) throws FinancialPlannerException {
        while (currentDate.isAfter(dateOfAddition) || currentDate.isEqual(dateOfAddition)) {
            cashflow.setHasRecurred(true);
            Cashflow toAdd;
            if (cashflow instanceof Income) {
                toAdd = new Income((Income) cashflow);
            } else if (cashflow instanceof Expense) {
                toAdd = new Expense((Expense) cashflow);
            } else {
                throw new FinancialPlannerException("Error adding recurring cashflows.");
            }
            toAdd.setDate(dateOfAddition);
            addToTempList(tempCashflowList, toAdd);
            cashflow = toAdd;
            dateOfAddition = dateOfAddition.plusDays(recur);
        }
    }

    private static void addToTempList(ArrayList<Cashflow> tempCashflowList, Cashflow toAdd) {
        tempCashflowList.add(toAdd);
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
        double initial;
        double current;
        try {
            initial = Double.parseDouble(split[1].trim());
            current = Double.parseDouble(split[2].trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error parsing budget values.");
        }
        if (initial == 0 && current == 0) {
            return;
        }
        if (initial < 0 || current < 0) {
            throw new IllegalArgumentException("Negative values for budget.");
        }
        if (initial > Cashflow.getBalance() || current > Cashflow.getBalance()) {
            throw new IllegalArgumentException("Budget exceeds balance.");
        }
        if (initial < current) {
            throw new IllegalArgumentException("Current budget exceeds initial budget.");
        }
        LocalDate date = LocalDate.parse(split[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if (LocalDate.now().isBefore(date)) {
            throw new IllegalArgumentException("Current date is before saved date.");
        }
        Budget.load(initial, current, date);
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
            throws FinancialPlannerException, IllegalArgumentException, DateTimeParseException
            , IndexOutOfBoundsException {
        try {
            Cashflow entry;
            double value = Double.parseDouble(split[1].trim());
            int recur = Integer.parseInt(split[3].trim());
            boolean hasRecurred = getHasRecurred(split, recur);
            LocalDate date = getDate(split, recur);
            int index = getIndex(recur);
            String description = getDescription(split, index);
            checkValidInput(value, recur);

            switch (type) {
            case "I":
                IncomeType incomeType;
                incomeType = IncomeType.valueOf(split[2].trim().toUpperCase());
                entry = new Income(value, incomeType, recur, description, date, hasRecurred);
                break;
            case "E":
                ExpenseType expenseType;
                expenseType = ExpenseType.valueOf(split[2].trim().toUpperCase());
                entry = new Expense(value, expenseType, recur, description, date, hasRecurred);
                break;
            default:
                throw new FinancialPlannerException("Error loading file.");
            }
            return entry;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erroneous arguments detected.");
        }
    }

    private static Reminder getReminder(String[] split) throws IllegalArgumentException, IndexOutOfBoundsException,
            FinancialPlannerException {
        try {
            Reminder entry;
            String type = split[1].trim();
            String date = split[2].trim();
            String status = split[3].trim();
            entry = new Reminder(type, date, status);
            return entry;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erroneous arguments detected.");
        } catch (IndexOutOfBoundsException e) {
            throw new FinancialPlannerException("There should be three data members for reminder.");
        }
    }

    private static Goal getGoal(String[] split) throws IllegalArgumentException {
        try {
            Goal entry;
            String type = split[1].trim();
            int amount = Integer.parseInt(split[2].trim());
            String status = split[3].trim();
            entry = new Goal(type, amount, status);
            return entry;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erroneous arguments detected.");
        }
    }

    private static boolean getHasRecurred(String[] split, int recur) throws IllegalArgumentException {
        boolean hasRecurred;
        if (recur != 0) {
            String stringHasRecurred = split[4].trim();
            if (stringHasRecurred.equals("true") || stringHasRecurred.equals("false")) {
                hasRecurred = Boolean.parseBoolean(stringHasRecurred);
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            hasRecurred = false;
        }
        return hasRecurred;
    }

    private static LocalDate getDate(String[] split, int recur) {
        LocalDate date;
        if (recur != 0) {
            date = LocalDate.parse(split[5].trim(), DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT));
        } else {
            date = null;
        }
        return date;
    }

    private static int getIndex(int recur) {
        int index;
        if (recur != 0) {
            index = 6;
        } else {
            index = 5;
        }
        return index;
    }

    private static String getDescription(String[] split, int index) {
        String description;
        if (split.length > index) {
            description = split[index].trim();
        } else {
            description = null;
        }
        return description;
    }

    /**
     * Load the watchlist.json file into the application on startup as a hashmap.
     *
     * @return Hashmap of loaded stocks
     */
    public static HashMap<String, Stock> loadWatchList(String filePath) {
        Ui ui = Ui.getInstance();
        Gson gson = new Gson();
        HashMap<String, Stock> stocksData = null;
        ui.showMessage("Loading existing watchlist..");
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            stocksData = gson.fromJson(reader, new TypeToken<HashMap<String,Stock>>(){}.getType());
            if (stocksData.size() > 5) {
                throw new FinancialPlannerException("You have more than 5 entries in watchlist.json");
            }
            if (!checkHashCode(stocksData)) {
                throw new FinancialPlannerException("watchlist.json values were edited. " +
                        "Please do not change the generated values!");
            }
        } catch (FileNotFoundException e) {
            ui.showMessage("Watchlist file not found... Creating");
        } catch (JsonSyntaxException e) {
            ui.showMessage("Watchlist JSON is corrupted!");
            ui.showMessage("Would you like to create new file? (Y/N)");
            if (!createNewFile()) {
                ui.showMessage("Exiting... Please fix the file.");
                System.exit(1);
            }
        } catch (FinancialPlannerException e) {
            ui.showMessage(e.getMessage());
            ui.showMessage("Would you like to create new watchlist? (Y/N)");
            if (!createNewFile()) {
                ui.showMessage("Exiting... Please fix the file.");
                System.exit(1);
            }
            stocksData = null;
        }
        return stocksData;
    }

    private static void checkValidInput(double value, int recur) throws FinancialPlannerException {
        if (value < 0) {
            throw new FinancialPlannerException("Amount cannot be negative.");
        }
        if (value > 999999999999.99) {
            throw new FinancialPlannerException("Amount exceeded maximum value this program can hold.");
        }
        if (recur < 0) {
            throw new FinancialPlannerException("Recurring value cannot be negative.");
        }
    }

    private static boolean checkHashCode(HashMap<String, Stock> stocksData) {
        for (HashMap.Entry<String, Stock> stock : stocksData.entrySet()) {
            if (stock.getValue().getHashCode() == 0) {
                continue;
            }
            if (stock.getValue().checkHashCode() != stock.getValue().getHashCode()) {
                return false;
            }
        }
        return true;
    }
}
