package seedu.duke.storage;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.Transport;
import seedu.duke.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The SaveToTxt class is responsible for saving financial records data to a text file.
 * It can save both Income and Expense objects to the specified file.
 *
 * This class handles exceptions for IO errors that may occur during file operations.
 */
public class SaveToTxt {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String IO_ERROR_MESSAGE = "An error occurred while saving tasks to the file.";
    private static final String INCOME_FILE_ACCESS_MESSAGE = "Error accessing file to save incomes" + "You should " +
            "put the jar file in a folder with read and write access";
    private static final String EXPENSE_FILE_ACCESS_MESSAGE = "Error accessing file to save expenses" + "You should " +
            "put the jar file in a folder with read and write access";
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    private final String path;

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/SaveToTxt.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (SecurityException se) {
            LOGGER.log(Level.SEVERE, "Error creating log file", se);
            System.err.println("Insufficient permissions to create logs directory. Please check your permissions or " +
                    "run the program in a different directory.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    /**
     * Construct a new object of the SaveToTxt class with the file path.
     *
     * @param path The path to the text file where data will be saved.
     */
    public SaveToTxt(String path) {
        assert path != null : "incomes should not be null";
        this.path = path;
    }

    /**
     * Saves both Income and Expense objects to the specified text file.
     *
     * @param incomes   The ArrayList of Income objects to save.
     * @param expenses  The ArrayList of Expense objects to save.
     */
    public void saveIncomeAndExpense(ArrayList<Income> incomes, ArrayList<Expense> expenses){
        assert incomes != null : "incomes should not be null";
        assert expenses != null : "expenses should not be null";
        saveIncomeToTextFile(incomes);
        saveExpenseToTextFile(expenses);
    }

    /**
     * Saves Income objects to the text file. Each Income object is represented as a line in the txt file.
     *
     * @param incomes The ArrayList of Income objects to save.
     * @throws IOException If an IO error occurs during the file write operation.
     */
    public void saveIncomeToTextFile(ArrayList<Income> incomes)  {
        assert incomes != null : "incomes should not be null";
        try (FileWriter fw = new FileWriter(path)) {
            for (Income income : incomes) {
                String incomeDescription = income.getDescription();
                String incomeDate = String.valueOf(income.getDate());
                String incomeAmount = df.format(income.getAmount());
                String textToAdd = "I" + " |de " + incomeDescription + " |amt " + incomeAmount + " |date " +
                        incomeDate + "\n";
                fw.write(textToAdd);
                LOGGER.log(Level.INFO , ("Income added to file: ") +
                        incomeDescription + " with amount $ " + incomeAmount);
            }
        } catch (AccessDeniedException e){
            LOGGER.log(Level.WARNING, "No access to the folder", e);
            System.out.println(INCOME_FILE_ACCESS_MESSAGE);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error while saving file ", e);
            System.out.println(IO_ERROR_MESSAGE);
        }
    }

    /**
     * Saves Expense objects to the text file. Each Expense object is represented as a line in the file,
     * and the type of Expense (Food, Transport, or Utilities) is specified in the line.
     *
     * @param expenses The ArrayList of Expense objects to save.
     * @throws IOException If an IO error occurs during the file write operation.
     */
    public void saveExpenseToTextFile(ArrayList<Expense> expenses){
        assert expenses != null : "expenses should not be null";
        try (FileWriter fw = new FileWriter(path, true)) {
            for (Expense expense : expenses) {
                String textToAdd = "";
                String expenseDescription = expense.getDescription();
                String expenseDate = String.valueOf(expense.getDate());;
                String expenseAmount = df.format(expense.getAmount());

                // Determine the type of Expense and format the line accordingly
                if (expense.getClass() == Food.class) {
                    textToAdd = "EF" + " |de " + expenseDescription + " |amt " + expenseAmount + " |date "
                            + expenseDate + " |type " + ((Food) expense).getMealType().ordinal() + "\n";
                } else if (expense.getClass() == Transport.class) {
                    textToAdd = "ET" + " |de " + expenseDescription + " |amt " + expenseAmount + " |date "
                            + expenseDate + " |type " + ((Transport) expense).getTransportationType().ordinal() + "\n";
                } else if (expense.getClass() == Utilities.class) {
                    textToAdd = "EU" + " |de " + expenseDescription + " |amt " + expenseAmount + " |date "
                            + expenseDate + " |type " + ((Utilities) expense).getUtilityType().ordinal() + "\n";
                }
                fw.write(textToAdd);
            }
        } catch (AccessDeniedException e){
            LOGGER.log(Level.WARNING, "Error accessing file ", e);
            System.out.println(EXPENSE_FILE_ACCESS_MESSAGE);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error creating file ", e);
            System.out.println(IO_ERROR_MESSAGE);
        }
    }
}
