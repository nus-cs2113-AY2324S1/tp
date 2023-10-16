package seedu.financialplanner.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import seedu.financialplanner.list.Budget;
import seedu.financialplanner.list.Cashflow;

import java.util.Scanner;

public class Ui {
    public static final Ui INSTANCE = new Ui();
    private Scanner Scanner = new Scanner(System.in);

    private Ui() {
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

    public void printStockInfo(JSONObject stock) {
        String symbol = StringUtils.rightPad((String) stock.get("symbol"), 10);
        String price = StringUtils.rightPad(stock.get("price").toString(), 10);
        String name = StringUtils.rightPad((String) stock.get("name"), 10);
        System.out.println(symbol + price + name);
    }

    public void printAddStock(String stockName) {
        System.out.println("You have successfully added:");
        System.out.println(stockName);
        System.out.println("Use Watchlist to view it!");

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
    }
}
