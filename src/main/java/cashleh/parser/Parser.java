package cashleh.parser;

import cashleh.budget.Budget;
import cashleh.budget.BudgetHandler;
import cashleh.commands.AddExpense;
import cashleh.commands.AddIncome;
import cashleh.commands.DeleteBudget;
import cashleh.commands.DeleteIncome;
import cashleh.commands.DeleteExpense;
import cashleh.commands.Exit;
import cashleh.commands.UpdateBudget;
import cashleh.commands.ViewExpenses;
import cashleh.commands.ViewBudget;
import cashleh.commands.ViewIncomes;
import cashleh.commands.ViewFinancialStatement;
import cashleh.commands.Command;
import cashleh.transaction.Expense;
import cashleh.transaction.ExpenseCategories.ExpenseCategory;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.Income;
import cashleh.transaction.IncomeCategories.IncomeCategory;

import cashleh.exceptions.CashLehParsingException;
import cashleh.transaction.IncomeStatement;

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

    private static final String AMT_KEYWORD = "/amt";
    private static final String DATE_KEYWORD = "/date";
    private static final String CAT_KEYWORD = "/cat";

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
        default:
            throw new CashLehParsingException("Aiyoh! Your input blur like sotong... Clean your input for CashLeh!");
        }
    }

    private Expense getExpense(String input) throws CashLehParsingException {
        String[] format = {ADD_EXPENSE, "/amt", "/date:optional", "/cat:optional"};
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String expenseName = inputDetails.get(ADD_EXPENSE);
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
        String[] format = {ADD_INCOME, "/amt", "/date:optional", "/cat:optional"};
        HashMap<String, String> inputDetails = StringTokenizer.tokenize(input, format);
        String incomeName = inputDetails.get(ADD_INCOME);
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

    private Budget getBudget(String input) throws CashLehParsingException {
        String trimmedInput = input.trim();
        if (trimmedInput.equals(UPDATE_BUDGET)) {
            throw new CashLehParsingException("Ayo, type in your budget amount hor!");
        }
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
