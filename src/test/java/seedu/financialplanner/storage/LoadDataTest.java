package seedu.financialplanner.storage;

import org.junit.jupiter.api.Test;
import seedu.financialplanner.cashflow.CashflowList;
import seedu.financialplanner.cashflow.Expense;
import seedu.financialplanner.cashflow.Income;
import seedu.financialplanner.enumerations.ExpenseType;
import seedu.financialplanner.enumerations.IncomeType;
import seedu.financialplanner.exceptions.FinancialPlannerException;
import seedu.financialplanner.investments.Stock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    void loadWatchListBasic() {
        try {
            HashMap<String, Stock> stocks = LoadData.loadWatchList("src/test/testData/basicwatchlist.json");
            assertEquals(2, stocks.size());
            assertNotNull(stocks.get("AAPL"));
            assertNotNull(stocks.get("GOOGL"));
        } catch (Exception e) {
            System.out.println("Caught");
        }
    }

    @Test
    void loadWatchListExceedSize() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            HashMap<String, Stock> stocks = LoadData.loadWatchList("src/test/testData/exceedwatchlist.json");
        });
    }

    @Test
    void loadWatchListIncorrectHashCode() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            HashMap<String, Stock> stocks = LoadData.loadWatchList("src/test/testData/incorrectwatchlist.json");
        });
    }

    private LocalDate stringToDate(String string) {
        return LocalDate.parse(string, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    private void getTestData() throws  FinancialPlannerException{
        LocalDate date = stringToDate("01/01/2023");
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date, false));
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 0, "parents", date, false));
        cashflowList.load(new Expense(100, ExpenseType.SHOPPING, 30, "shopee", date, false));
        cashflowList.load(new Expense(100, ExpenseType.INSURANCE, 10, "ntuc", date, true));

        date = date.plusDays(10);
        cashflowList.load(new Expense(100, ExpenseType.INSURANCE, 10, "ntuc", date, false));
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date, false));

        date = date.plusDays(10);
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date, false));

        date = date.plusDays(10);
        cashflowList.load(new Income(123.12, IncomeType.ALLOWANCE, 10, null, date, false));
        cashflowList.load(new Expense(100, ExpenseType.SHOPPING, 30, "shopee", date, false));

        date = stringToDate("21/01/2023");
        cashflowList.load(new Expense(100, ExpenseType.INSURANCE, 10, "ntuc", date, false));

        date = date.plusDays(10);
        cashflowList.load(new Expense(100, ExpenseType.INSURANCE, 10, "ntuc", date, false));
    }
}
