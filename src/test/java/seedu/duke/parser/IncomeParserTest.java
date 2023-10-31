package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Contains JUnit tests for the {@code IncomeParser} class. This class ensures that the parsing methods
 * related to incomes function as expected and handle invalid inputs gracefully.
 */
public class IncomeParserTest {

    /**
     * This method tests the parseIncome method in IncomeParser.
     * It verifies if the incomeFields are correctly parsed into an Income object.
     */
    @Test
    public void testParseIncome() throws KaChinnnngException {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "5000.00");

        Income income = IncomeParser.parseIncome(incomeFields);
        assertEquals("Salary", income.getDescription());
        assertEquals(LocalDate.of(2023, 10, 12), income.getDate());
        assertEquals(5000.00, income.getAmount());
    }

    /**
     * This method tests the parseDate method in IncomeParser
     * Specifically, it tests if the date is parsed correctly.
     *
     * @throws KaChinnnngException if the parsed date is invalid
     */
    @Test
    public void testParseDate_validDate() throws KaChinnnngException {
        LocalDate result = IncomeParser.parseDate("12/10/2023");
        assertEquals(LocalDate.of(2023, 10, 12), result);
    }

    /**
     * This method tests the parseDate method in IncomeParser
     * Specifically, it tests if a KaChinnnngException is thrown when trying to parse an invalid date.
     *
     * @see KaChinnnngException if the parsed date is invalid
     */
    @Test
    public void testParseDate_invalidDate() {
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseDate("12/13/2023"));
    }

    /**
     * This method tests the parseDate method in IncomeParser.
     * Specifically, it tests if a KaChinnnngException is thrown when trying to parse a date that is in the future.
     *
     */
    @Test
    public void testParseDate_futureDate() {
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseDate("12/10/2025"));
    }

    /**
     * This method tests the parseDate method in IncomeParser.
     * Specifically, it tests if a KaChinnnngException is thrown when trying to parse an invalid day.
     *
     */
    @Test
    void testParseDate_invalidDay(){
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseDate("32/10/2023"));
    }

    /**
     * This method tests the getIndex method in IncomeParser when trying to get index from an empty input.
     *
     */
    @Test
    public void testGetIndex_missingField() {
        HashMap<String, String> inputMap = new HashMap<>();
        assertThrows(KaChinnnngException.class, () -> IncomeParser.getIndex(inputMap));
    }

    /**
     * This method tests the behaviour when user inputs amount that is above the limit.
     *
     */
    @Test
    void testParseAmount_aboveLimit_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "1000000000.00");

        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * This method tests the behaviour when user inputs an empty string for description.
     *
     */
    @Test
    void testParseDescription_emptyString_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "");
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "10.00");

        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * This method tests the behaviour when user inputs an empty string for date.
     *
     */
    @Test
    void testParseDate_emptyString_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.DATE_FIELD, "");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "10.00");

        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * This method tests the behaviour when user inputs an empty string for amount.
     *
     */
    @Test
    void testParseAmount_emptyString_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "");

        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * This method tests the behaviour when user inputs an invalid index.
     * Specifically negative index
     */
    @Test
    void testGetIndex_negativeIndex_exceptionThrown() {
        HashMap<String, String> inputMap = new HashMap<>();
        inputMap.put(IncomeParser.INDEX_FIELD, "-1");

        assertThrows(KaChinnnngException.class, () -> IncomeParser.getIndex(inputMap));
    }

    /**
     * This method tests the behaviour when user inputs an invalid index.
     * Specifically zero index
     */
    @Test
    void testGetIndex_zeroIndex_exceptionThrown() {
        HashMap<String, String> inputMap = new HashMap<>();
        inputMap.put(IncomeParser.INDEX_FIELD, "0");

        assertThrows(KaChinnnngException.class, () -> IncomeParser.getIndex(inputMap));
    }

    /**
     * Tests for an exception when a zero index is provided in the input data.
     */
    @Test
    void testParseIncome_nullArguments_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(null));
    }

    /**
     * Checks if an exception is thrown when null arguments are passed to the {@code parseIncome} method.
     */
    @Test
    void testParseIncome_missingDescription_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "5000.00");
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * Tests if an exception is thrown when the date is missing in incomeFields.
     */
    @Test
    void testParseIncome_missingDate_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "5000.00");
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * Checks if an exception is thrown when the amount is missing in incomeFields.
     */
    @Test
    void testParseIncome_missingAmount_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * Validates that an exception is thrown when the amount in incomeFields has an invalid format.
     */
    @Test
    void testParseAmount_invalidFormat_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put(IncomeParser.DESCRIPTION_FIELD, "Salary");
        incomeFields.put(IncomeParser.DATE_FIELD, "12/10/2023");
        incomeFields.put(IncomeParser.AMOUNT_FIELD, "5,000.00");  // Comma is not expected
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

    /**
     * Tests for an exception when a non-numeric value is provided for the index in the input data.
     */
    @Test
    void testGetIndex_invalidFormat_exceptionThrown() {
        HashMap<String, String> inputMap = new HashMap<>();
        inputMap.put(IncomeParser.INDEX_FIELD, "abc");
        assertThrows(KaChinnnngException.class, () -> IncomeParser.getIndex(inputMap));
    }

    /**
     * Checks if an exception is thrown when an unrecognized field is provided in incomeFields.
     */
    @Test
    void testParseIncome_nonExistingField_exceptionThrown() {
        HashMap<String, String> incomeFields = new HashMap<>();
        incomeFields.put("nonExistingField", "Value");
        assertThrows(KaChinnnngException.class, () -> IncomeParser.parseIncome(incomeFields));
    }

}
