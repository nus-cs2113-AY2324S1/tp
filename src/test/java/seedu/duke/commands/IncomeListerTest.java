package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.financialrecords.Income;
import seedu.duke.ui.MockUi;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncomeListerTest {
    private ArrayList<Income> incomes;
    private MockUi mockUi;

    @BeforeEach
    void setUp() {
        incomes = new ArrayList<>();
        mockUi = new MockUi();
    }

    @Test
    void listIncomes_emptyList_displayNoIncomes() {
        IncomeLister incomeLister = new IncomeLister(incomes, mockUi);
        incomeLister.listIncomes();

        List<String> messages = mockUi.getPrintedMessages();
        assertEquals(1, messages.size());
        assertEquals("You have no recorded incomes.", messages.get(0));
    }

    @Test
    void listIncomes_nonEmptyList_displaysAllIncomes() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate date1 = LocalDate.parse("01/01/2023", formatter);
            LocalDate date2 = LocalDate.parse("02/01/2023", formatter);

            incomes.add(new Income("Description 1", date1, 50.0));
            incomes.add(new Income("Description 2", date2, 100.0));

            IncomeLister incomeLister = new IncomeLister(incomes, mockUi);
            incomeLister.listIncomes();

            List<String> messages = mockUi.getPrintedMessages();
            assertEquals(3, messages.size()); // Three messages: header and two income details
            assertEquals("Here are your incomes:", messages.get(0));
            assertEquals("1. " + incomes.get(0).toString(), messages.get(1));
            assertEquals("2. " + incomes.get(1).toString(), messages.get(2));
        } catch (KaChinnnngException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testIncomeConstructor_negativeAmount() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            new Income("Test", LocalDate.now(), -50.0);
        });
        String expectedMessage = "Amount cannot be negative!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);  // Using assertEquals for clarity
    }

    @Test
    void testToString_validIncome() throws KaChinnnngException {
        Income income = new Income("Test", LocalDate.of(2023, 1, 1), 50.0);
        String expected = "Income: Test | Date: 01/Jan/2023 | Amount: $50.00";
        assertEquals(expected, income.toString());
    }

    @Test
    void testLoggerFileCreation() throws KaChinnnngException {
        new Income("Logger Test", LocalDate.now(), 50.0);
        File logFile = new File("logs/Income.log");
        assertTrue(logFile.exists());
    }

}
