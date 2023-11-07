package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Expense;
import seedu.duke.ui.Ui;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * This class contains JUnit tests for the DeleteExpenseCommand class.
 */
public class DeleteExpenseCommandTest {

    private DeleteExpenseCommand deleteExpenseCommand;
    private Ui ui;
    private ArrayList<Expense> expenses;

    @BeforeEach
    public void setUp() {
        deleteExpenseCommand = new DeleteExpenseCommand();
        ui = new Ui();
        expenses = new ArrayList<>();
    }

    /**
     * Test the execution of the DeleteExpenseCommand with valid input. The expense should be deleted.
     */
    @Test
    public void testExecute_validInput_expenseDeleted() throws KaChinnnngException {
        // Arrange
        Expense expense = new Expense("Lunch", LocalDate.now().minusDays(1), 20.00);
        expenses.add(expense);
        String fullCommand = "delete expense 1";

        // Act
        assertDoesNotThrow(() -> deleteExpenseCommand.execute(expenses, fullCommand, ui));

        // Assert
        assertEquals(0, expenses.size());
    }

    /**
     * Test the execution of the DeleteExpenseCommand with missing arguments. It should throw a KaChinnnngException.
     */
    @Test
    public void testExecute_missingArgument_exceptionThrown() {
        // Arrange
        String fullCommand = "delete expense";

        // Act and Assert
        assertThrows(KaChinnnngException.class, () -> deleteExpenseCommand.execute(expenses, fullCommand, ui));
    }

    /**
     * Test the execution of the DeleteExpenseCommand with an invalid index. It should throw a KaChinnnngException.
     */
    @Test
    public void testExecute_invalidIndex_exceptionThrown() {
        // Arrange
        String fullCommand = "delete expense 1";

        // Act and Assert
        assertThrows(KaChinnnngException.class, () -> deleteExpenseCommand.execute(expenses, fullCommand, ui));
    }

    /**
     * Test the execution of the DeleteExpenseCommand with a non-existent expense index.
     * It should throw a KaChinnnngException.
     */
    @Test
    public void testExecute_nonExistentExpenseIndex_exceptionThrown() {
        // Arrange
        String fullCommand = "delete expense 1";

        // Act and Assert
        assertThrows(KaChinnnngException.class, () -> deleteExpenseCommand.execute(expenses, fullCommand, ui));
    }
}
