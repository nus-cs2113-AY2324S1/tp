package seedu.duke.ui;

import  seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;

import java.util.ArrayList;
import java.util.List;

public class MockUi extends Ui{
    private List<String> printedMessages = new ArrayList<>();
    private List<String> showIncomes = new ArrayList<>();
    private List<String> showExpenses = new ArrayList<>();

    @Override
    public void printMessage(String message) {
        printedMessages.add(message);
    }

    @Override
    public void showMatchingIncomes(List<Income> matchingIncomes) {
        showIncomes.add(matchingIncomes.toString());
    }

    @Override
    public void showMatchingExpenses(List<Expense> matchingExpenses) {
        showExpenses.add(matchingExpenses.toString());
    }

    public List<String> getPrintedMessages() {
        return printedMessages;
    }

    public List<String> getShowIncomes() {
        return showIncomes;
    }

    public List<String> getShowExpenses() {
        return showExpenses;
    }
}
