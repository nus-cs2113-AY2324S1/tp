package cashleh.parser;

import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;

import cashleh.commands.AddExpense;
import cashleh.commands.AddIncome;
import cashleh.commands.Command;
import cashleh.commands.DeleteBudget;
import cashleh.commands.DeleteExpense;
import cashleh.commands.DeleteIncome;
import cashleh.commands.Exit;
import cashleh.commands.FilterExpense;
import cashleh.commands.FilterIncome;
import cashleh.commands.FilterTransaction;
import cashleh.commands.UpdateBudget;
import cashleh.commands.ViewBudget;
import cashleh.commands.ViewExpenses;
import cashleh.commands.ViewFinancialStatement;
import cashleh.commands.ViewIncomes;

import cashleh.exceptions.CashLehDateParsingException;
import cashleh.exceptions.CashLehParsingException;

import cashleh.transaction.Categories;
import cashleh.transaction.Expense;
import cashleh.transaction.ExpenseCategories.ExpenseCategory;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.Income;
import cashleh.transaction.IncomeCategories.IncomeCategory;
import cashleh.transaction.IncomeStatement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.OptionalDouble;

public class Parser {
    private static final String ADD_INCOME = "addincome";
    private static final String DELETE_INCOME = "deleteincome";
    private static final String VIEW_INCOMES = "viewincomes";

    private static final String ADD_EXPENSE = "addexpense";
    private static final String DELETE_EXPENSE = "deleteexpense";
    private static final String VIEW_EXPENSES = "viewexpenses";

    private static final String UPDATE_BUDGET = "updatebudget";
    private static final String DELETE_BUDGET = "deletebudget";
    private static final String VIEW_BUDGET = "viewbudget";
    private static final String VIEW_FINANCIAL_STATEMENT = "viewfinancialstatement";
    private static final String EXIT = "exit";
    private static final String FILTER_EXPENSE = "filterexpense";
    private static final String FILTER_INCOME = "filterincome";
    private static final String FILTER = "filter";
    private static final String AMT_KEYWORD = "/amt";
    private static final String DATE_KEYWORD = "/date";
    private static final String CAT_KEYWORD = "/cat";
    private static final double MAX_AMT = 9999999.99;

    private final ExpenseStatement expenseStatement;
    private final IncomeStatement incomeStatement;
    private final BudgetHandler budgetHandler;

    public Parser(ExpenseStatement expenseStatement, IncomeStatement incomeStatement, BudgetHandler budgetHandler) {
        this.expenseStatement = expenseStatement;
        this.incomeStatement = incomeStatement;
        this.budgetHandler = budgetHandler;
    }

    public Command parse(String input) throws CashLehParsingException {
        String command = input.contains(" ") ? input.trim().split(" ")[0] : input.trim();

        // Ensure commands are case-insensitive
        String modifiedInput = input.replaceFirst(command, command.toLowerCase()).trim();
        switch (command.toLowerCase()) {
        case ADD_INCOME:
            Income income = getIncome(modifiedInput);
            return new AddIncome(income, incomeStatement);
        case DELETE_INCOME:
            return getDeleteTransaction(modifiedInput, DELETE_INCOME);
        case VIEW_INCOMES:
            return new ViewIncomes(incomeStatement);
        case ADD_EXPENSE:
            Expense expense = getExpense(modifiedInput);
            return new AddExpense(expense, expenseStatement);
        case DELETE_EXPENSE:
            return getDeleteTransaction(modifiedInput, DELETE_EXPENSE);
        case VIEW_EXPENSES:
            return new ViewExpenses(expenseStatement);
        case UPDATE_BUDGET:
            Budget budget = getBudget(modifiedInput);
            return new UpdateBudget(budget, budgetHandler);
        case DELETE_BUDGET:
            return new DeleteBudget(budgetHandler);
        case VIEW_BUDGET:
            return new ViewBudget(budgetHandler);
        case VIEW_FINANCIAL_STATEMENT:
            return new ViewFinancialStatement(incomeStatement, expenseStatement);
        case EXIT:
            return new Exit();
        case FILTER_EXPENSE:
            FindParser expenseToFind = filterBy(FILTER_EXPENSE, modifiedInput);
            return new FilterExpense(expenseToFind, expenseStatement);
        case FILTER_INCOME:
            FindParser incomeToFind = filterBy(FILTER_INCOME, modifiedInput);
            return new FilterIncome(incomeToFind, incomeStatement);
        case FILTER:
            FindParser transactionToFind = filterBy(FILTER, modifiedInput);
            return new FilterTransaction(transactionToFind, expenseStatement, incomeStatement);
        default:
            throw new CashLehParsingException("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!");
        }
    }

    private Expense getExpense(String input) throws CashLehParsingException {
        String[] format = {ADD_EXPENSE, AMT_KEYWORD, DATE_KEYWORD+":optional", CAT_KEYWORD+":optional"};
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String expenseName = inputDetails.get(ADD_EXPENSE);
        assert expenseName != null;

        String expenseAmtString = inputDetails.get(AMT_KEYWORD);
        String expenseDateString = inputDetails.get(DATE_KEYWORD);
        String expenseCategoryString = inputDetails.get(CAT_KEYWORD);
        

        if (expenseName.isEmpty()) {
            throw new CashLehParsingException(
                "Oopsie! An expense without a description is like a CashLeh transaction without its story - not as fun!"
            );
        }

        double expenseAmt;
        try {
            expenseAmt = Double.parseDouble(expenseAmtString);
            if (expenseAmt < 0) {
                throw new CashLehParsingException("Please enter a positive amount!");
            }
            if (expenseAmt > MAX_AMT) {
                throw new CashLehParsingException("Amount entered is too large! " +
                    "Please split up your transaction into smaller ones " +
                    "with maximum amount per transaction at $9999999");
            }
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Please enter a valid expense amount!");
        }

        LocalDate parsedDate = null;
        if (!(expenseDateString == null || expenseDateString.isEmpty())) {
            parsedDate = DateParser.parse(expenseDateString);
        }
        
        ExpenseCategory parsedCategory = null;
        if (!(expenseCategoryString == null || expenseCategoryString.isEmpty())) {
            parsedCategory = ExpenseCatParser.parse(expenseCategoryString);
        }

        if (parsedDate == null && parsedCategory == null) {
            return new Expense(expenseName, expenseAmt);
        } else if (parsedDate == null) {
            return new Expense(expenseName, expenseAmt, parsedCategory);
        } else if (parsedCategory == null) {
            return new Expense(expenseName, expenseAmt, parsedDate);
        } else {
            return new Expense(expenseName, expenseAmt, parsedDate, parsedCategory);
        }
    }

    private Income getIncome(String input) throws CashLehParsingException {
        String[] format = {ADD_INCOME, AMT_KEYWORD, DATE_KEYWORD+":optional", CAT_KEYWORD+":optional"};
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String incomeName = inputDetails.get(ADD_INCOME);
        assert incomeName != null;
        
        String incomeAmtString = inputDetails.get(AMT_KEYWORD);
        String incomeDateString = inputDetails.get(DATE_KEYWORD);
        String incomeCategoryString = inputDetails.get(CAT_KEYWORD);

        if (incomeName.isEmpty()) {
            throw new CashLehParsingException(
                    "Oopsie! An income without a description is like a CashLeh transaction without " +
                            "its story - not as fun!"
            );
        }
        double incomeAmt;
        try {
            incomeAmt = Double.parseDouble(incomeAmtString);
            if (incomeAmt < 0) {
                throw new CashLehParsingException("Please enter a positive amount!");
            }
            if (incomeAmt > MAX_AMT) {
                throw new CashLehParsingException("Amount entered is too large! " +
                    "Please split up your transaction into smaller ones " +
                    "with maximum amount per transaction at $9999999");
            }
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Please enter a valid income amount!");
        }

        LocalDate parsedDate = null;
        if (incomeDateString != null && !incomeDateString.isEmpty()) {
            parsedDate = DateParser.parse(incomeDateString);
        }
        
        IncomeCategory parsedCategory = null;
        if (incomeCategoryString != null && !incomeCategoryString.isEmpty()) {
            parsedCategory = IncomeCatParser.parse(incomeCategoryString);
        }

        if (parsedDate == null && parsedCategory == null) {
            return new Income(incomeName, incomeAmt);
        } else if (parsedDate == null) {
            return new Income(incomeName, incomeAmt, parsedCategory);
        } else if (parsedCategory == null) {
            return new Income(incomeName, incomeAmt, parsedDate);
        } else {
            return new Income(incomeName, incomeAmt, parsedDate, parsedCategory);
        }
    }

    private Command getDeleteTransaction(String input, String transactionType) throws CashLehParsingException {
        String trimmedInput = input.trim();
        if (trimmedInput.equals(transactionType)) {
            throw new CashLehParsingException("Which transaction kena the chop today? Make your choice!");
        }
        String transactionString = input.split(" ", 2)[1];
        int transactionIndex;
        try {
            transactionIndex = Integer.parseInt(transactionString);
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Eh, that's not the kind of number we flaunt in CashLeh!");
        }
        return transactionType.equals(DELETE_EXPENSE) ?
            new DeleteExpense(transactionIndex, expenseStatement) : new DeleteIncome(transactionIndex, incomeStatement);
    }

    /**
     * Parses and extracts filtering criterion based on the specified transaction type and input string.
     * @param transactionType The type of transaction to filter (FILTER_EXPENSE, FILTER_INCOME, or FILTER).
     * @param input The input string to parse and filter.
     * @return A {@link FindParser} containing the parsed and filtered transaction details.
     * @throws CashLehParsingException If the input or parsing fails.
     */
    private FindParser filterBy (String transactionType, String input) throws CashLehParsingException {
        String[] format = null;
        switch (transactionType) {
        case FILTER_EXPENSE:
            format = new String[]{FILTER_EXPENSE, AMT_KEYWORD+":optional",
                DATE_KEYWORD+":optional", CAT_KEYWORD+":optional"};
            break;
        case FILTER_INCOME:
            format = new String[]{FILTER_INCOME, AMT_KEYWORD+":optional",
                DATE_KEYWORD+":optional", CAT_KEYWORD+":optional"};
            break;
        case FILTER:
            format = new String[]{FILTER, AMT_KEYWORD+":optional", DATE_KEYWORD+":optional", CAT_KEYWORD+":optional"};
            break;
        default:
            throw new CashLehParsingException("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!");
        }
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);

        String descriptionString = inputDetails.get(transactionType);
        // Create expression patterns for "/amt", "/cat", and "/date"
        String amtFilterPattern = "(?i)(/amt|amt/|a/mt|am/t)";
        String catFilterPattern = "(?i)(/cat|cat/|c/at|/ca/t)";
        String dateFilterPattern = "(?i)(/date|date/|d/ate|da/te|dat/e)";
        // Remove any instance of /amt, /cat and /date from description
        descriptionString = descriptionString.replaceAll(amtFilterPattern, "");
        descriptionString = descriptionString.replaceAll(catFilterPattern, "");
        descriptionString = descriptionString.replaceAll(dateFilterPattern, "");
        // Remove special characters and punctuations from description
        descriptionString = descriptionString.replaceAll("[^a-zA-Z0-9\\s]", "");
        descriptionString = descriptionString.trim();

        String amountString = inputDetails.get(AMT_KEYWORD);
        String dateString = inputDetails.get(DATE_KEYWORD);
        String categoryString = inputDetails.get(CAT_KEYWORD);

        if ((descriptionString == null || descriptionString.isEmpty()) &&
                (amountString == null || amountString.isEmpty()) &&
                (dateString == null || dateString.isEmpty()) &&
                (categoryString == null || categoryString.isEmpty())){
            throw new CashLehParsingException("Please provide at least one filter criterion " +
                    "(description, amount, date, or category)!");
        }

        OptionalDouble parsedAmount = OptionalDouble.empty();
        if ((amountString != null) && !amountString.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountString);
                if (amount > MAX_AMT) {
                    throw new CashLehParsingException("Amount entered is too large! Please enter a different amount.");
                }
                parsedAmount = OptionalDouble.of(amount);
            } catch (NumberFormatException e) {
                throw new CashLehParsingException("Please enter a valid expense amount!");
            }
        }
        LocalDate parsedDate = null;
        if ((dateString != null) && !dateString.isEmpty()) {
            try {
                parsedDate = DateParser.parse(dateString);
            } catch (CashLehDateParsingException e) {
                throw new CashLehDateParsingException();
            }
        }
        Categories parsedCategory = parseCategory(transactionType, categoryString);

        return new FindParser(descriptionString, parsedAmount, parsedDate, parsedCategory);
    }

    /**
     * Parses the category string based on the transaction type.
     * @param transactionType The type of transaction (FILTER_EXPENSE, FILTER_INCOME, FILTER).
     * @param categoryString  The category string to be parsed.
     * @return The parsed category, or null if the category string is empty or not provided.
     */
    private Categories parseCategory(String transactionType, String categoryString) {
        if (categoryString != null && !categoryString.isEmpty()) {
            if (transactionType.equals(FILTER_EXPENSE)) {
                return ExpenseCatParser.parse(categoryString);
            } else if (transactionType.equals(FILTER_INCOME)) {
                return IncomeCatParser.parse(categoryString);
            } else {
                // Try to parse as ExpenseCategory first.
                Categories parsedCategory = ExpenseCatParser.parse(categoryString);
                // Even if category was meant to be passed in as an incomeCategory,
                // it will have a result of OTHERS after being parsed via ExpenseCatParser,
                // thus parse once more using IncomeCatParser
                boolean checkCategory = parsedCategory.equals(ExpenseCategory.valueOf("OTHERS"));
                if (checkCategory) {
                    // Try to parse as IncomeCategory.
                    return IncomeCatParser.parse(categoryString);
                }
                return parsedCategory;
            }
        } else {
            return null;
        }
    }

    private Budget getBudget(String input) throws CashLehParsingException {
        String trimmedInput = input.trim();
        if (trimmedInput.equals(UPDATE_BUDGET)) {
            throw new CashLehParsingException("Ayo, type in your budget amount hor!");
        }
        String newBudget = input.split(" ", 2)[1];
        double newBudgetAmount;
        try {
            newBudgetAmount = Double.parseDouble(newBudget);
            if (newBudgetAmount < 0) {
                throw new CashLehParsingException("Please enter a positive budget amount!");
            }
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Eh, that's not the kind of number we flaunt in CashLeh!");
        }
        return new Budget(newBudgetAmount);
    }
}
