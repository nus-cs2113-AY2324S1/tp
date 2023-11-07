package seedu.duke.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.ClearAll;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@link SaveToTxt} class.
 * This test class provides test cases to check whether incomes and expenses records
 * are saved correctly to the txt file
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
     * Test the {@link SaveToTxt#saveIncomeToTextFile(ArrayList)} method with income only.
     * This test case checks whether the one incomes record can save correctly.
     */
    @Test
    public void testSaveToTxtWithOneIncome() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        test.saveIncomeToTextFile(incomes);
        File file = new File(path);
        Scanner s = new Scanner(file);                      // Create a Scanner using the File as the source
        String line = s.nextLine();
        assertEquals("I |de Salary |amt 5000.00 |date 2023-10-10", line);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
    }
    /**
     * Test the {@link SaveToTxt#saveIncomeToTextFile(ArrayList)} method with incomes only.
     * This test case checks whether the multiple incomes record can save correctly.
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
        assertEquals("I |de Salary |amt 5000.00 |date 2023-10-10", lines[0]);
        assertEquals("I |de Bonus |amt 1000.00 |date 2023-10-15" ,lines[1]);
    }
    /**
     * Test the {@link SaveToTxt#saveExpenseToTextFile(ArrayList)} method with expense of Food type only.
     * This test case checks whether the Food expenses record can save correctly.
     */
    @Test
    public void testSaveToTxtWithOneExpense() throws KaChinnnngException, IOException {
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
        assertEquals("EF |de chicken sandwich |amt 10.00 |date 2023-10-01 |type 2", lines[0]);
    }

    /**
     * Test the {@link SaveToTxt#saveExpenseToTextFile(ArrayList)} method with different expense type but no incomes.
     * This test case checks whether the different type of expenses record can save correctly.
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
        assertEquals("EF |de chicken sandwich |amt 10.00 |date 2023-10-01 |type 2", lines[0]);
        assertEquals("ET |de Taxi |amt 50.00 |date 2023-10-01 |type 4", lines[1]);
        assertEquals("EU |de Electricity |amt 100.00 |date 2023-10-03 |type 2", lines[2]);
    }

    /**
     * Test the {@link SaveToTxt#saveIncomeAndExpense(ArrayList, ArrayList)} method with both incomes and expenses.
     * This test case checks whether both incomes and expenses can save correctly
     */
    @Test
    public void testSaveToTxtWithDifferentExpensesAndIncomes() throws KaChinnnngException, IOException {
        // Since it's two separate function so need to test whether they work together
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
        assertEquals("I |de Salary |amt 5000.00 |date 2023-10-10", lines[0]);
        assertEquals("I |de Bonus |amt 1000.00 |date 2023-10-15" ,lines[1]);
        assertEquals("EF |de chicken sandwich |amt 10.00 |date 2023-10-01 |type 2", lines[2]);
        assertEquals("ET |de Taxi |amt 50.00 |date 2023-10-01 |type 4", lines[3]);
        assertEquals("EU |de Electricity |amt 100.00 |date 2023-10-03 |type 2", lines[4]);
    }

    /**
     * Test the {@link SaveToTxt#saveExpenseToTextFile(ArrayList)} and disable write access.
     * This test case checks whether SaveToTxt function produce error when write access is disabled.
     */
    @Test
    public void testNoAccessDirectory() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        File file = new File(path);
        file.setWritable(false);
        expenses.add(new Food("chicken sandwich", LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        test.saveExpenseToTextFile(expenses);
        file.setWritable(true);
        Scanner s = new Scanner(file);
        int i = 0;
        while (s.hasNext()) {
            lines[i] = s.nextLine();
            i++;
        }
    }

    @Test
    public void testPassNullArrayList() {
        assertThrows(AssertionError.class, () -> new SaveToTxt(null));
        assertThrows(AssertionError.class, () -> new SaveToTxt(path).saveIncomeAndExpense(null, expenses));
        assertThrows(AssertionError.class, () -> new SaveToTxt(path).saveIncomeAndExpense(incomes, null));
        assertThrows(AssertionError.class, () -> new SaveToTxt(path).saveIncomeToTextFile(null));
        assertThrows(AssertionError.class, () -> new SaveToTxt(path).saveIncomeToTextFile(null));

    }
}
