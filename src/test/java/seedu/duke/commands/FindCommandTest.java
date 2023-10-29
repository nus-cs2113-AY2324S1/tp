package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

import java.util.ArrayList;

import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;
import seedu.duke.ui.MockUi;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;



public class FindCommandTest {
    private ArrayList<Income> testIncomes;
    private ArrayList<Expense> testExpenses;
    private MockUi mockUi;

    @BeforeEach
    void setUp() {
        testIncomes = new ArrayList<>();
        testExpenses = new ArrayList<>();
        mockUi = new MockUi(); // Assuming MockUi has a parameterless constructor.
    }

    @Test
    void testValidFindCommandExpenseByCategory() throws Exception {
        Food foodExpense = new Food("Burger",
                LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        testExpenses.add(foodExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", "Food",
                null, null, mockUi);
        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testValidFindCommandExpenseByDescription() throws Exception {
        Food foodExpense = new Food("Burger",
                LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        testExpenses.add(foodExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                "Burger", null, mockUi);

        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testValidFindCommandExpenseByDate() throws Exception {
        Food foodExpense = new Food("Burger",
                LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        testExpenses.add(foodExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                null, "10/Oct/2023", mockUi);

        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testValidFindCommandExpenseByMonth() throws Exception {
        // Adding multiple expenses in October
        Food foodExpense1 = new Food("Burger", LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        Food foodExpense2 = new Food("Pizza", LocalDate.of(2023, 10, 15), 8.0, MealType.DINNER);
        Food foodExpense3 = new Food("Sandwich", LocalDate.of(2023, 10, 28), 3.0, MealType.BREAKFAST);

        // Adding an expense from another month for control
        Food foodExpenseOutsideMonth = new Food("Salad", LocalDate.of(2023, 9, 30), 4.0, MealType.DINNER);

        testExpenses.add(foodExpense1);
        testExpenses.add(foodExpense2);
        testExpenses.add(foodExpense3);
        testExpenses.add(foodExpenseOutsideMonth);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null, null, "Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00, "
                + "Food Expense (DINNER): Pizza | Date: 15/Oct/2023 | Amount: $8.00, "
                + "Food Expense (BREAKFAST): Sandwich | Date: 28/Oct/2023 | Amount: $3.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }


    @Test
    void testValidFindCommandExpenseByDate2() throws Exception {
        Food foodExpense = new Food("Burger",
                LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        testExpenses.add(foodExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                null, "10/10/2023", mockUi);

        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testMultipleMatchingExpenses() throws Exception{
        Food foodExpense1 = new Food ("Burger",
                LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        Food foodExpense2 = new Food ("Burger",
                LocalDate.of(2023,10,11), 5.0, MealType.DINNER);
        testExpenses.add(foodExpense1);
        testExpenses.add(foodExpense2);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                "Burger", null, mockUi);
        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00, "
                + "Food Expense (DINNER): Burger | Date: 11/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testNoMatchingExpenses() throws Exception {
        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                "NonexistentItem", null, mockUi);
        findCommand.execute();

        assertTrue(mockUi.getPrintedMessages().contains("No matching expenses found."));
    }

    @Test
    void testInvalidDateFormat() throws Exception {
        Food foodExpense = new Food("Burger", LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        testExpenses.add(foodExpense);

        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                    null, "20231010", mockUi);  // Invalid date format
            findCommand.execute();
        });

        assertTrue(exception.getMessage().contains("Invalid date format."));
    }

    @Test
    void testNullValues() throws Exception {
        Food foodExpense = new Food("Burger", LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        testExpenses.add(foodExpense);

        assertThrows(KaChinnnngException.class, () -> {
            FindCommand findCommand = new FindCommand(testIncomes, testExpenses,
                    null, null, null, null, mockUi);
            findCommand.execute();
        });
    }

    @Test
    void testValidFindCommandIncomeByDescription() throws Exception {
        Income income = new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0);
        testIncomes.add(income);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "income", null,
                "Salary", null, mockUi);
        findCommand.execute();

        String expectedOutput = "[Income: Salary | Date: 10/Oct/2023 | Amount: $5000.00]";
        assertEquals(expectedOutput, mockUi.getShowIncomes().get(0));
    }

    @Test
    void testValidFindCommandIncomeByDate() throws Exception {
        Income income = new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0);
        testIncomes.add(income);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "income", null,
                null, "10/Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Income: Salary | Date: 10/Oct/2023 | Amount: $5000.00]";
        assertEquals(expectedOutput, mockUi.getShowIncomes().get(0));
    }

    @Test
    void testMultipleIncomeMatches() throws Exception {
        Income income1 = new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0);
        Income income2 = new Income("Salary", LocalDate.of(2023, 10, 11), 5000.0);
        testIncomes.add(income1);
        testIncomes.add(income2);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "income", null,
                "Salary", null, mockUi);
        findCommand.execute();

        String expectedOutput = "[Income: Salary | Date: 10/Oct/2023 | Amount: $5000.00, "
                + "Income: Salary | Date: 11/Oct/2023 | Amount: $5000.00]";
        assertEquals(expectedOutput, mockUi.getShowIncomes().get(0));
    }

    @Test
    void testNoMatchingIncomes() throws Exception {
        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "income", null,
                "NonexistentItem", null, mockUi);
        findCommand.execute();

        assertTrue(mockUi.getPrintedMessages().contains("No matching incomes found."));
    }

    @Test
    void testIncomesByMonth() throws Exception {
        Income income1 = new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0);
        Income income2 = new Income("Salary", LocalDate.of(2023, 10, 11), 5000.0);
        Income income3 = new Income("Salary", LocalDate.of(2023, 9, 11), 5000.0);
        testIncomes.add(income1);
        testIncomes.add(income2);
        testIncomes.add(income3);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "income", null,
                null, "Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Income: Salary | Date: 10/Oct/2023 | Amount: $5000.00, "
                + "Income: Salary | Date: 11/Oct/2023 | Amount: $5000.00]";
        assertEquals(expectedOutput, mockUi.getShowIncomes().get(0));
    }

    @Test
    void testIncomeFutureDate() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            Income futureIncome = new Income("Bonus", LocalDate.of(2025, 10, 10), 7000.0);
            testIncomes.add(futureIncome);
        });

        assertTrue(exception.getMessage().contains("Date cannot be in the future!"));
    }

    @Test
    void testFindExpenseByDescriptionAndDate() throws Exception {
        Food foodExpense1 = new Food("Burger", LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        Food foodExpense2 = new Food("Burger", LocalDate.of(2023, 10, 11), 5.0, MealType.DINNER);
        testExpenses.add(foodExpense1);
        testExpenses.add(foodExpense2);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", null,
                "Burger", "10/Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testFindExpenseByDescriptionAndCategory() throws Exception {
        Food foodExpense1 = new Food("Burger", LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
        Food foodExpense2 = new Food("Pizza", LocalDate.of(2023, 10, 11), 5.0, MealType.DINNER);
        Transport transportExpense = new Transport("Bus",
                LocalDate.of(2023, 10, 11), 5.0, TransportationType.BUS);
        testExpenses.add(foodExpense1);
        testExpenses.add(foodExpense2);
        testExpenses.add(transportExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", "Food",
                "Burger", null, mockUi);
        findCommand.execute();

        String expectedOutput = "[Food Expense (LUNCH): Burger | Date: 10/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testFindExpenseByDateAndCategory() throws Exception {
        Transport transportExpense1 = new Transport("Bus",
                LocalDate.of(2023, 10, 11), 5.0, TransportationType.BUS);
        Utilities utilitiesExpense = new Utilities("Electricity",
                LocalDate.of(2023, 10, 11), 5.0, UtilityType.WATER);
        Transport transportExpense2 = new Transport("Bus",
                LocalDate.of(2023, 10, 11), 5.0, TransportationType.TAXI);
        testExpenses.add(transportExpense1);
        testExpenses.add(utilitiesExpense);
        testExpenses.add(transportExpense2);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", "Transport",
                null, "11/Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Transportation Expense (BUS): Bus | Date: 11/Oct/2023 | Amount: $5.00, "
                + "Transportation Expense (TAXI): Bus | Date: 11/Oct/2023 | Amount: $5.00]";
        assertTrue(mockUi.getShowExpenses().contains(expectedOutput));
    }

    @Test
    void testFindExpenseByDescriptionAndDateAndCategory() throws Exception {
        Utilities utilitiesExpense1 = new Utilities("Electricity",
                LocalDate.of(2023, 10, 11), 5.0, UtilityType.ELECTRICITY);
        Utilities utilitiesExpense2 = new Utilities("Water",
                LocalDate.of(2023, 10, 11), 5.0, UtilityType.WATER);
        Food foodExpense = new Food("Burger",
                LocalDate.of(2023, 10, 11), 5.0, MealType.LUNCH);
        testExpenses.add(utilitiesExpense1);
        testExpenses.add(utilitiesExpense2);
        testExpenses.add(foodExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "expense", "Utilities",
                "Electricity", "11/Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Utilities Expense(ELECTRICITY): Electricity | Date: 11/Oct/2023 | Amount: $5.00]";
        assertEquals(expectedOutput, mockUi.getShowExpenses().get(0));
    }

    @Test
    void testIncomeByDescriptionAndDate() throws Exception {
        Income income1 = new Income("Salary", LocalDate.of(2023, 10, 10), 5000.0);
        Income income2 = new Income("Salary", LocalDate.of(2023, 10, 11), 5000.0);
        testIncomes.add(income1);
        testIncomes.add(income2);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses, "income", null,
                "Salary", "10/Oct/2023", mockUi);
        findCommand.execute();

        String expectedOutput = "[Income: Salary | Date: 10/Oct/2023 | Amount: $5000.00]";
        assertEquals(expectedOutput, mockUi.getShowIncomes().get(0));
    }

    @Test
    void testNegativeIncomeAmount() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            Income negativeIncome = new Income("Salary", LocalDate.of(2023, 10, 10), -5000.0);
            testIncomes.add(negativeIncome);
        });

        assertTrue(exception.getMessage().contains("Amount cannot be negative!"));
    }

    @Test
    void testNegativeExpenseAmount() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            Food negativeExpense = new Food("Burger",
                    LocalDate.of(2023, 10, 10), -5.0, MealType.LUNCH);
            testExpenses.add(negativeExpense);
        });

        assertTrue(exception.getMessage().contains("Amount cannot be negative!"));
    }

    @Test
    void testEmptyIncomeDescription() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            Income emptyDescriptionIncome = new Income("",
                    LocalDate.of(2023, 10, 10), 5000.0);
            testIncomes.add(emptyDescriptionIncome);
        });

        assertTrue(exception.getMessage().contains("Description cannot be empty!"));
    }

    @Test
    void testEmptyExpenseDescription() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            Food emptyDescriptionExpense = new Food("",
                    LocalDate.of(2023, 10, 10), 5.0, MealType.LUNCH);
            testExpenses.add(emptyDescriptionExpense);
        });

        assertTrue(exception.getMessage().contains("Description cannot be empty!"));
    }

    @Test
    void testInvalidExpenseCategory() {
        Exception exception = assertThrows(KaChinnnngException.class, () -> {
            // Assuming 'Gaming' is not a valid category in your application
            FindCommand findCommand = new FindCommand(testIncomes, testExpenses,
                    "expense", "Gaming", null, null, mockUi);
            findCommand.execute();
        });

        assertTrue(exception.getMessage().contains("Invalid expense category provided! Allowed categories are: "));
    }


    @Test
    void testFindCommandWithZeroAmountExpense() throws Exception {
        Expense zeroAmountExpense = new Expense("Zero Amount Expense", LocalDate.now(), 0.0);
        testExpenses.add(zeroAmountExpense);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses,
                "expense", "Food", null, null, mockUi);
        findCommand.execute();

        assertFalse(mockUi.getShowExpenses().contains("Zero Amount Expense"));
    }

    @Test
    void testFindCommandWithZeroIncome() throws Exception {
        Income zeroAmountIncome = new Income("Zero Amount Income", LocalDate.now(), 0.0);
        testIncomes.add(zeroAmountIncome);

        FindCommand findCommand = new FindCommand(testIncomes, testExpenses,
                "income", null, null, null, mockUi);
        findCommand.execute();

        assertFalse(mockUi.getShowIncomes().contains("Zero Amount Income"));
    }

}
