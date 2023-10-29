package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.financialrecords.Income;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IncomeManagerTest {
    private IncomeManager incomeManager;

    @BeforeEach
    void setUp() {
        incomeManager = new IncomeManager("/de Grocery /date 29/10/2023 /amt 50.0");
    }

    @Test
    void constructor_initializesCorrectly() {
        assertNotNull(incomeManager);
    }

    @Test
    void execute_parsesIncomeSuccessfully() throws KaChinnnngException {
        incomeManager.execute();
        Income income = incomeManager.getNewIncome();

        assertNotNull(income);
        // You may also want to check the properties of `income` to ensure it's been parsed correctly.
    }
    @Test
    void execute_missingFields_throwsException() {
        IncomeManager managerWithMissingFields = new IncomeManager("/de Grocery /date 29/10/2023");
        assertThrows(KaChinnnngException.class, managerWithMissingFields::execute);
    }

    @Test
    void execute_extraFields_throwsException() {
        IncomeManager managerWithExtraFields = new IncomeManager("/de Grocery /date 29/10/2023 " +
                "/amt 50.0 /extraField Something");
        assertThrows(KaChinnnngException.class, managerWithExtraFields::execute);
    }

    @Test
    void constructor_nullDetails_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new IncomeManager(null));
    }

    @Test
    void execute_incorrectFieldOrder_throwsException() {
        IncomeManager managerWithIncorrectOrder = new IncomeManager("/amt 50.0 /de Grocery /date 29/10/2023");
        assertThrows(KaChinnnngException.class, managerWithIncorrectOrder::execute);
    }

    @Test
    void execute_spacesInFields_parsedCorrectly() throws KaChinnnngException {
        IncomeManager managerWithSpaces = new IncomeManager("/de Grocery Store /date 29/10/2023 /amt 50.0");
        managerWithSpaces.execute();
        Income income = managerWithSpaces.getNewIncome();
        assertNotNull(income);
    }

    @Test
    void execute_incorrectDateFormat_throwsException() {
        IncomeManager managerWithBadDate = new IncomeManager("/de Grocery /date 29-10-2023 /amt 50.0");
        assertThrows(KaChinnnngException.class, managerWithBadDate::execute);
    }

    @Test
    void execute_incorrectAmountFormat_throwsException() {
        IncomeManager managerWithBadAmount = new IncomeManager("/de Grocery /date 29/10/2023 /amt fifty");
        assertThrows(KaChinnnngException.class, managerWithBadAmount::execute);
    }

    @Test
    void testLoggerFileCreation() {
        // Initialization
        IncomeManager incomeManager = new IncomeManager("/de Grocery /date 29/10/2023 /amt 50.0");

        // Assertion
        File loggerFile = new File("logs/IncomeManager.log");
        assertTrue(loggerFile.exists(), "Logger file should be created");

        // Cleanup (optional, depending on whether you want to keep logs from test runs)
        loggerFile.delete();
    }

}
