package seedu.nuscents.storage;

import seedu.nuscents.data.exception.NuscentsException;
import seedu.nuscents.data.transaction.Transaction;
import seedu.nuscents.data.transaction.Allowance;
import seedu.nuscents.data.transaction.Expense;
import seedu.nuscents.data.TransactionList;
import seedu.nuscents.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

import static seedu.nuscents.parser.Parser.parseAllowance;
import static seedu.nuscents.parser.Parser.parseBudget;
import static seedu.nuscents.parser.Parser.parseExpense;

public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            FileHandler fileHandler = new FileHandler("storage.log");
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Logging to file not working.", e);
        }
    }

    public String getPath() {
        return filePath;
    }

    /**
     * Reads data from the storage file and return it.
     *
     * @return an arraylist of tasks
     * @throws FileNotFoundException If the storage file does not exist.
     */
    public ArrayList<Transaction> readDataFromFile() throws FileNotFoundException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        File file = new File(filePath);
        logger.log(Level.INFO, "Creating a File object to read data from file");
        transactionDecoder(file, transactions);
        logger.log(Level.INFO, "Loading from data file finished");
        return transactions;
    }

    /**
     * Decodes the storage data file and store it into the arraylist of tasks.
     *
     * @param file         storage data file
     * @param transactions arraylist of tasks
     * @throws FileNotFoundException If the storage data file does not exist.
     */
    private static void transactionDecoder(File file, ArrayList<Transaction> transactions)
            throws FileNotFoundException {
        Scanner data = new Scanner(file);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM, yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        while (data.hasNext()) {
            String transactionDetails = data.nextLine();
            char transactionType = transactionDetails.charAt(0);
            try {
                String arguments = buildArguments(transactionDetails, formatter, dateFormat);
                switch (transactionType) {
                case 'A':
                    transactions.add(parseAllowance(arguments));
                    break;
                case 'E':
                    transactions.add(parseExpense(arguments));
                    break;
                default:
                    Ui.showDataCorruptedError();
                    break;
                }
            } catch (ParseException | NuscentsException e) {
                logger.log(Level.WARNING, "Something went wrong when reading data from file");
                Ui.showException(e);
                Ui.showDataCorruptedError();
            }
        }
    }

    private static String buildArguments(String transactionDetails, SimpleDateFormat formatter,
                                         SimpleDateFormat dateFormat)
            throws ParseException {
        String[] columns = transactionDetails.split("\\s*\\|\\s*");
        Date date = formatter.parse(columns[2]);
        String dateString = dateFormat.format(date);
        String arguments = "/amt " + columns[1] + " /date " + dateString + " /desc " + columns[3];
        if (columns.length > 4) {
            arguments += " /note " + columns[4];
        }
        if (columns.length > 5) {
            arguments += " /cat " + columns[5];
        }
        return arguments;
    }

    /**
     * Writes the data to the storage file.
     * Creates a new file if the file does not exist.
     *
     * @param transactionList list of tasks
     * @throws IOException If there were errors converting and/or storing the data to the file.
     */
    public void writeToFile(TransactionList transactionList) throws IOException {
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        logger.log(Level.INFO, "Creating a File object to write data to file");
        ArrayList<Transaction> transactions = transactionList.getTransactions();
        for (Transaction transaction : transactions) {
            logger.log(Level.INFO, "Converting info to storage format");
            String output = toStorageFormat(transaction);
            fw.write(output);
            fw.write("\n");
        }
        logger.log(Level.INFO, "Transaction data successfully stored");
        fw.close();
    }

    private static String toStorageFormat(Transaction transaction) {
        if (transaction instanceof Allowance) {
            return "A" + " | " + transaction.toString();

        } else if (transaction instanceof Expense) {
            return "E" + " | " + transaction.toString();

        } else {
            logger.log(Level.WARNING, "Invalid transaction format");
            return null;
        }
    }

    public void writeBudgetToFile(TransactionList transactionList) throws IOException {
        File file = new File("./data/budget.txt");
        FileWriter fw = new FileWriter(file);
        String budget = Float.toString(transactionList.getBudget());
        fw.write(budget);
        fw.close();
    }

    public float readBudgetFromFile() throws FileNotFoundException, IOException {
        try {
            File file = new File("./data/budget.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String arguments = sc.nextLine();
                if (!arguments.equals("0.0")) {
                    float budget = parseBudget(arguments);
                    return budget;
                }
                return 0;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui.showReadDataError();
            File file = new File("./data/budget.txt");
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (NuscentsException e) {
            Ui.showException(e);
        }
        return 0;
    }
}

