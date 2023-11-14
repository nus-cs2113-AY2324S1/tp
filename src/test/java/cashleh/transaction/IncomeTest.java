package cashleh.transaction;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeTest {
    Income testIncome = new Income("pocket money", 200);
    Income testIncome2 = new Income("salary", 8000, LocalDate.of(2023, 9, 20));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void testToStringWithoutDate() {
        String expectedString = "Income: pocket money (Amount: 200.0, Date: " + LocalDate.now().format(formatter) + ")";
        assertEquals(testIncome.toString(), expectedString);
    }

    @Test
    void testToStringWithDate() {
        String expectedString = "Income: salary (Amount: 8000.0, Date: 20/09/2023)";
        assertEquals(testIncome2.toString(), expectedString);
    }
}
