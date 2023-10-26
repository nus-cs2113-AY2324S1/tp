package cashleh;

import cashleh.exceptions.CashLehReadFromFileException;
import cashleh.exceptions.CashLehWriteToFileException;
import cashleh.exceptions.CashLehFileCorruptedException;
import cashleh.exceptions.CashLehParsingException;
import cashleh.exceptions.CashLehDateParsingException;
import cashleh.parser.DateParser;
import cashleh.parser.ExpenseCatParser;
import cashleh.parser.IncomeCatParser;
import cashleh.parser.StringTokenizer;
import cashleh.transaction.Expense;
import cashleh.transaction.ExpenseCategories.ExpenseCategory;
import cashleh.transaction.ExpenseStatement;
import cashleh.transaction.Income;
import cashleh.transaction.IncomeCategories.IncomeCategory;
import cashleh.transaction.IncomeStatement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The `FileStorage` class is responsible for reading and writing financial transaction data to and from a file.
 * It provides methods for reading and writing income and expense data from and to a text file. The file format
 * is expected to be in a specific format, where each transaction is identified by a type (Income or Expense)
 * and contains details such as description, amount, date, and category (if applicable).
 *
 */
public class FileStorage {
    private static final String FILEPATH_PREFIX = "data/";
    private static final char INCOME_TYPE = 'I';
    private static final char EXPENSE_TYPE = 'E';
    private String filePath;

    /**
     * Constructor for a new 'FileStorage' object with the provided username, which is used to
     * generate the file path for reading and writing transaction data.
     *
     * @param userName The username of the user for whom the transaction data is stored.
     */
    public FileStorage(String userName) {
        filePath = FILEPATH_PREFIX + userName + ".txt";
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Reads income and expense data from a file and populates the provided IncomeStatement and ExpenseStatement
     * objects with the data. If the file does not exist, it creates an empty file with the filePath.
     *
     * @param incomeStatement The IncomeStatement object to populate with income data.
     * @param expenseStatement The ExpenseStatement object to populate with expense data.
     * @throws CashLehReadFromFileException If there is an error while reading from the file.
     * @throws CashLehFileCorruptedException If the file format is corrupted or invalid.
     */
    public void readFromFile(IncomeStatement incomeStatement, ExpenseStatement expenseStatement)
            throws CashLehReadFromFileException, CashLehFileCorruptedException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new CashLehReadFromFileException();
            }
        } else {
            getDataFromFile(file, incomeStatement, expenseStatement);
        }
    }

    private void getDataFromFile(File file, IncomeStatement incomeStatement, ExpenseStatement expenseStatement)
            throws CashLehReadFromFileException, CashLehFileCorruptedException {
        try {
            Scanner in = new Scanner(file);
            while (in.hasNext()) {
                String input = in.nextLine();
                char transactionType = input.charAt(1);

                String[] transaction = input.split("] ", 2);
                String transactionInfo;
                if (transaction.length < 2) {
                    throw new CashLehFileCorruptedException(
                        "Wah, jialat! Cannot load your transactions. Check if the file format spoilt!");
                } else {
                    transactionInfo = transaction[1];
                }

                switch (transactionType) {
                case INCOME_TYPE:
                    Income income = getIncome(transactionInfo);
                    incomeStatement.addIncome(income);
                    break;
                case EXPENSE_TYPE:
                    Expense expense = getExpense(transactionInfo);
                    expenseStatement.addExpense(expense);
                    break;
                default:
                    throw new CashLehFileCorruptedException(
                        "Wah, jialat! File glitches made the transaction type go MIA.");
                }
            }

        } catch (FileNotFoundException e) {
            throw new CashLehReadFromFileException();
        }
    }

    /**
     * Writes income and expense data to a file based on the provided IncomeStatement and ExpenseStatement
     * objects. The data is written in a specific format to the file.
     *
     * @param incomeStatement The IncomeStatement object containing income data to be written.
     * @param expenseStatement The ExpenseStatement object containing expense data to be written.
     * @throws CashLehWriteToFileException If there is an error while writing to the file.
     */
    public void writeToFile(IncomeStatement incomeStatement, ExpenseStatement expenseStatement)
            throws CashLehWriteToFileException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < incomeStatement.getSize(); i++) {
                Income income = incomeStatement.getIncome(i);
                String incomeText = income.toString().trim();
                fw.write("[I] " + incomeText + System.lineSeparator());
            }

            for (int i = 0; i < expenseStatement.getSize(); i++) {
                Expense expense = expenseStatement.getExpense(i);
                String expenseText = expense.toString().trim();
                fw.write("[E] " + expenseText + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new CashLehWriteToFileException();
        }
    }

    private Income getIncome(String input) throws CashLehFileCorruptedException {
        // Define the prefixes to extract income information
        String[] incomePrefixes = {"Income: ", "(Amount: ", ", Date: ", ", Category: :optional"};

        // Tokenize the input to get a map of income details
        HashMap<String, String> incomeDetails = null;
        try {
            incomeDetails = StringTokenizer.tokenize(input, incomePrefixes);
        } catch (CashLehParsingException e) {
            throw new CashLehFileCorruptedException(
                "Wah, jialat! One or more necessary fields missing! Check your file again!");
        }

        // Extract individual details
        String incomeDescription = incomeDetails.get("Income: ").trim();
        String incomeAmountString = incomeDetails.get("(Amount: ").trim();
        String incomeDateString = incomeDetails.get(", Date: ").trim();
        String incomeCategoryString = incomeDetails.get(", Category: ");

        incomeDateString = incomeCategoryString == null ?
            incomeDateString.replace(")", "") : incomeDateString;
        incomeCategoryString = incomeCategoryString == null ?
            incomeCategoryString : incomeCategoryString.replace(")", "");

        if (incomeDescription.isEmpty()) {
            throw new CashLehFileCorruptedException("Wah, jialat! The income description is gone!");
        }

        double incomeAmount;
        // Parse income
        try {
            incomeAmount = Double.parseDouble(incomeAmountString);
        } catch (NumberFormatException e) {
            throw new CashLehFileCorruptedException("Wah, jialat! The income amount is all messed up!");
        }

        // Parse the date (if available)
        LocalDate parsedDate = null;
        if (incomeDateString != null && !incomeDateString.isEmpty()) {
            try {
                parsedDate = DateParser.parse(incomeDateString);
            } catch (CashLehDateParsingException e) {
                throw new CashLehFileCorruptedException("Wah, jialat! The date of transaction is all messed up!");
            }
        }

        IncomeCategory parsedCategory = null;
        if (!(incomeCategoryString == null) && !incomeCategoryString.isEmpty()) {
            parsedCategory = IncomeCatParser.parse(incomeCategoryString);
        }
        if (parsedDate == null && parsedCategory == null) {
            return new Income(incomeDescription, incomeAmount);
        } else if (parsedDate == null) {
            return new Income(incomeDescription, incomeAmount, parsedCategory);
        } else if (parsedCategory == null) {
            return new Income(incomeDescription, incomeAmount, parsedDate);
        } else {
            return new Income(incomeDescription, incomeAmount, parsedDate, parsedCategory);
        }
    }

    private Expense getExpense(String input) throws CashLehFileCorruptedException {
        // Define the prefixes to extract expense information
        String[] expensePrefixes = {"Expense: ", "(Amount: ", ", Date: ", ", Category: :optional"};

        // Tokenize the input to get a map of expense details
        HashMap<String, String> expenseDetails = null;
        try {
            expenseDetails = StringTokenizer.tokenize(input, expensePrefixes);
        } catch (CashLehParsingException e) {
            throw new CashLehFileCorruptedException(
                "Wah, jialat! One or more necessary fields missing! Check your file again!");
        }

        // Extract individual details
        String expenseDescription = expenseDetails.get("Expense: ");
        String expenseAmountString = expenseDetails.get("(Amount: ");
        String expenseDateString = expenseDetails.get(", Date: ");
        String expenseCategoryString = expenseDetails.get(", Category: ");

        expenseDateString = expenseCategoryString == null ?
            expenseDateString.replace(")", "") : expenseDateString;
        expenseCategoryString = expenseCategoryString == null ?
            expenseCategoryString : expenseCategoryString.replace(")", "");

        if (expenseDescription.isEmpty()) {
            throw new CashLehFileCorruptedException("Wah, jialat! The income description is gone!");
        }

        // Parse expense amount
        double expenseAmount;
        try {
            expenseAmount = Double.parseDouble(expenseAmountString);
        } catch (NumberFormatException e) {
            throw new CashLehFileCorruptedException("Wah, jialat! The income amount is all messed up!");
        }

        // Parse the date (if available)
        LocalDate parsedDate = null;
        if (expenseDateString != null && !expenseDateString.isEmpty()) {
            try {
                parsedDate = DateParser.parse(expenseDateString);
            } catch (CashLehDateParsingException e) {
                throw new CashLehFileCorruptedException("Wah, jialat! The date of transaction is all messed up!");
            }
        }

        ExpenseCategory parsedCategory = null;
        if (!(expenseCategoryString == null) && !expenseCategoryString.isEmpty()) {
            parsedCategory = ExpenseCatParser.parse(expenseCategoryString);
        }

        if (parsedDate == null && parsedCategory == null) {
            return new Expense(expenseDescription, expenseAmount);
        } else if (parsedDate == null) {
            return new Expense(expenseDescription, expenseAmount, parsedCategory);
        } else if (parsedCategory == null) {
            return new Expense(expenseDescription, expenseAmount, parsedDate);
        } else {
            return new Expense(expenseDescription, expenseAmount, parsedDate, parsedCategory);
        }
    }


}
