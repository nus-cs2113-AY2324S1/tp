package cashleh.transaction;

import cashleh.exceptions.CashLehMissingTransactionException;
import cashleh.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public double getSumOfEntries() {
        return incomeStatement.stream().mapToDouble(Income::getAmount).sum();
    }

    public void printIncomes() {
        int listSize = incomeStatement.size();
        String[] texts = new String[listSize + 1];
        texts[0] = "Here's a list of your current incomes:";
        for (int i = 1; i <= listSize; i++) {
            Income currentIncome = incomeStatement.get(i - 1);
            texts[i] = "\t" + i + "." + currentIncome.toString();
        }
        Ui.printMultipleText(texts);
    }
    @Override
    public String toString() {
        return incomeStatement.stream().map(Income::toString).collect(Collectors.joining("\n"));
    }
}
