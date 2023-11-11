package seedu.financialplanner.visualisations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VisualizerTest {

    @Test
    void visualizeInvalid() throws FinancialPlannerException {
        CashflowList cashflowList = CashflowList.getInstance();
        HashMap<String, Double> map = Categorizer.sortType(cashflowList, "Expense");
        Exception exception = assertThrows(FinancialPlannerException.class, () -> {
            Visualizer.displayChart("Hello", map, "Expense");
        });
        String expected = "Hello Chart Type Not Found";
        assertEquals(exception.getMessage(), expected);
    }

    @DisabledOnOs({OS.LINUX})
    @Test
    void visualizePie() throws FinancialPlannerException {
        CashflowList cashflowList = CashflowList.getInstance();
        HashMap<String, Double> map = Categorizer.sortType(cashflowList, "Expense");
        assertDoesNotThrow(() -> Visualizer.displayChart("Pie", map, "Expense"));
    }

    @DisabledOnOs({OS.LINUX})
    @Test
    void visualizeBar() throws FinancialPlannerException {
        CashflowList cashflowList = CashflowList.getInstance();
        cashflowList.addExpense(100, ExpenseType.ENTERTAINMENT, 0, "hi");
        HashMap<String, Double> map = Categorizer.sortType(cashflowList, "Expense");
        assertDoesNotThrow(() -> Visualizer.displayChart("Bar", map, "Expense"));
    }

    @DisabledOnOs({OS.LINUX})
    @Test
    void visualizeRadar() throws FinancialPlannerException {
        CashflowList cashflowList = CashflowList.getInstance();
        cashflowList.addExpense(100, ExpenseType.ENTERTAINMENT, 0, "hi");
        HashMap<String, Double> map = Categorizer.sortType(cashflowList, "Expense");
        assertDoesNotThrow(() -> Visualizer.displayChart("Radar", map, "Expense"));
    }
}
