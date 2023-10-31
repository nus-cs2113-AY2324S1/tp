package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.Balance;
import seedu.duke.commands.ClearAll;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.*;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the {@link SaveToTxt} class.
 * This test class provides test cases to check the calculation and reporting
 * of financial balance between income and expenses.
 */
public class SaveToTxtTest {
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;
    private String path;
    private String []lines = new String[10];
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @BeforeEach
    public void setup() throws KaChinnnngException {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        path = "Test.txt";

    }

    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithOneIncome() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        test.saveIncomeToTextFile(incomes);
        File file = new File(path);
        Scanner s = new Scanner(file);                      // Create a Scanner using the File as the source
        String line = s.nextLine();
        assertEquals("I | Salary | 5000.0 | 2023-10-10", line);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
    }
    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithMoreThanOneIncomes() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus", LocalDate.of(2023, 10, 15), 1000.0));
        test.saveIncomeToTextFile(incomes);
        File file = new File(path);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
        assertEquals("I | Salary | 5000.0 | 2023-10-10", lines[0]);
        assertEquals("I | Bonus | 1000.0 | 2023-10-15" ,lines[1]);
    }
    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithFoodType() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        expenses.add(new Food("chicken sandwich", LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        test.saveExpenseToTextFile(expenses);
        File file = new File(path);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
        assertEquals("EF | chicken sandwich | 10.0 | 2023-10-01 | 2", lines[0]);
    }
    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithTransportationType() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        expenses.add(new Transport("Taxi", LocalDate.of(2023, 10, 1),50.0, TransportationType.FUEL));
        test.saveExpenseToTextFile(expenses);
        File file = new File(path);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
        assertEquals("ET | Taxi | 50.0 | 2023-10-01 | 4", lines[0]);
    }
    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithUtilityType() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        expenses.add(new Utilities("Electricity", LocalDate.of(2023, 10, 3),100.0, UtilityType.ELECTRICITY));
        test.saveExpenseToTextFile(expenses);
        File file = new File(path);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
        assertEquals("EU | Electricity | 100.0 | 2023-10-03 | 2", lines[0]);
    }

    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithDifferentExpenseType() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        expenses.add(new Food("chicken sandwich", LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        expenses.add(new Transport("Taxi", LocalDate.of(2023, 10, 1),50.0, TransportationType.FUEL));
        expenses.add(new Utilities("Electricity", LocalDate.of(2023, 10, 3),100.0, UtilityType.ELECTRICITY));
        test.saveExpenseToTextFile(expenses);
        File file = new File(path);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
        assertEquals("EF | chicken sandwich | 10.0 | 2023-10-01 | 2", lines[0]);
        assertEquals("ET | Taxi | 50.0 | 2023-10-01 | 4", lines[1]);
        assertEquals("EU | Electricity | 100.0 | 2023-10-03 | 2", lines[2]);
    }

    /**
     * Test the {@link Balance#getBalance()} method with both income and expenses.
     * This test case checks if the balance calculation correctly subtracts expenses from income.
     */
    @Test
    public void testSaveToTxtWithDifferentExpensesAndIncomes() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus", LocalDate.of(2023, 10, 15), 1000.0));
        expenses.add(new Food("chicken sandwich", LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        expenses.add(new Transport("Taxi", LocalDate.of(2023, 10, 1),50.0, TransportationType.FUEL));
        expenses.add(new Utilities("Electricity", LocalDate.of(2023, 10, 3),100.0, UtilityType.ELECTRICITY));
        test.saveIncomeAndExpense(incomes,expenses);
        File file = new File(path);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
        assertEquals("I | Salary | 5000.0 | 2023-10-10", lines[0]);
        assertEquals("I | Bonus | 1000.0 | 2023-10-15" ,lines[1]);
        assertEquals("EF | chicken sandwich | 10.0 | 2023-10-01 | 2", lines[2]);
        assertEquals("ET | Taxi | 50.0 | 2023-10-01 | 4", lines[3]);
        assertEquals("EU | Electricity | 100.0 | 2023-10-03 | 2", lines[4]);
    }
}
