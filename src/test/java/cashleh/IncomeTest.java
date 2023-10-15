package cashleh;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeTest {
    Income testIncome = new Income("pocket money", 200);

//    @Test
//    void isOneTimeIncome() {
//        assertEquals(testIncome.isOneTimeIncome(), "One time");
//        testIncome.setOneTimeIncome(false);
//        assertEquals(testIncome.isOneTimeIncome(), "Recurring");
//    }

//    @Test
//    void testToString() {
//        assertEquals(testIncome.toString(), "\t+ S$ 200: pocket money (date: 2023-10-10, One time)");
//    }
}
