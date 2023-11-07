package seedu.duke.storage;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.Income;
import seedu.duke.financialrecords.Expense;
import seedu.duke.financialrecords.Food;
import seedu.duke.financialrecords.Utilities;
import seedu.duke.financialrecords.Transport;
import seedu.duke.financialrecords.expensetypes.MealType;
import seedu.duke.financialrecords.expensetypes.TransportationType;
import seedu.duke.financialrecords.expensetypes.UtilityType;
import seedu.duke.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The GetFromTxt class is responsible for reading financial records data from a text file.
 * It parses the contents of the file and populates two ArrayLists: one for Incomes and one for Expenses.
 *
 * This class handles exceptions for file creation errors, parsing errors, and other issues that may arise
 * when reading data from the file.
 */
public class GetFromTxt {
    private static final String AMOUNT_NOT_SUPPORT_ERROR =
            "Amount field from this line is not supported from storage file ";

    private static final String DATE_TIME_FORMAT_ERROR =
            "Date time format incorrect on this line from storage file ";

    private static final String FILE_ACCESS_MESSAGE = "You should put the jar file in " +
            "a folder with read and write access";

    private static final String FILE_CREATION_ERROR =
            "Something went wrong while creating the file from storage file ";

    private static final String INDEX_OUT_OF_BOUND_FROM_FILE_ERROR =
            "Details missed on this line from storage file ";

    private static final String NUMBER_FROM_FILE_INCORRECT =
            "The expense type is incorrect on this line from storage file ";

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());
    private final File file;

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/GetFromTxt.log", true);
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
     * Construct a new object of the GetFromTxt class with the provided file path.
     *
     * @param path The path to the text file containing financial records data.
     */
    public GetFromTxt(String path) {
        assert path != null: "path should not be null";
        file = new File(path);
    }

    /**
     * Exception handling for file creation and file accessing, read every line of the txt file and
     * call the execute function to store all the data in
     *
     * @param incomes   The ArrayList to store Income objects.
     * @param expenses  The ArrayList to store Expense objects.
     * @throws FileNotFoundException If the file specified by the path cannot be found.
     */
    public void getFromTextFile(ArrayList<Income> incomes, ArrayList<Expense> expenses) throws FileNotFoundException,
            KaChinnnngException {
        assert incomes != null : "incomes should not be null";
        assert expenses != null : "expenses should not be null";
        try {
            if (file.createNewFile()) {
                return; // If there is no such file in the directory, create the file and return without reading data.
            }
        } catch(AccessDeniedException e){
            LOGGER.log(Level.WARNING, "Insufficient permissions to access the folder ", e);
            System.out.println(FILE_ACCESS_MESSAGE);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Error accessing storage file ", e);
            System.out.println(FILE_CREATION_ERROR);
        }
        Scanner s;
        try {
            s = new Scanner(file);                      // Create a Scanner using the File as the source
        } catch(IOException e){
            throw new KaChinnnngException("Access denied when accessing KaChinnnngggg.txt");
        }
        while (s.hasNext()) {
            String textLine = s.nextLine();
            execute(textLine, incomes, expenses);
        }
    }

    /**
     * Reads financial records data from the text file and add to the corresponding ArrayLists
     * with Income and Expense objects.
     *
     * @param incomes   The ArrayList to store Income objects.
     * @param expenses  The ArrayList to store Expense objects.
     */
    public void execute(String textLine, ArrayList<Income> incomes, ArrayList<Expense> expenses){
        try {
            // Parse the data from the text line
            int expenseType = 0;
            String commandFromFile = textLine.split(" \\|de ")[0];
            String descriptionFromFile = textLine.split(" \\|de ")[1];
            descriptionFromFile = descriptionFromFile.split(" \\|amt ")[0];
            String stringAmount = textLine.split(" \\|amt ")[1];
            stringAmount = stringAmount.split(" \\|date ")[0];
            double amountFromFile = Double.parseDouble(stringAmount);
            String stringDate = textLine.split(" \\|date ")[1];
            LocalDate dateFromFile;
            // Check for valid amount range
            if (amountFromFile > 999999.99 || amountFromFile <= 0) {
                throw new KaChinnnngException("Expense amount must be between $0.01 and $999999.99");
            }
            if (!commandFromFile.equals("I")){
                dateFromFile = LocalDate.parse(stringDate.split(" \\|type ")[0]);
                expenseType = Integer.parseInt(textLine.split(" \\|type ")[1]);
            } else{
                dateFromFile = LocalDate.parse(stringDate);
            }
            // Create appropriate objects based on the commandFromFile
            switch (commandFromFile) {
            case "I":
                incomes.add(new Income(descriptionFromFile, dateFromFile, amountFromFile));
                LOGGER.log(Level.INFO , ("Income added: ") + descriptionFromFile + amountFromFile);
                break;
            case "EF":
                expenses.add(new Food(descriptionFromFile, dateFromFile,
                        amountFromFile, MealType.values()[expenseType]));
                LOGGER.log(Level.INFO , ("Food type Expense added: ") +
                        descriptionFromFile + " with amount $ " + amountFromFile);
                break;
            case "ET":
                expenses.add(new Transport(descriptionFromFile, dateFromFile,
                        amountFromFile, TransportationType.values()[expenseType]));
                LOGGER.log(Level.INFO , ("Transport type Expense added: ") +
                        descriptionFromFile + " with amount $ " + amountFromFile);
                break;
            case "EU":
                expenses.add(new Utilities(descriptionFromFile, dateFromFile,
                        amountFromFile, UtilityType.values()[expenseType]));
                LOGGER.log(Level.INFO , ("Utility type Expense added: ") +
                        descriptionFromFile + " with amount $ " + amountFromFile);
                break;
            default:
                System.out.println("The format of this line is incorrect " + "\"" + textLine + "\"");
            }
        } catch (IndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "Details missed from the line of storage file ", e);
            System.out.println(INDEX_OUT_OF_BOUND_FROM_FILE_ERROR + "\"" + textLine + "\"");
        } catch (KaChinnnngException e) {
            LOGGER.log(Level.WARNING, "Invalid amount in the storage file ", e);
            System.out.println(AMOUNT_NOT_SUPPORT_ERROR + "\"" + textLine + "\"");
        } catch (DateTimeParseException e){
            LOGGER.log(Level.WARNING, "Invalid date format ", e);
            System.out.println(DATE_TIME_FORMAT_ERROR + "\"" + textLine + "\"");
        } catch(NumberFormatException e){
            LOGGER.log(Level.WARNING, "Non-integer for the expense type ", e);
            System.out.println(NUMBER_FROM_FILE_INCORRECT + "\"" + textLine + "\"");
        }
    }
}
