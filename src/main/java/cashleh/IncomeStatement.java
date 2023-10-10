package cashleh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IncomeStatement {
    private ArrayList<Income> incomeStatement = new ArrayList<>();
    public IncomeStatement() {}
    public IncomeStatement(Income... incomes) {
        incomeStatement.addAll(List.of(incomes));
    }
    public void add(Income incomeToAdd) {
        incomeStatement.add(incomeToAdd);
    }
    public void delete(int incomeIndexToDelete) {
        incomeStatement.remove(incomeIndexToDelete);
    }
    public Income get(int incomeIndex) {
        return incomeStatement.get(incomeIndex);
    }
    public int getNumberOfEntries() {
        return incomeStatement.size();
    }
    public double getSumOfEntries() {
        return incomeStatement.stream().mapToDouble(Income::getAmount).sum();
    }
    @Override
    public String toString() {
        return incomeStatement.stream().map(Income::toString).collect(Collectors.joining("\n"));
    }
}
