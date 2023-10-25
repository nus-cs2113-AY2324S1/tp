package seedu.financialplanner.utils;

import org.apache.commons.lang3.StringUtils;
import seedu.financialplanner.investments.Stock;
import seedu.financialplanner.investments.WatchList;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;

import java.util.Scanner;

public class Ui {
    private static Ui ui = null;
    private Scanner Scanner = new Scanner(System.in);
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
        return Scanner;
    }

    public void setScanner(Scanner scanner) {
        this.Scanner = scanner;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void welcomeMessage() {
        showMessage("Welcome to your Financial Planner. Type something to get started.");
    }

    public void exitMessage() {
        showMessage("Exiting Financial Planner. Goodbye.");
    }

    public String input() {
        return Scanner.nextLine().trim();
    }

    public void printWatchListHeader() {
        System.out.print("Symbol");
        System.out.print("    ");
        System.out.print("Price");
        System.out.print("     ");
        System.out.print("Company Name");
        System.out.println();
    }

    public void printStocksInfo(WatchList watchList) {
        for (Stock stock: watchList.getStocks()) {
            String symbol = StringUtils.rightPad(stock.getSymbol(), 10);
            String price = StringUtils.rightPad(stock.getPrice(), 10);
            String name = StringUtils.rightPad((String) stock.getStockName(), 10);
            System.out.println(symbol + price + name);
        }
    }

    public void printAddStock(String stockName) {
        System.out.println("You have successfully added:");
        System.out.println(stockName);
        System.out.println("Use Watchlist to view it!");
    }

    public void printDeleteStock(String stockName) {
        System.out.println("You have successfully deleted: ");
        System.out.println(stockName);
        System.out.println("Use watchlist command to view updated Watchlist");
    }

    public void printAddedCashflow(Cashflow entry) {
        System.out.print("You have added an ");
        System.out.println(entry);
        System.out.println("to the Financial Planner.");
        System.out.println("Balance: " + entry.formatBalance());
    }

    public void printDeletedCashflow(Cashflow entry) {
        System.out.print("You have removed an ");
        System.out.println(entry);
        System.out.println("from the Financial Planner.");
        System.out.println("Balance: " + entry.formatBalance());
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
        if (Budget.getCurrentBudget() <= 0) {
            message.append("You have exceeded your current budget by: ");
        } else if (Budget.getCurrentBudget() > 0) {
            message.append("Your remaining budget for the month is: ");
        }
        message.append(Budget.getCurrentBudgetString());
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

    public void printDisplayChart(String type, String chart) {
        showMessage("Displaying " + chart + "chart for " + type);
    }

    public void printOverview(String... args) {
        String balance = args[0];
        String income = args[1];
        String expense = args[2];
        String budget = args[3];
        String reminders = args[4];

        showMessage("Here is an overview of your financials:\n" +  "Total balance: " + balance + "\n" +
                "Highest income: " + income + "\n" + "Highest expense: " + expense + "\n" +
                "Remaining budget for the month: " + budget + "\n\n" + "Reminders:\n" + reminders);
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
            showMessage("Unknown command");
        }
    }
}
