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
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetFromTxtTest {
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
     * Test the {@link SaveToTxt#saveIncomeToTextFile(ArrayList)} method with multiple incomes records without expenses,
     * This test case checks whether the more than one income from the txt file save to the list correctly
     */
    @Test
    public void testGetFromTxtWithMoreThanOneIncomes() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus", LocalDate.of(2023, 10, 15), 1000.0));
        test.saveIncomeToTextFile(incomes);
        ArrayList<Income> newIncomes = new ArrayList<>();
        ArrayList<Expense> newExpenses = new ArrayList<>();
        GetFromTxt getFromTxt = new GetFromTxt(path);
        getFromTxt.getFromTextFile(newIncomes, newExpenses);
        assertEquals("Salary", newIncomes.get(0).getDescription());
        assertEquals("Bonus", newIncomes.get(1).getDescription());
        assertEquals(LocalDate.of(2023, 10, 10),  newIncomes.get(0).getDate());
        assertEquals(LocalDate.of(2023, 10, 15),  newIncomes.get(1).getDate());
        assertEquals(5000.0, newIncomes.get(0).getAmount());
        assertEquals(1000.0, newIncomes.get(1).getAmount());
    }

    /**
     * Test the {@link GetFromTxt#getFromTextFile(ArrayList, ArrayList)} method
     * with different expense type without incomes, This test case checks whether the different type of expenses
     * from the txt file save to the list correctly
     */
    @Test
    public void testGetFromTxtWithDifferentExpenseType() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        expenses.add(new Food("chicken sandwich",
                LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        expenses.add(new Transport("Taxi",
                LocalDate.of(2023, 10, 2),50.0, TransportationType.FUEL));
        expenses.add(new Utilities("Electricity",
                LocalDate.of(2023, 10, 3),100.0, UtilityType.ELECTRICITY));
        test.saveExpenseToTextFile(expenses);
        ArrayList<Income> newIncomes = new ArrayList<>();
        ArrayList<Expense> newExpenses = new ArrayList<>();
        GetFromTxt getFromTxt = new GetFromTxt(path);
        getFromTxt.getFromTextFile(newIncomes, newExpenses);
        assertEquals("chicken sandwich", newExpenses.get(0).getDescription());
        assertEquals("Taxi", newExpenses.get(1).getDescription());
        assertEquals("Electricity", newExpenses.get(2).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1),  newExpenses.get(0).getDate());
        assertEquals(LocalDate.of(2023, 10, 2),  newExpenses.get(1).getDate());
        assertEquals(LocalDate.of(2023, 10, 3),  newExpenses.get(2).getDate());
        assertEquals(10.0, newExpenses.get(0).getAmount());
        assertEquals(50.0, newExpenses.get(1).getAmount());
        assertEquals(100.0, newExpenses.get(2).getAmount());
        assertEquals(MealType.LUNCH, ((Food) newExpenses.get(0)).getMealType());
        assertEquals(TransportationType.FUEL, ((Transport) newExpenses.get(1)).getTransportationType());
        assertEquals(UtilityType.ELECTRICITY, ((Utilities) newExpenses.get(2)).getUtilityType());
    }

    /**
     * Test the {@link GetFromTxt#getFromTextFile(ArrayList, ArrayList)} method with different expense type and incomes.
     * This test case checks whether the different type of expenses and income record get from
     * the txt file save to the list correctly
     */
    @Test
    public void testGetFromTxtWithDifferentIncomesAndExpenses() throws KaChinnnngException, IOException {
        SaveToTxt test = new SaveToTxt(path);
        new ClearAll(incomes,expenses).clearAllIncomeAndExpense();
        test.saveIncomeAndExpense(incomes, expenses);
        expenses.add(new Food("chicken sandwich",
                LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        expenses.add(new Transport("Taxi",
                LocalDate.of(2023, 10, 2),50.0, TransportationType.FUEL));
        expenses.add(new Utilities("Electricity",
                LocalDate.of(2023, 10, 3),100.0, UtilityType.ELECTRICITY));
        incomes.add(new Income("Salary",
                LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus",
                LocalDate.of(2023, 10, 15), 1000.0));
        test.saveIncomeAndExpense(incomes,expenses);
        ArrayList<Income> newIncomes = new ArrayList<>();
        ArrayList<Expense> newExpenses = new ArrayList<>();
        GetFromTxt getFromTxt = new GetFromTxt(path);
        getFromTxt.getFromTextFile(newIncomes, newExpenses);
        assertEquals("chicken sandwich", newExpenses.get(0).getDescription());
        assertEquals("Taxi", newExpenses.get(1).getDescription());
        assertEquals("Electricity", newExpenses.get(2).getDescription());
        assertEquals(LocalDate.of(2023, 10, 1),  newExpenses.get(0).getDate());
        assertEquals(LocalDate.of(2023, 10, 2),  newExpenses.get(1).getDate());
        assertEquals(LocalDate.of(2023, 10, 3),  newExpenses.get(2).getDate());
        assertEquals(10.0, newExpenses.get(0).getAmount());
        assertEquals(50.0, newExpenses.get(1).getAmount());
        assertEquals(100.0, newExpenses.get(2).getAmount());
        assertEquals(MealType.LUNCH, ((Food) newExpenses.get(0)).getMealType());
        assertEquals(TransportationType.FUEL, ((Transport) newExpenses.get(1)).getTransportationType());
        assertEquals(UtilityType.ELECTRICITY, ((Utilities) newExpenses.get(2)).getUtilityType());
        assertEquals("Salary", newIncomes.get(0).getDescription());
        assertEquals("Bonus", newIncomes.get(1).getDescription());
        assertEquals(LocalDate.of(2023, 10, 10),  newIncomes.get(0).getDate());
        assertEquals(LocalDate.of(2023, 10, 15),  newIncomes.get(1).getDate());
        assertEquals(5000.0, newIncomes.get(0).getAmount());
        assertEquals(1000.0, newIncomes.get(1).getAmount());
    }
    /**
     * Test the {@link GetFromTxt#getFromTextFile(ArrayList, ArrayList)} method with Both incorrect format.
     * This test case checks whether the program still working when user changes the txt file.
     */
    @Test
    public void testGetFromTxtWithWrongFormat() throws KaChinnnngException, IOException {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("nolthing special");
            fw.write("EF | chicken sandwich | 10000000000.0 | 2023-10-01 | 2\n");
            fw.write("EF | chicken sandwich | -1 | 2023-10/01 | 2\n");
            fw.write("EF \n");
            fw.write("ABC | chicken sandwich | 1000.0 | 2023-10-01 | 1\n");
            fw.write("EF | chicken sandwich | 1000.0 | 2023-10-01 | asdf\n");
            fw.write("EF | chicken sandwich | 10000000000.0 | 2023-10-01 | 32\n");
        } catch (IOException e) {
            System.out.println("Error");
        }
        ArrayList<Income> newIncomes = new ArrayList<>();
        ArrayList<Expense> newExpenses = new ArrayList<>();
        GetFromTxt getFromTxt = new GetFromTxt(path);
        getFromTxt.getFromTextFile(newIncomes, newExpenses);
        assertEquals(0, newIncomes.size());
        assertEquals(0, newExpenses.size());
    }

}
