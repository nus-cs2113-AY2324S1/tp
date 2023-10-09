package seedu.financialplanner.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void welcomeMessage() {
        showMessage("\tWelcome to your Financial Planner. Type something to get started.");
    }

    public void exitMessage() {
        showMessage("\tExiting Financial Planner. Goodbye.");
    }

    public String input() {
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
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
}
