package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditExpenseCommandTest {
    private ArrayList<Expense> expenses;
    private String fullCommand;

    @BeforeEach
    public void setUp() throws KaChinnnngException {
        // Initialize the expenses and fullCommand for testing
        expenses = new ArrayList<>();
        expenses.add(new Expense("Rent", LocalDate.of(2023, 10, 5), 2000.0));
        expenses.add(new Expense("Groceries", LocalDate.of(2023, 10, 7), 100.0));
    }

    @Test
    public void testExecute_invalidIndex_exceptionThrown() throws KaChinnnngException {
        fullCommand = "edit expense 3 expense /cat food /type lunch /de chicken sandwich /date 01/10/2023 /amt 10.00";
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(expenses, fullCommand);

        assertThrows(KaChinnnngException.class, () -> {
            // This test checks if the execute method throws an exception when the index is out of bounds
            editExpenseCommand.execute();
        });
    }

    @Test
    public void testExecute_validInput_expenseEdited() throws KaChinnnngException {
        fullCommand = "edit expense 1 expense /cat food /type lunch /de chicken rice /date 01/10/2023 /amt 10.00";
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(expenses, fullCommand);

        // Test if the execute method changes the expense and prints the changes
        assertDoesNotThrow(() -> editExpenseCommand.execute());

        // Verify if the expense was updated correctly
        Expense updatedExpense = expenses.get(0);
        assertEquals("Food Expense (LUNCH): chicken rice | Date: 01/Oct/2023 | Amount: $10.00",
                updatedExpense.toString());
    }

    @Test
    public void testExecute_invalidExpense_exceptionThrown() throws KaChinnnngException {
        // Test if execute method throws a KaChinnnngException for an invalid command
        fullCommand = "edit expense 1 invalid expense command";
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(expenses, fullCommand);
        assertThrows(KaChinnnngException.class, () -> editExpenseCommand.execute());
    }
}
