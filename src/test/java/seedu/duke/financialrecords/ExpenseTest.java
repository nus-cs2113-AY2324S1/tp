package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test suite for the {@link Expense} class.
 * This class contains unit tests to validate the behavior of the Expense class and ensure
 * correctness in the representation and management of financial expenses.
 */
public class ExpenseTest {

    private Expense expense;

    /**
     * Initializes common test fixtures before each test execution.
     */
    @BeforeEach
    void setUp() {
        try {
            expense = new Expense("Lunch", LocalDate.now().minusDays(1), 20.00);
        } catch (KaChinnnngException e) {
            fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    /**
     * Tests if an exception is thrown when the description of an Expense object is empty.
     */
    @Test
    void testExpenseDescription_notEmpty(){
        assertThrows(KaChinnnngException.class, () -> new Expense("", LocalDate.now(), 20.00));
    }

    /**
     * Tests if an exception is thrown when the amount of an Expense object is negative.
     */
    @Test
    void testExpenseAmount_notNegative(){
        assertThrows(KaChinnnngException.class, () -> new Expense("Lunch", LocalDate.now(), -20.00));
    }

    /**
     * Tests if an exception is thrown when the date of an Expense object is in the future.
     */
    @Test
    void testExpenseDate_notFuture(){
        assertThrows(KaChinnnngException.class, () -> new Expense("Lunch", LocalDate.now().plusDays(1), 20.00));
    }

    /**
     * Tests if the correct description of the Expense object is returned.
     */
    @Test
    void getDescription() {
        assertEquals("Lunch", expense.getDescription());
    }

    /**
     * Tests if the correct date of the Expense object is returned.
     */
    @Test
    void getDate() {
        assertEquals(LocalDate.now().minusDays(1), expense.getDate());
    }

    /**
     * Tests if the correct amount of the Expense object is returned.
     */
    @Test
    void getAmount() {
        assertEquals(20.00, expense.getAmount());
    }

    /**
     * Tests if the correct date string of the Expense object is returned.
     */
    @Test
    void getDateString() {
        assertEquals(LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")),
                expense.getDateString());
    }

    /**
     * Tests the string representation of the Expense object.
     */
    @Test
    void testToString() {
        assertEquals("Expense: Lunch | Date: " +
                        LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")) +
                        " | Amount: $20.00",
                expense.toString());
    }
}
