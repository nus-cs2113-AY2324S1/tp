package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Income;
import seedu.duke.ui.Ui;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * This class contains JUnit tests for the DeleteIncomeCommand class.
 */
public class DeleteIncomeCommandTest {

    private DeleteIncomeCommand deleteIncomeCommand;
    private Ui ui;
    private ArrayList<Income> incomes;

    @BeforeEach
    public void setUp() {
        deleteIncomeCommand = new DeleteIncomeCommand();
        ui = new Ui();
        incomes = new ArrayList<>();
    }

    /**
     * Test the execution of the DeleteIncomeCommand with valid input. The income should be deleted.
     */
    @Test
    public void testExecute_validInput_incomeDeleted() throws KaChinnnngException {
        // Arrange
        Income income = new Income("Salary", LocalDate.of(2023, 10, 12), 5000.00);
        incomes.add(income);
        String fullCommand = "delete income 1";

        // Act
        assertDoesNotThrow(() -> deleteIncomeCommand.execute(incomes, fullCommand, ui));

        // Assert
        assertEquals(0, incomes.size());
    }

    /**
     * Test the execution of the DeleteIncomeCommand with missing arguments. It should throw a KaChinnnngException.
     */
    @Test
    public void testExecute_missingArgument_exceptionThrown() {
        // Arrange
        String fullCommand = "delete income";

        // Act and Assert
        assertThrows(KaChinnnngException.class, () -> deleteIncomeCommand.execute(incomes, fullCommand, ui));
    }

    /**
     * Test the execution of the DeleteIncomeCommand with an invalid index. It should throw a KaChinnnngException.
     */
    @Test
    public void testExecute_invalidIndex_exceptionThrown() {
        // Arrange
        String fullCommand = "delete income 1";

        // Act and Assert
        assertThrows(KaChinnnngException.class, () -> deleteIncomeCommand.execute(incomes, fullCommand, ui));
    }

    /**
     * Test the execution of the DeleteIncomeCommand with a non-existent income index. It should throw a KaChinnnngException.
     */
    @Test
    public void testExecute_nonExistentIncomeIndex_exceptionThrown() {
        // Arrange
        String fullCommand = "delete income 1";

        // Act and Assert
        assertThrows(KaChinnnngException.class, () -> deleteIncomeCommand.execute(incomes, fullCommand, ui));
    }
}