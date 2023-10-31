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
    private static final String FILEPATH_POSTFIX = ".txt";
    private static final String TRANSACTION_DELIMITER = "] ";
    private static final String PARSE_TRANSACTION_DELIMITER = ")";
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
        filePath = FILEPATH_PREFIX + userName + FILEPATH_POSTFIX;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Reads income and expense data from a file and populates the provided IncomeStatement and ExpenseStatement
     * objects with the data. If the file does not exist, it creates an empty file with the filePath.
     *
     * @param incomeStatement  The IncomeStatement object to populate with income data.
     * @param expenseStatement The ExpenseStatement object to populate with expense data.
     * @throws CashLehReadFromFileException  If there is an error while reading from the file.
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

                String[] transaction = input.split(TRANSACTION_DELIMITER, 2);
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
     * @param incomeStatement  The IncomeStatement object containing income data to be written.
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
        String[] incomePrefixes = {"Income: ", "(Amount: ", ", Date: ", ", Category: :optional"};
        String[] incomeDetails = parseTransactionDetails(input, incomePrefixes);
        String description = incomeDetails[0];
        double amount = Double.parseDouble(incomeDetails[1]);
        String categoryString = incomeDetails[2];
        boolean dateNotProvided = incomeDetails.length == 3;

        try {
            if (categoryString == null && dateNotProvided) {
                return new Income(description, amount);
            } else if (dateNotProvided) {
                IncomeCategory category = IncomeCatParser.parse(categoryString);
                return new Income(description, amount, category);
            } else if (categoryString == null) {
                LocalDate date = DateParser.parse(incomeDetails[3]);
                return new Income(description, amount, date);
            } else {
                IncomeCategory category = IncomeCatParser.parse(categoryString);
                LocalDate date = DateParser.parse(incomeDetails[3]);
                return new Income(description, amount, date, category);
            }
        } catch (CashLehDateParsingException e) {
            throw new CashLehFileCorruptedException("Wah, jialat! The date of transaction is all messed up!");
        }

    }

    private Expense getExpense(String input) throws CashLehFileCorruptedException {
        String[] expensePrefixes = {"Expense: ", "(Amount: ", ", Date: ", ", Category: :optional"};
        String[] expenseDetails = parseTransactionDetails(input, expensePrefixes);
        String description = expenseDetails[0];
        double amount = Double.parseDouble(expenseDetails[1]);
        String categoryString = expenseDetails[2];

        try {
            if (categoryString == null && expenseDetails.length == 3) {
                return new Expense(description, amount);
            } else if (expenseDetails.length == 3) {
                ExpenseCategory category = ExpenseCatParser.parse(categoryString);
                return new Expense(description, amount, category);
            } else if (categoryString == null) {
                LocalDate date = DateParser.parse(expenseDetails[3]);
                return new Expense(description, amount, date);
            } else {
                ExpenseCategory category = ExpenseCatParser.parse(categoryString);
                LocalDate date = DateParser.parse(expenseDetails[3]);
                return new Expense(description, amount, date, category);
            }
        } catch (CashLehDateParsingException e) {
            throw new CashLehFileCorruptedException("Wah, jialat! The date of transaction is all messed up!");
        }
    }

    private String[] parseTransactionDetails(String input, String[] prefixes)
            throws CashLehFileCorruptedException {
        HashMap<String, String> transactionDetails = null;
        try {
            transactionDetails = StringTokenizer.tokenize(input, prefixes);
        } catch (CashLehParsingException e) {
            throw new CashLehFileCorruptedException(
                "Wah, jialat! One or more necessary fields are missing! Check your file again!");
        }

        String description = transactionDetails.get(prefixes[0]);
        String amountString = transactionDetails.get(prefixes[1]);
        String dateString = transactionDetails.get(prefixes[2]);
        String categoryString = transactionDetails.get(prefixes[3]);

        dateString = (categoryString == null) ? dateString.replace(PARSE_TRANSACTION_DELIMITER, "") : dateString;
        categoryString = (categoryString == null) ? categoryString
            : categoryString.replace(PARSE_TRANSACTION_DELIMITER, "");

        if (description.isEmpty()) {
            throw new CashLehFileCorruptedException("Wah, jialat! The description is missing!");
        }

        double amount;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            throw new CashLehFileCorruptedException("Wah, jialat! The amount is all messed up!");
        }

        LocalDate parsedDate = null;
        if (dateString != null && !dateString.isEmpty()) {
            try {
                parsedDate = DateParser.parse(dateString);
            } catch (CashLehDateParsingException e) {
                throw new CashLehFileCorruptedException("Wah, jialat! The date of transaction is all messed up!");
            }
        }

        if (parsedDate == null) {
            String[] transactionInfo = {description, String.valueOf(amount), categoryString};
            return transactionInfo;
        } else {
            String[] transactionInfo = {description, String.valueOf(amount),categoryString, String.valueOf(parsedDate)};
            return transactionInfo;
        }
    }
}
