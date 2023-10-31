package seedu.duke.ui;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;

import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import java.time.LocalDate;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UiTest {
    @Test
    public void readCommand_validInput_commandReturned() throws KaChinnnngException {
        // Set up simulated user input
        ByteArrayInputStream in = new ByteArrayInputStream("testCommand".getBytes());
        System.setIn(in);

        Ui ui = new Ui();

        // Act
        String command = ui.readCommand();

        // Assert
        assertEquals("testCommand", command);
    }

    @Test
    public void readCommand_noInput_exceptionThrown() {
        // Set up simulated user input with no data
        ByteArrayInputStream in = new ByteArrayInputStream("".getBytes());
        System.setIn(in);

        Ui ui = new Ui();

        // Act and Assert
        assertThrows(KaChinnnngException.class, ui::readCommand);
    }

    @Test
    public void printWelcomeMessage_validInput_messagePrinted() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui.printWelcomeMessage();

        // Assert
        assertEquals("____________________________________________________________"+ System.lineSeparator()
                + "Welcome to KaChinnnngggg! How may i assist you today?"+ System.lineSeparator()
                + "____________________________________________________________"+ System.lineSeparator(),
                out.toString());
    }

    @Test
    public void printGoodbyeMessage_validInput_messagePrinted() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        ui.printGoodbyeMessage();

        // Assert
        assertEquals("____________________________________________________________" + System.lineSeparator()
                + "Bye. Hope to see you again soon!" + System.lineSeparator()
                + "____________________________________________________________" + System.lineSeparator()
                , out.toString());
    }

    @Test
    public void showLineDivider_validInput_messagePrinted() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui.showLineDivider();

        // Assert
        assertEquals("____________________________________________________________"+ System.lineSeparator(),
                out.toString());
    }

    @Test
    public void printIncomeAddedMessage_validInput_messagePrinted() {
        // Create an actual Income object for the test
        Income testIncome = null;
        try {
            testIncome = new Income("Test Description", LocalDate.now(), 100.0);
        } catch (KaChinnnngException e) {
            e.printStackTrace();
        }

        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        assert testIncome != null;
        Ui.printIncomeAddedMessage(testIncome);

        // Assert
        assertEquals("____________________________________________________________" + System.lineSeparator()
                + "Got it. I've added this income:" + System.lineSeparator()
                + testIncome
                + System.lineSeparator() +
                "____________________________________________________________" +
                System.lineSeparator(), out.toString());
    }

    @Test
    public void printExpenseAddedMessage_validInput_messagePrinted() {
        // Create an actual Expense object for the test
        Expense testExpense = null;
        try {
            testExpense = new Expense("Test Description", LocalDate.now(), 100.0);
        } catch (KaChinnnngException e) {
            e.printStackTrace();
        }

        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        assert testExpense != null;
        Ui.printExpenseAddedMessage(testExpense);

        // Assert
        assertEquals("____________________________________________________________" + System.lineSeparator()
                + "Got it. I've added this expense:" + System.lineSeparator()
                + testExpense
                + System.lineSeparator() + "____________________________________________________________" +
                System.lineSeparator(), out.toString());
    }

    @Test
    public void printListIncomeMessage_validInput_messagePrinted() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        ui.printListIncomeMessage();

        // Assert
        assertEquals("Here are your incomes:" + System.lineSeparator(), out.toString());
    }

    @Test
    public void printListExpenseMessage_validInput_messagePrinted() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        ui.printListExpenseMessage();

        // Assert
        assertEquals("Here are your expenses:" + System.lineSeparator(), out.toString());
    }

    @Test
    public void showMatchingIncomes_nullTest() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        ui.showMatchingIncomes(null);

        // Assert
        assertEquals("No matching incomes found.", out.toString());
    }

    @Test
    public void showMatchingIncome_correctTest(){
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        Income income = null;
        List<Income> incomes = new ArrayList<>();
        try {
            income = new Income("Test Description", LocalDate.now(), 100.0);
            incomes.add(income);
        } catch (KaChinnnngException e) {
            e.printStackTrace();
        }
        ui.showMatchingIncomes(incomes);

        // Assert
        assertEquals("Here are the matching incomes in your list:" + System.lineSeparator()
                + income.toString() + System.lineSeparator(), out.toString());
    }

    @Test
    public void showMatchingExpenses_nullTest() {
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        ui.showMatchingExpenses(null);

        // Assert
        assertEquals("No matching expenses found.", out.toString());
    }

    @Test
    public void showMatchingExpenses_correctTest(){
        // Set up simulated output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Ui ui = new Ui();
        Expense expense = null;
        List<Expense> expenses = new ArrayList<>();
        try {
            expense = new Expense("Test Description", LocalDate.now(), 100.0);
            expenses.add(expense);
        } catch (KaChinnnngException e) {
            e.printStackTrace();
        }
        ui.showMatchingExpenses(expenses);

        // Assert
        assertEquals("Here are the matching expenses in your list:"
                + System.lineSeparator() + expense.toString() + System.lineSeparator(), out.toString());
    }
}
