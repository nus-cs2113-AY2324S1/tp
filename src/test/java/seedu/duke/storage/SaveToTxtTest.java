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
        assertEquals("I | Salary | 5000.0 | 2023-10-10", line);
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
        assertEquals("I | Salary | 5000.0 | 2023-10-10", lines[0]);
        assertEquals("I | Bonus | 1000.0 | 2023-10-15" ,lines[1]);
    }
    /**
     * Test the {@link SaveToTxt#saveExpenseToTextFile(ArrayList)} method with expense of Food type only.
     * This test case checks whether the Food expenses record can save correctly.
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
     * Test the {@link SaveToTxt#saveExpenseToTextFile(ArrayList)} method with expense of Transportation type only.
     * This test case checks whether the Transportation type expenses record can save correctly.
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
     * Test the {@link SaveToTxt#saveExpenseToTextFile(ArrayList)} method with expense of Utility type only.
     * This test case checks whether the Utility expenses record can save correctly.
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
        assertEquals("EF | chicken sandwich | 10.0 | 2023-10-01 | 2", lines[0]);
        assertEquals("ET | Taxi | 50.0 | 2023-10-01 | 4", lines[1]);
        assertEquals("EU | Electricity | 100.0 | 2023-10-03 | 2", lines[2]);
    }

    /**
     * Test the {@link SaveToTxt#saveIncomeAndExpense(ArrayList, ArrayList)} method with both incomes and expenses.
     * This test case checks whether both incomes and expenses can save correctly
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
