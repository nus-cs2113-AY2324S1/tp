package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LoadDataTest {
    protected CashflowList cashflowList = CashflowList.getInstance();

    @Test
    void testLoad() throws FinancialPlannerException {
        cashflowList.list.clear();
        LocalDate date = stringToDate("01/01/2023");
        date = date.plusDays(30);
        LoadData.load("src/test/testData/TestData.txt", date);
        String actual = cashflowList.getList();
        cashflowList.list.clear();
        getTestData();
        String expected = cashflowList.getList();
        assertEquals(expected, actual);
    }

    @Test
    void loadWatchList() {
    }
    private LocalDate stringToDate(String string) {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    private void getTestData() {
        LocalDate date = stringToDate("01/01/2023");
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date));
        cashflowList.load(new Expense(100, ExpenseType.SHOPPING, 30, "shopee", date));

        date = date.plusDays(10);
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date));

        date = date.plusDays(10);
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date));

        date = date.plusDays(10);
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date));
        cashflowList.load(new Expense(100, ExpenseType.SHOPPING, 30, "shopee", date));
    }
}
