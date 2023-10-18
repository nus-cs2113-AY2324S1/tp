package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpenseTest {

    private Expense expense;

    @BeforeEach
    void setUp() {
        try {
            expense = new Expense("Lunch", LocalDate.now().minusDays(1), 20.00);
        } catch (KaChinnnngException e) {
            fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test
    void testExpenseDescription_notEmpty(){
        assertThrows(KaChinnnngException.class, () -> new Expense("", LocalDate.now(), 20.00));
    }

    @Test
    void testExpenseAmount_notNegative(){
        assertThrows(KaChinnnngException.class, () -> new Expense("Lunch", LocalDate.now(), -20.00));
    }

    @Test
    void testExpenseDate_notFuture(){
        assertThrows(KaChinnnngException.class, () -> new Expense("Lunch", LocalDate.now().plusDays(1), 20.00));
    }

    @Test
    void getDescription() {
        assertEquals("Lunch", expense.getDescription());
    }

    @Test
    void getDate() {
        assertEquals(LocalDate.now().minusDays(1), expense.getDate());
    }

    @Test
    void getAmount() {
        assertEquals(20.00, expense.getAmount());
    }

    @Test
    void getDateString() {
        assertEquals(LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")),
                expense.getDateString());
    }

    @Test
    void testToString() {
        assertEquals("Expense: Lunch | Date: " +
                        LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MMM/yyyy")) +
                        " | Amount: $20.00",
                expense.toString());
    }
}
