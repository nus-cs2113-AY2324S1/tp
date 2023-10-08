package seedu.financialplanner.list;

import java.util.ArrayList;

public class FinancialList {
    protected ArrayList<Cashflow> list = new ArrayList<>();


    private void printAddedCashflow(String line) {
        System.out.println("Added " + line + "to the list.");
    }
    public void addIncome(double value, String type, int recur) {
        Income toAdd = new Income(value, type, recur);
        list.add(toAdd);
        printAddedCashflow("income");
    }
}
