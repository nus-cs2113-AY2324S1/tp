package cashleh;

import Exceptions.CashLehException;
import Exceptions.CashLehParsingException;
import cashleh.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String ADD_INCOME = "addIncome";
    private static final String DELETE_INCOME = "deleteIncome";
    private static final String VIEW_INCOMES = "viewIncomes";
    private static final String ADD_EXPENSE = "addExpense";
    private static final String DELETE_EXPENSE = "deleteExpense";
    private static final String VIEW_EXPENSES = "viewExpenses";
    private static final String EXIT = "exit";

    public Command parse(String input) throws CashLehException {
        String command = input.contains(" ") ? input.split(" ")[0] : input;
        switch (command) {
//        case ADD_INCOME:
//            break;
//        case DELETE_INCOME:
//            break;
//        case VIEW_INCOMES:
//            break;
        case ADD_EXPENSE:
            Expense expense = getExpense(input);
            return new AddExpense(expense);
        case DELETE_EXPENSE:
            return getDeleteExpense(input);
        case VIEW_EXPENSES:
            return new ViewExpenses();
        case EXIT:
            return new Exit();
        default:
            throw new CashLehException("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!");
        }
    }

    private Expense getExpense(String input) throws CashLehParsingException {
        if (!input.contains("/amt")) {
            throw new CashLehParsingException("Please enter a proper amount using '/amt'.");
        }
        String[] expenseInfo = input.substring(ADD_EXPENSE.length() + 1).split("/amt", 2);
        String expenseName = expenseInfo[0].trim();
        String expenseAmtString = expenseInfo[1].trim();
        if (expenseName.isEmpty()) {
            throw new CashLehParsingException("Oopsie! An expense without a description is like a CashLeh transaction without its story - not as fun!");
        }
        if (expenseAmtString.isEmpty()) {
            throw new CashLehParsingException("Oopsie! An expense without the amount is like a wallet without cash, so not 'CashLeh'!");
        }

        double expenseAmt;
        try {
            expenseAmt = Double.parseDouble(expenseAmtString);
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Please enter a valid expense amount!");
        }
        return new Expense(expenseName, expenseAmt);
    }

    private Command getDeleteExpense(String input) throws CashLehParsingException {
        String trimmedInput = input.trim();
        if (trimmedInput.equals(DELETE_EXPENSE)) {
            throw new CashLehParsingException("Which expense kena the chop today? Make your choice!");
        }
        String expenseString = input.split(" ", 2)[1];
        int expenseIndex;
        try {
            expenseIndex = Integer.parseInt(expenseString);
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Eh, that's not the kind of number we flaunt in CashLeh!");
        }
        return new DeleteExpense(expenseIndex);
    }
}