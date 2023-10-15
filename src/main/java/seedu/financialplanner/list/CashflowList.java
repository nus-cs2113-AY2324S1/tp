package seedu.financialplanner.list;

import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

public class CashflowList {
    public static final CashflowList INSTANCE = new CashflowList();

    public final ArrayList<Cashflow> list = new ArrayList<>();

    private CashflowList() {
    }


    public void addIncome(double value, String type, int recur) {
        Income toAdd = new Income(value, type, recur);
        list.add(toAdd);
        Ui.INSTANCE.printAddedCashflow(toAdd);
    }

    public void addExpense(double value, String type, int recur) {
        Expense toAdd = new Expense(value, type, recur);
        list.add(toAdd);
        Ui.INSTANCE.printAddedCashflow(toAdd);
    }

    public void delete(int index) {
        int listIndex = index - 1;

        Cashflow toRemove = get(listIndex);
        list.remove(listIndex);
        Cashflow.balance -= toRemove.amount;
        Ui.INSTANCE.printDeletedCashflow(toRemove);
    }
    //helper method to find the index of a given cashflow in the overall list
    //given its index in its respective list. e.g. "income 3" is the third income
    //in the overall list
    private int cashflowIndexFinder(String type, int cashflowIndex) {

        switch (type) {
        case "income":
            return findCashflowIndexFromIncomeIndex(cashflowIndex);
        case "expense":
            return findCashflowIndexFromExpenseIndex(cashflowIndex);
        default:
            return -1;
        }
    }

    private int findCashflowIndexFromIncomeIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry instanceof Income) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }

    private int findCashflowIndexFromExpenseIndex(int cashflowIndex) {
        int cashflowCounter = 0;
        int overallCashflowIndex = 0;

        for (Cashflow entry : list) {
            if (entry instanceof Expense) {
                cashflowCounter += 1;
            }
            if (cashflowCounter == cashflowIndex) {
                break;
            }
            overallCashflowIndex += 1;
        }
        return overallCashflowIndex;
    }

    public void deleteCashflow(String cashflowType, int index) {
        int listIndex = cashflowIndexFinder(cashflowType, index);

        Cashflow toRemove = get(listIndex);
        list.remove(listIndex);
        Cashflow.balance -= toRemove.amount;
        Ui.INSTANCE.printDeletedCashflow(toRemove);
    }

    public void load(Cashflow entry) {
        list.add(entry);
    }

    //temp method
    public String getList() {
        String output = "";
        for (Cashflow entry : list) {
            output += entry.formatString() + "\n";
        }
        return output;
    }

    public Cashflow get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }
}
