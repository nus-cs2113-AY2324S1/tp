package seedu.financialplanner.visualisations;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategorizerTest {
    @Test
    void sortType() {
        CashflowList cashflowList = CashflowList.getInstance();
        Exception exception = assertThrows(FinancialPlannerException.class, () -> {
            Categorizer.sortType(cashflowList, "Hello");
        });
        String expected = "Hello Type not found";
        assertEquals(exception.getMessage(), expected);
    }

    @Test
    void sortExpense() throws FinancialPlannerException {
        CashflowList cashflowList = CashflowList.getInstance();
        HashMap<String, Double> map = Categorizer.sortType(cashflowList, "Expense");
        assertNotNull(map);
    }

    @Test
    void sortIncome() throws FinancialPlannerException {
        CashflowList cashflowList = CashflowList.getInstance();
        HashMap<String, Double> map = Categorizer.sortType(cashflowList, "Income");
        assertNotNull(map);
    }
}
