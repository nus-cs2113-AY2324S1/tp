package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Represents an Income Statement in the CashLeh application.
 * This class is used to manage and store a list of incomes.
 * It provides methods for adding, deleting, retrieving, and displaying incomes within the statement.
 */
public class IncomeStatement {
    private final ArrayList<Income> incomeStatement = new ArrayList<>();
    public IncomeStatement() {}
    public IncomeStatement(Income... incomes) {
        incomeStatement.addAll(List.of(incomes));
    }
    public void addIncome(Income incomeToAdd) {
        incomeStatement.add(incomeToAdd);
    }
    public void deleteIncome(int incomeIndex) throws CashLehMissingTransactionException {
        try {
            incomeStatement.remove(incomeIndex);
        } catch(IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }
    public Income getIncome(int incomeIndex) throws CashLehMissingTransactionException {
        try {
            return incomeStatement.get(incomeIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new CashLehMissingTransactionException();
        }
    }
    public int getNumberOfEntries() {
        return incomeStatement.size();
    }
    public double getTotalIncomeAmount() {
        return incomeStatement.stream().mapToDouble(Income::getAmount).sum();
    }

    public void printIncomes() {
        int listSize = incomeStatement.size();
        String[] texts = new String[listSize + 1];
        texts[0] = "The current sum of all your incomes amounts to: " + getTotalIncomeAmount();
        for (int i = 1; i <= listSize; i++) {
            Income currentIncome = incomeStatement.get(i - 1);
            texts[i] = "\t" + i + "." + currentIncome.toString();
        }
        Ui.printMultipleText(texts);
    }
    public void findIncome(String description, double amount) throws CashLehMissingTransactionException {
        ArrayList<String> matchingIncomes = new ArrayList<>();
        boolean isMatch = false;
        matchingIncomes.add("Here are your corresponding incomes with description:");
        for (Income income : incomeStatement) {
            boolean descriptionMatch = (description == null) || (description.isEmpty())
                    || income.getDescription().equals(description);
            boolean amountMatch = (amount == -1) || (income.getAmount() == amount);
            if (descriptionMatch && amountMatch) {
                matchingIncomes.add(income.toString());
                isMatch = true;
            }
        }
        if (isMatch) {
            Ui.printMultipleText(matchingIncomes);
        } else {
            throw new CashLehMissingTransactionException();
        }
    }
    @Override
    public String toString() {
        return incomeStatement.stream().map(Income::toString).collect(Collectors.joining("\n"));
    }
}
