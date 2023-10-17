package cashleh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncomeTest {
    Income testIncome = new Income("pocket money", 200);

    @Test
    void isOneTimeIncome() {
        System.out.println(testIncome.getTotalIncome());
        assertEquals(testIncome.isOneTimeIncome(), "One time");
        testIncome.setOneTimeIncome(false);
        assertEquals(testIncome.isOneTimeIncome(), "Recurring");
        System.out.println(testIncome.getTotalIncome());
    }

    @Test
    void testToString() {
        System.out.println(testIncome.getTotalIncome());
        assertEquals(testIncome.toString(), "pocket money (amount: 200.0)");
        System.out.println(testIncome.getTotalIncome());
    }
}
