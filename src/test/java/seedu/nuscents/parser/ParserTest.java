package seedu.nuscents.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.nuscents.commands.Command;
import seedu.nuscents.commands.InvalidCommand;
import seedu.nuscents.commands.ViewCommand;
import seedu.nuscents.commands.ExitCommand;
import seedu.nuscents.commands.ListCommand;
import seedu.nuscents.commands.DeleteCommand;
import seedu.nuscents.commands.FilterCommand;
import seedu.nuscents.commands.HelpCommand;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.data.exception.NuscentsException;
import seedu.nuscents.data.transaction.Allowance;
import seedu.nuscents.data.transaction.AllowanceCategory;
import seedu.nuscents.data.transaction.Expense;
import seedu.nuscents.data.transaction.ExpenseCategory;
import seedu.nuscents.data.transaction.Transaction;
import seedu.nuscents.ui.Messages;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nuscents.parser.Parser.parseAllowanceCategory;
import static seedu.nuscents.parser.Parser.parseExpenseCategory;

public class ParserTest {
    private TransactionList transactionList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
        transactionList.clearTransactionList();
        Date date = new Date(123, Calendar.NOVEMBER, 30);
        // Create a test transaction to be used in the tests
        Expense expense = new Expense(20.0f, date, "Lunch", "Pasta", ExpenseCategory.FOOD);
        transactionList.addTransaction(expense);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void parseCommand_invalidCommand_invalidCommandReturned() throws Exception {
        Command result = Parser.parseCommand("asdf");
        assertTrue(result instanceof InvalidCommand);
    }
    @Test
    public void parseCommand_exitCommand_success() throws Exception {
        Command result = Parser.parseCommand("exit");
        assertTrue(result instanceof ExitCommand);
    }

    @Test
    public void parseCommand_listCommand_success() throws Exception {
        Command result = Parser.parseCommand("list");
        assertTrue(result instanceof ListCommand);
    }

    @Test
    public void parseCommand_deleteCommand_success() throws Exception {
        String input = "delete 1";
        Command result = Parser.parseCommand(input);
        assertTrue(result instanceof DeleteCommand);
        assertEquals(1, Parser.parseTaskIndex("1"));
    }

    @Test
    public void parseCommand_viewCommand_success() throws Exception {
        String input = "view 1";
        Command result = Parser.parseCommand(input);
        assertTrue(result instanceof ViewCommand);
        assertEquals(1, Parser.parseTaskIndex("1"));
    }

    @Test
    public void parseCommand_filterCommand_success() throws Exception {
        String input = "filter food";
        Command result = Parser.parseCommand(input);
        assertTrue(result instanceof FilterCommand);
        assertEquals(ExpenseCategory.FOOD, Parser.parseFilterCategory("food"));
    }

    @Test
    public void parseAllowance_validInput_success() throws NuscentsException, ParseException {
        String arguments = "/amt 100.0 /date 2023-10-15 /desc Birthday Gift /note From friends";
        Allowance allowance = Parser.parseAllowance(arguments);
        assertEquals(100.0, allowance.getAmount());
        SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");
        String formattedDate = formatter.format(allowance.getDate());
        assertEquals("15-10-2023", formattedDate);
        assertEquals("Birthday Gift", allowance.getDescription());
        assertEquals("From friends", allowance.getAdditionalInfo());
    }

    @Test
    public void parseExpense_validInput_success() throws NuscentsException, ParseException {
        String arguments = "/amt 50.0 /date 16-10-2023 /desc Dinner /note Alone";
        Expense expense = Parser.parseExpense(arguments);
        assertEquals(50.0, expense.getAmount());
        SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");
        String formattedDate = formatter.format(expense.getDate());
        assertEquals("16-10-2023", formattedDate);
        assertEquals("Dinner", expense.getDescription());
        assertEquals("Alone", expense.getAdditionalInfo());
    }

    @Test
    public void parseAllowance_emptyArguments_exceptionThrown() {
        String input = "allowance";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals("OOPS!!! Invalid input format for adding an allowance.", exception.getMessage());
    }

    @Test
    public void parseAllowance_missingArguments_exceptionThrown() {
        String input = "allowance /amt 50.0 /desc Dinner";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals(Messages.MESSAGE_EMPTY_ALLOWANCE, exception.getMessage());
    }

    @Test
    public void parseAllowance_invalidAmount_exceptionThrown() {
        String input = "allowance /amt fifty /date 14-02-2023 /desc Gift";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals(Messages.MESSAGE_INVALID_AMOUNT, exception.getMessage());
    }

    @Test
    public void parseExpense_missingArguments_exceptionThrown() {
        String input = "expense /amt 50.0 /desc Dinner";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals("OOPS!!! Invalid input format for adding an expense.", exception.getMessage());
    }

    @Test
    public void parseExpense_emptyArguments_exceptionThrown() {
        String input = "expense";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals(Messages.MESSAGE_EMPTY_EXPENSE, exception.getMessage());
    }

    @Test
    public void parseExpense_invalidAmount_exceptionThrown() {
        String input = "expense /amt fifty /date 15-02-2023 /desc Dinner";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals(Messages.MESSAGE_INVALID_AMOUNT, exception.getMessage());
    }

    @Test
    public void parseAllowance_invalidDateTime_exceptionThrown() {
        String input = "allowance /amt 100.0 /date 15/10/2023 /desc Lunch";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals("OOPS!!! The format of the date is invalid.", exception.getMessage());
    }

    @Test
    public void parseExpense_invalidDateTime_exceptionThrown() {
        String input = "expense /amt 50.0 /date 16-13-2023 /desc Dinner";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand(input);
        });
        assertEquals("OOPS!!! The format of the date is invalid.", exception.getMessage());
    }

    @Test
    public void parseAllowanceCategory_validCategory_success() throws NuscentsException {
        assertEquals(AllowanceCategory.SALARY, parseAllowanceCategory("sAlARy"));
        assertEquals(AllowanceCategory.ALLOWANCE, parseAllowanceCategory("allOWANCE"));
        assertEquals(AllowanceCategory.INVESTMENTS, parseAllowanceCategory("INVESTMEnts"));
        assertEquals(AllowanceCategory.GIFTS, parseAllowanceCategory("GIFTS"));
        assertEquals(AllowanceCategory.OTHER_ALLOWANCE, parseAllowanceCategory("OthER_ALLOWANCE"));
        assertEquals(AllowanceCategory.NO_ALLOWANCE_CATEGORY, parseAllowanceCategory(""));
        assertEquals(AllowanceCategory.NO_ALLOWANCE_CATEGORY, parseAllowanceCategory("NO_ALLOWANCE_CATEGORY"));
    }

    @Test
    public void parseAllowanceCategory_invalidCategory_exceptionThrown() throws NuscentsException {
        String input = "asdfjkl";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseAllowanceCategory(input);
        });
        assertEquals(Messages.MESSAGE_UNKNOWN_ALLOWANCE_CATEGORY, exception.getMessage());
    }

    @Test
    public void parseExpenseCategory_validCategory_success() throws NuscentsException {
        assertEquals(ExpenseCategory.FOOD, parseExpenseCategory("FOOD"));
        assertEquals(ExpenseCategory.ENTERTAINMENT, parseExpenseCategory("ENtertainment"));
        assertEquals(ExpenseCategory.UTILITY, parseExpenseCategory("UTILity"));
        assertEquals(ExpenseCategory.RENT, parseExpenseCategory("RENT"));
        assertEquals(ExpenseCategory.TRANSPORTATION, parseExpenseCategory("transportation"));
        assertEquals(ExpenseCategory.OTHER_EXPENSE, parseExpenseCategory("OTher_EXPENSE"));
        assertEquals(ExpenseCategory.NO_EXPENSE_CATEGORY, parseExpenseCategory(""));
        assertEquals(ExpenseCategory.NO_EXPENSE_CATEGORY, parseExpenseCategory("no_expense_category"));
    }

    @Test
    public void parseExpenseCategory_invalidCategory_exceptionThrown() throws NuscentsException {
        String input = "huehue";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseExpenseCategory(input);
        });
        assertEquals(Messages.MESSAGE_UNKNOWN_EXPENSE_CATEGORY, exception.getMessage());
    }

    @Test
    public void parseTaskIndex_validInput_success() throws NuscentsException, IndexOutOfBoundsException {
        String input = "1";
        Transaction transaction = new Transaction("test description");
        assertEquals(1, Parser.parseTaskIndex(input));
    }

    @Test
    public void parseTaskIndex_outOfBoundsIndex_exceptionThrown() {
        String input = "-6";
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            Parser.parseTaskIndex(input);
        });
        assertEquals("OOPS!!! This is an invalid transaction index.", exception.getMessage());
    }

    @Test
    public void parseTaskIndex_invalidArgument_exceptionThrown() {
        String input = "InvalidArgument";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseTaskIndex(input);
        });
        assertEquals("OOPS!!! The input should consist only of digits.", exception.getMessage());
    }

    @Test
    public void parseTaskIndex_emptyArgument_exceptionThrown() {
        String input = null;
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseTaskIndex(input);
        });
        assertEquals("OOPS!!! The index of a delete/view/edit command cannot be empty.", exception.getMessage());
    }

    @Test
    public void parseTransactionCategory_validCategory_success() throws NuscentsException {
        assertEquals(ExpenseCategory.FOOD, parseExpenseCategory("FOOD  "));
        assertEquals(ExpenseCategory.ENTERTAINMENT, parseExpenseCategory("ENtertainment "));
        assertEquals(ExpenseCategory.UTILITY, parseExpenseCategory("UTILity"));
        assertEquals(ExpenseCategory.RENT, parseExpenseCategory("RENT "));
        assertEquals(ExpenseCategory.TRANSPORTATION, parseExpenseCategory("transportation "));
        assertEquals(ExpenseCategory.OTHER_EXPENSE, parseExpenseCategory("OTher_EXPENSE"));
        assertEquals(ExpenseCategory.NO_EXPENSE_CATEGORY, parseExpenseCategory(""));
        assertEquals(ExpenseCategory.NO_EXPENSE_CATEGORY, parseExpenseCategory("no_expense_category"));
        assertEquals(AllowanceCategory.SALARY, parseAllowanceCategory("sAlARy"));
        assertEquals(AllowanceCategory.ALLOWANCE, parseAllowanceCategory("allOWANCE"));
        assertEquals(AllowanceCategory.INVESTMENTS, parseAllowanceCategory("INVESTMEnts"));
        assertEquals(AllowanceCategory.GIFTS, parseAllowanceCategory("GIFTS"));
        assertEquals(AllowanceCategory.OTHER_ALLOWANCE, parseAllowanceCategory(" OthER_ALLOWANCE"));
        assertEquals(AllowanceCategory.NO_ALLOWANCE_CATEGORY, parseAllowanceCategory("  "));
        assertEquals(AllowanceCategory.NO_ALLOWANCE_CATEGORY, parseAllowanceCategory("  NO_ALLOWANCE_CATEGORY"));
    }

    @Test
    public void parseFilterCategory_invalidCategory_exceptionThrown() throws NuscentsException {
        String input = "asdfjkl";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseFilterCategory(input);
        });
        assertEquals(Messages.MESSAGE_UNKNOWN_FILTER_CATEGORY, exception.getMessage());
    }

    @Test
    public void parseEdit_emptyArguments_exceptionThrown() {
        String input = "edit 2";
        Exception exception = assertThrows(NuscentsException.class, () -> {
            Parser.parseEdit(input);
        });
        assertEquals(Messages.MESSAGE_EMPTY_EDIT, exception.getMessage());
    }



    @Test
    public void parseCommand_helpCommandWithCorrectInput_returnsHelpCommand() throws Exception {
        Command result = Parser.parseCommand("help");
        assertTrue(result instanceof HelpCommand);
    }

    @Test
    public void parseCommand_helpCommandWithIncorrectInput_throwsException() {
        Exception exceptionWithSpace = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand("help ");
        });
        assertEquals("OOPS!!! The correct format is 'help' alone.", exceptionWithSpace.getMessage());

        Exception exceptionWithExtraChars = assertThrows(NuscentsException.class, () -> {
            Parser.parseCommand("help extra");
        });
        assertEquals("OOPS!!! The correct format is 'help' alone.", exceptionWithExtraChars.getMessage());
    }
}
