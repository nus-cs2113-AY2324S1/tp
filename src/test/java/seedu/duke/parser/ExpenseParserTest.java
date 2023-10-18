package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.Utilities;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpenseParserTest {
    @Test
    void parseDate_validDate_success() throws KaChinnnngException {
        LocalDate expectedDate = LocalDate.of(2020, 02, 29);
        LocalDate actualDate = ExpenseParser.parseDate("29/02/2020");
        assertEquals(expectedDate, actualDate);
    }

    @Test
    void parseDate_invalidDay_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseDate("32/02/2020"));
    }

    @Test
    void parseDate_invalidDate_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseDate("10/10/20202"));
    }

    @Test
    void parseDate_futureDate_exceptionThrown() {
        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseDate("10/10/3021"));
    }

    @Test
    void testParseExpense_foodCategory_success() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertTrue(ExpenseParser.parseExpense(expenseFields) instanceof Food);
    }

    @Test
    void testParseExpense_transportCategory_success() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "transport");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Bus");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "1.50");

        assertTrue(ExpenseParser.parseExpense(expenseFields) instanceof Transport);
    }

    @Test
    void testParseExpense_utilitiesCategory_success() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "utilities");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Electricity");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "50.00");

        assertTrue(ExpenseParser.parseExpense(expenseFields) instanceof Utilities);
    }

    @Test
    void testParseExpense_invalidCategory_exceptionThrown() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "invalid");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseExpense_missingAmountField_success() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseExpense_missingDescriptionFields_success(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseExpense_missingDateField_success(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "3.50");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testGetIndexValid() throws KaChinnnngException {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.INDEX_FIELD, "1");

        assertEquals(1, ExpenseParser.getIndex(expenseFields));
    }

    @Test
    void testGetIndexInvalid() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.INDEX_FIELD, "a");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.getIndex(expenseFields));
    }

    @Test
    void testParseAmount_aboveLimit_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "1000000.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseCategory_emptyString_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "9999.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseDescription_emptyString_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "9999.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseDate_emptyString_exceptionThrown(){
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "9999.00");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }

    @Test
    void testParseAmount_emptyString_exceptionThrown() {
        HashMap<String, String> expenseFields = new HashMap<>();
        expenseFields.put(ExpenseParser.CATEGORY_FIELD, "food");
        expenseFields.put(ExpenseParser.DESCRIPTION_FIELD, "Chicken Rice");
        expenseFields.put(ExpenseParser.DATE_FIELD, "12/10/2020");
        expenseFields.put(ExpenseParser.AMOUNT_FIELD, "");

        assertThrows(KaChinnnngException.class, () -> ExpenseParser.parseExpense(expenseFields));
    }
}
