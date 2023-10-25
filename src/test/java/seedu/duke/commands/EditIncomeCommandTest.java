package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.Income;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditIncomeCommandTest {
    private EditIncomeCommand editIncomeCommand;
    private ArrayList<Income> incomes;
    private String fullCommand;

    @BeforeEach
    public void setUp() throws KaChinnnngException {
        // Initialize the incomes and fullCommand for testing
        incomes = new ArrayList<>();
        incomes.add(new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0));
        incomes.add(new Income("Bonus", LocalDate.of(2023, 10, 15), 1000.0));
    }

    @Test
    public void testExecute_invalidIndex_exceptionThrown() throws KaChinnnngException {
        fullCommand = "edit income 3 income /description salary for October /date 01/10/2023 /amount 5000.00";
        editIncomeCommand = new EditIncomeCommand(incomes, fullCommand);

        assertThrows(KaChinnnngException.class, () -> {
            // This test checks if the execute method throws an exception when the index is out of bounds
            editIncomeCommand.execute();
        });
    }

    @Test
    public void testExecute_validInput_incomeEdited() throws KaChinnnngException {
        fullCommand = "edit income 1 income /description salary for October /date 01/10/2023 /amount 5000.00";
        editIncomeCommand = new EditIncomeCommand(incomes, fullCommand);

        // Test if the execute method changes the income and prints the changes
        assertDoesNotThrow(() -> editIncomeCommand.execute());

        // Verify if the income was updated correctly
        Income updatedincome = incomes.get(0);
        assertEquals("Income: salary for October | Date: 01/Oct/2023 | Amount: $5000.00",
                updatedincome.toString());
    }

    @Test
    public void testExecute_invalidIncome_exceptionThrown() throws KaChinnnngException {
        // Test if execute method throws a KaChinnnngException for an invalid command
        fullCommand = "edit income 1 invalid income command";
        editIncomeCommand = new EditIncomeCommand(incomes, fullCommand);
        assertThrows(KaChinnnngException.class, () -> editIncomeCommand.execute());
    }
}
