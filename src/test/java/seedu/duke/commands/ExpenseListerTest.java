package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;
import seedu.duke.ui.MockUi;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpenseListerTest {
    private ArrayList<Expense> expenses;
    private MockUi mockUi;

    @BeforeEach
    void setUp() {
        expenses = new ArrayList<>();
        mockUi = new MockUi();
    }

    @Test
    void listExpenses_emptyList_displayNoExpenses() {
        expenses.clear();
        ExpenseLister expenseLister = new ExpenseLister(expenses, mockUi);
        expenseLister.listExpenses();

        List<String> messages = mockUi.getPrintedMessages();
        assertEquals(1, messages.size());
        assertEquals("You have no recorded expenses.", messages.get(0));
    }

    @Test
    void listExpenses_nonEmptyList_displaysAllExpenses() throws KaChinnnngException {
        expenses.clear();
        expenses.add(new Food("chicken sandwich",
                LocalDate.of(2023, 10, 1),10.0, MealType.LUNCH));
        expenses.add(new Transport("Taxi",
                LocalDate.of(2023, 10, 2),50.0, TransportationType.FUEL));
        expenses.add(new Utilities("Electricity",
                LocalDate.of(2023, 10, 3),100.0, UtilityType.ELECTRICITY));
        ExpenseLister expenseLister = new ExpenseLister(expenses, mockUi);
        expenseLister.listExpenses();

        List<String> messages = mockUi.getPrintedMessages();
        assertEquals(4, messages.size()); // Three messages: header and two income details
        assertEquals("Here are your expenses:", messages.get(0));
        assertEquals("1. " + expenses.get(0).toString(), messages.get(1));
        assertEquals("2. " + expenses.get(1).toString(), messages.get(2));
        assertEquals("3. " + expenses.get(2).toString(), messages.get(3));

    }

    @Test
    void testExpenseConstructor_negativeAmount() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            new Expense("Test", LocalDate.now(), -50.0);
        });
        String expectedMessage = "Amount cannot be negative!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);  // Using assertEquals for clarity
    }

}
