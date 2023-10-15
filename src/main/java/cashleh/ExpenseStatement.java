package cashleh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseStatement {
    private ArrayList<Expense> expenseStatement = new ArrayList<>();
    public ExpenseStatement() {
    }
    public ExpenseStatement(Expense... expenses) {
        expenseStatement.addAll(List.of(expenses));
    }
    public void addExpense(Expense expenseToAdd) {
        expenseStatement.add(expenseToAdd);
    }
    public void deleteExpense(int expenseIndexToDelete) {
        expenseStatement.remove(expenseIndexToDelete);
    }
    public Expense getExpense(int expenseIndex) {
        return expenseStatement.get(expenseIndex);
    }
    public int getNumberOfExpenses() {
        return expenseStatement.size();
    }

    @Override
    public String toString() {
        return expenseStatement.stream().map(Expense::toString).collect(Collectors.joining("\n"));
    }
}
