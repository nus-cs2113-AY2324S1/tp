package seedu.financialplanner.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.cashflow.Budget;
import seedu.financialplanner.cashflow.Cashflow;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the handling of user interactions.
 */
public class Ui {
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private static Ui ui = null;
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN = "\u001B[36m";
    private Scanner scanner = new Scanner(System.in);
    private Ui() {
    }

    public static Ui getInstance() {
        if (ui == null) {
            ui = new Ui();
        }
        return ui;
    }

    public static void printCorruptedFileError(String message) {
        System.out.println(message);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMessage(String message) {
        assert !message.isEmpty();
        System.out.println(CYAN + message + RESET);
    }

    public void welcomeMessage() {
        showMessage("Welcome to your Financial Planner. Type something to get started.");
    }

    public void exitMessage() {
        showMessage("Exiting Financial Planner. Goodbye.");
    }

    public String input() {
        return scanner.nextLine().trim();
    }

    public String userInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        System.exit(1);
        return "";
    }

    public void printWatchListHeader() {
        System.out.print(CYAN + "Symbol" + RESET);
        System.out.print("    ");
        System.out.print(CYAN + "Market" + RESET);
        System.out.print("    ");
        System.out.print(YELLOW + "Price" + RESET);
        System.out.print("     ");
        System.out.print(GREEN + "Daily High" + RESET);
        System.out.print("     ");
        System.out.print(RED + "Daily Low" + RESET);
        System.out.print("     ");
        System.out.print("EquityName");
        System.out.print("                       ");
        System.out.print("Last Updated");
        System.out.print("     ");
        System.out.println();
    }

    public void printWatchListAcknowledgement() {
        showMessage("Data provided by Financial Modeling Prep and Alpha Vantage =)");
    }


    public void printStocksInfo(WatchList watchList) {
        for (Map.Entry<String, Stock> set : watchList.getStocks().entrySet()) {
            Stock stock = set.getValue();

            if (!ObjectUtils.allNotNull(
                    stock.getPrice(),
                    stock.getDayHigh(),
                    stock.getDayLow(),
                    stock.getLastUpdated(),
                    stock.getExchange()
            )) {
                showMessage(stock.getStockName() + " (" + stock.getSymbol() + ") is not found on FMP");
                continue;
            }

            String symbol = StringUtils.rightPad(stock.getSymbol(), 10);
            String market = StringUtils.rightPad(stock.getExchange(), 10);
            String price = YELLOW + StringUtils.rightPad(stock.getPrice(), 10) + RESET;
            String dayHigh = GREEN + StringUtils.rightPad(stock.getDayHigh(), 15) + RESET;
            String dayLow = RED + StringUtils.rightPad(stock.getDayLow(), 14) + RESET;
            String name = StringUtils.rightPad(stock.getStockName(), 33);
            String date = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss")
                    .format(stock.getLastUpdated());
            String lastUpdate = StringUtils.rightPad(date, 10);
            showMessage(symbol + market + price + dayHigh + dayLow + name + lastUpdate);
        }
        printWatchListAcknowledgement();
    }

    public void printAddStock(String stockName) {
        showMessage("You have successfully added:");
        showMessage(stockName);
        showMessage("Use Watchlist to view it!");
    }

    public void printDeleteStock(String stockName) {
        showMessage("You have successfully deleted: ");
        showMessage(stockName);
        showMessage("Use watchlist command to view updated Watchlist");
    }

    public String formatBalance(double balance) {
        DecimalFormat decimalFormat = new DecimalFormat("####0.00");

        return decimalFormat.format(Cashflow.round(balance, 2));
    }

    public void printAddedCashflow(Cashflow entry) {
        showMessage("You have added an " + entry);
        showMessage("to the Financial Planner.");
        showMessage("Balance: " + formatBalance(Cashflow.getBalance()));
    }

    public void printAddedCashflowWithoutBalance(Cashflow entry) {
        showMessage("You have added an " + entry);
        showMessage("to the Financial Planner.");
    }

    public void printBalance() {
        showMessage("Balance: " + formatBalance(Cashflow.getBalance()));
    }

    public void printDeletedCashflow(Cashflow entry) {
        showMessage("You have removed an " + entry);
        showMessage("from the Financial Planner.");
        showMessage("Balance: " + formatBalance(Cashflow.getBalance()));
    }

    public void printDeletedRecur(Cashflow entry) {
        showMessage("You have removed future recurrences of this cashflow.");
        showMessage("Updated cashflow:");
        showMessage(entry.toString());
    }

    public void printBudgetBeforeUpdate() {
        showMessage("Budget has been updated:\nOld initial budget: " +
                Budget.getInitialBudgetString() + "\nOld current budget: " +
                Budget.getCurrentBudgetString());
    }

    public void printBudgetAfterUpdate() {
        showMessage("New initial budget: " + Budget.getInitialBudgetString() +
                "\nNew current budget: " + Budget.getCurrentBudgetString());
        if (Budget.getCurrentBudget() <= 0) {
            showMessage("You have exceeded your budget, please update to a larger budget or " +
                    "reset the current budget to initial budget.");
        }
    }

    public void printBudgetAfterDeduction() {
        StringBuilder message = new StringBuilder();
        message.append("Your remaining budget for the month is: ").append(Budget.getCurrentBudgetString());
        if (Budget.getCurrentBudget() <= 0) {
            message.append("Oops, you ran out of budget, please update to a larger budget or " +
                    "reset the current budget to initial budget.");
        }

        showMessage(message.toString());
    }

    public void printBudget() {
        showMessage("You have a remaining budget of " + Budget.getCurrentBudgetString() + ".");
    }

    public void printDeleteBudget() {
        showMessage("Budget has been deleted.");
    }

    public void printResetBudget() {
        showMessage("Budget has been reset to " + Budget.getInitialBudgetString() + ".");
    }

    public void printDisplayChartMessage(String type, String chart) {
        showMessage("Displaying " + chart + "chart for " + type);
    }

    public void printOverview(String... args) {
        String balance = args[0];
        String income = args[1];
        String expense = args[2];
        String budget = args[3];
        String reminders = args[4];
        String wishlist = args[5];

        showMessage("Here is an overview of your financials:\n" +  "Total balance: " + balance + "\n" +
                "Highest income: " + income + "\n" + "Highest expense: " + expense + "\n" +
                "Remaining budget for the month: " + budget + "\n\n" + "Reminders:\n" + reminders +
                "\n\nWishlist:\n" + wishlist);
    }

    public void printSetBudget() {
        showMessage("A monthly budget of " + Budget.getInitialBudgetString() + " has been set.");
    }

    public void printBudgetExceedBalance() {
        showMessage("Since initial budget exceeds current balance, budget will be reset to current balance.");
    }

    public void printBudgetError(String errorType) {
        switch (errorType) {
        case "delete":
            showMessage("Budget has not been set yet.");
            break;
        case "reset":
            showMessage("Budget has not been spent yet.");
            break;
        case "view":
            showMessage("There is no existing budget.");
            break;
        default:
            logger.log(Level.SEVERE, "Unreachable default case reached");
            showMessage("Unknown command");
        }
    }

    public void printEmptyCashFlow(String type) {
        showMessage(StringUtils.capitalize(type) + " is empty... Nothing to visualize");
    }

    public void printInvalidStockLoaded(String key) {
        showMessage(RED + "Could not load " + key  + " due to incorrect format. Check the UG for correct format");
    }
}
