package cashleh.parser;

import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;
import cashleh.commands.*;
import cashleh.transaction.*;
import cashleh.transaction.ExpenseCategories.ExpenseCategory;
import cashleh.transaction.IncomeCategories.IncomeCategory;
import cashleh.exceptions.CashLehParsingException;

import java.time.LocalDate;
import java.util.HashMap;

public class Parser {
    private static final String ADD_INCOME = "addIncome";
    private static final String DELETE_INCOME = "deleteIncome";
    private static final String VIEW_INCOMES = "viewIncomes";

    private static final String ADD_EXPENSE = "addExpense";
    private static final String DELETE_EXPENSE = "deleteExpense";
    private static final String VIEW_EXPENSES = "viewExpenses";

    private static final String UPDATE_BUDGET = "updateBudget";
    private static final String DELETE_BUDGET = "deleteBudget";
    private static final String VIEW_BUDGET = "viewBudget";
    private static final String VIEW_FINANCIAL_STATEMENT = "viewFinancialStatement";
    private static final String EXIT = "exit";
    private static final String FILTER_EXPENSE = "filterExpense";
    private static final String FILTER_INCOME = "filterIncome";
    private static final String FILTER = "filter";



    private final ExpenseStatement expenseStatement;
    private final IncomeStatement incomeStatement;
    private final BudgetHandler budgetHandler;

    public Parser(ExpenseStatement expenseStatement, IncomeStatement incomeStatement, BudgetHandler budgetHandler) {
        this.expenseStatement = expenseStatement;
        this.incomeStatement = incomeStatement;
        this.budgetHandler = budgetHandler;
    }

    public Command parse(String input) throws CashLehParsingException {
        String command = input.contains(" ") ? input.split(" ")[0] : input;
        switch (command) {
        case ADD_INCOME:
            Income income = getIncome(input);
            return new AddIncome(income, incomeStatement);
        case DELETE_INCOME:
            return getDeleteTransaction(input, DELETE_INCOME);
        case VIEW_INCOMES:
            return new ViewIncomes(incomeStatement);
        case ADD_EXPENSE:
            Expense expense = getExpense(input);
            return new AddExpense(expense, expenseStatement);
        case DELETE_EXPENSE:
            return getDeleteTransaction(input, DELETE_EXPENSE);
        case VIEW_EXPENSES:
            return new ViewExpenses(expenseStatement);
        case UPDATE_BUDGET:
            Budget budget = getBudget(input);
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
            FindParser expenseToFind = filterDetails(FILTER_EXPENSE, input);
            return new FilterExpense(expenseToFind, expenseStatement);
        case FILTER_INCOME:
            FindParser incomeToFind = filterDetails(FILTER_INCOME, input);
            return new FilterIncome(incomeToFind, incomeStatement);
        case FILTER:
            FindParser transactionToFind = filterDetails(FILTER, input);
            return new FilterTransaction(transactionToFind, expenseStatement, incomeStatement);
        default:
            throw new CashLehParsingException("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!");
        }
    }

    private Expense getExpense(String input) throws CashLehParsingException {
        String[] format = {ADD_EXPENSE, "/amt", "/date:optional", "/cat:optional"};
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String expenseName = inputDetails.get(ADD_EXPENSE);
        String expenseAmtString = inputDetails.get("/amt");
        String expenseDateString = inputDetails.get("/date");
        String expenseCategoryString = inputDetails.get("/cat");

        if (expenseName.isEmpty()) {
            throw new CashLehParsingException(
                "Oopsie! An expense without a description is like a CashLeh transaction without its story - not as fun!"
            );
        }

        double expenseAmt;
        try {
            expenseAmt = Double.parseDouble(expenseAmtString);
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
        }
        else if (parsedDate == null) {
            return new Expense(expenseName, expenseAmt, parsedCategory);
        }
        else if (parsedCategory == null) {
            return new Expense(expenseName, expenseAmt, parsedDate);
        }
        return new Expense(expenseName, expenseAmt, parsedDate, parsedCategory);
    }

    private Income getIncome(String input) throws CashLehParsingException {
        String[] format = {ADD_INCOME, "/amt", "/date:optional", "/cat:optional"};
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String incomeName = inputDetails.get(ADD_INCOME);
        String incomeAmtString = inputDetails.get("/amt");
        String incomeDateString = inputDetails.get("/date");
        String incomeCategoryString = inputDetails.get("/cat");

        if (incomeName.isEmpty()) {
            throw new CashLehParsingException(
                    "Oopsie! An income without a description is like a CashLeh transaction without " +
                            "its story - not as fun!"
            );
        }
        double incomeAmt;
        try {
            incomeAmt = Double.parseDouble(incomeAmtString);
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Please enter a valid expense amount!");
        }

        LocalDate parsedDate = null;
        if (!(incomeDateString == null) && !incomeDateString.isEmpty()) {
            parsedDate = DateParser.parse(incomeDateString);
        }
        
        IncomeCategory parsedCategory = null;
        if (!(incomeCategoryString == null) && !incomeCategoryString.isEmpty()) {
            parsedCategory = IncomeCatParser.parse(incomeCategoryString);
        }

        if (parsedDate == null && parsedCategory == null) {
            return new Income(incomeName, incomeAmt);
        }
        else if (parsedDate == null) {
            return new Income(incomeName, incomeAmt, parsedCategory);
        }
        else if (parsedCategory == null) {
            return new Income(incomeName, incomeAmt, parsedDate);
        }
        return new Income(incomeName, incomeAmt, parsedDate, parsedCategory);
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

    private FindParser filterDetails (String transactionType, String input) throws CashLehParsingException {
        String[] format = null;
        switch (transactionType) {
        case FILTER_EXPENSE:
            format = new String[]{FILTER_EXPENSE, "/amt:optional"};
            break;
        case FILTER_INCOME:
            format = new String[]{FILTER_INCOME, "/amt:optional"};
            break;
        case FILTER:
            format = new String[]{FILTER, "/amt:optional"};
            break;
        default:
            throw new CashLehParsingException("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!");
        }
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String descriptionString = inputDetails.get(transactionType);
        String amountString = inputDetails.get("/amt");
        if (amountString != null) {
            double amount;
            try {
                amount = Double.parseDouble(amountString);
                return new FindParser(descriptionString, amount);
            } catch (NumberFormatException e) {
                throw new CashLehParsingException("Please enter a valid expense amount!");
            }
        }
        return new FindParser(descriptionString, -1.0);
    }

    private Budget getBudget(String input) throws CashLehParsingException {
        String newBudget = input.split(" ", 2)[1];
        int newBudgetAmount;
        try {
            newBudgetAmount = Integer.parseInt(newBudget);
        } catch (NumberFormatException e) {
            throw new CashLehParsingException("Eh, that's not the kind of number we flaunt in CashLeh!");
        }
        return new Budget(newBudgetAmount);
    }
}
