package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IncomeParserTest {
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

    @Test
    public void testParseDate() throws KaChinnnngException {
        LocalDate date = IncomeParser.parseDate("12/10/2023");
        assertEquals(LocalDate.of(2023, 10, 12), date);
    }

    @Test
    public void testParseDate_invalidDate() {
        assertThrows(KaChinnnngException.class, () -> {
            IncomeParser.parseDate("12/13/2023");
        });
    }

    @Test
    public void testParseDate_futureDate() {
        assertThrows(KaChinnnngException.class, () -> {
            IncomeParser.parseDate("12/10/2025");
        });
    }

    @Test
    public void testGetIndex_missingField() {
        HashMap<String, String> inputMap = new HashMap<>();
        assertThrows(KaChinnnngException.class, () -> {
            IncomeParser.getIndex(inputMap);
        });
    }
}
