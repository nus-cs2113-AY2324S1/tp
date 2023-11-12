package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.ui.MockUi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListCommandTest {
    private ArrayList<Income> incomes;
    private ArrayList<Expense> expenses;
    private ListCommand listCommand;

    private final MockUi mockUi = new MockUi();

    @BeforeEach
    public void setup() throws KaChinnnngException {
        incomes = new ArrayList<>();
        expenses = new ArrayList<>();
        listCommand = new ListCommand(incomes,expenses, mockUi);
    }

    @Test
    public void testWithBothListEmpty(){
        listCommand = new ListCommand(incomes,expenses, mockUi);
        listCommand.execute();
        List<String> messages = mockUi.getPrintedMessages();
        assertEquals(1, messages.size());
        assertEquals("You do not have any records.", messages.get(0));
    }

    @Test
    public void testWithIncomeListEmpty() throws KaChinnnngException {
        try {
            incomes.clear();
            expenses.clear();
            expenses.add(new Food("chicken sandwich",
                    LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
            listCommand = new ListCommand(incomes,expenses, mockUi);
            listCommand.execute();

            List<String> messages = mockUi.getPrintedMessages();
            assertEquals(3, messages.size()); // Three messages: header and two income details
            assertEquals("You have no recorded incomes.", messages.get(0));
            assertEquals("Here are your expenses:", messages.get(1));
            assertEquals("1. Food Expense (LUNCH): chicken sandwich | Date: 01/Oct/2023 | Amount: $10.00",
                    messages.get(2));
        } catch (KaChinnnngException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        listCommand.execute();
        expenses.clear();
    }

    @Test
    public void testWithExpenseListEmpty() throws KaChinnnngException {
        try {
            incomes.clear();
            expenses.clear();
            incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
            listCommand = new ListCommand(incomes,expenses, mockUi);
            listCommand.execute();

            List<String> messages = mockUi.getPrintedMessages();
            assertEquals(3, messages.size()); // Three messages: header and two income details
            assertEquals("Here are your incomes:", messages.get(0));
            assertEquals("1. Income: Salary | Date: 10/Oct/2023 | Amount: $5000.00", messages.get(1));
            assertEquals("You have no recorded expenses.", messages.get(2));
        } catch (KaChinnnngException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
        listCommand.execute();
        expenses.clear();
    }

    @Test
    public void testBothListNotEmpty() throws KaChinnnngException {
        incomes.clear();
        expenses.clear();
        expenses.add(new Food("chicken sandwich",
                LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        listCommand.execute();
    }
}
