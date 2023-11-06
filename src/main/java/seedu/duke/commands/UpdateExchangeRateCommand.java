package seedu.duke.commands;

import seedu.duke.financialrecords.ExchangeRateManager;
import seedu.duke.storage.ExchangeRateFileHandler;
import seedu.duke.ui.Ui;

import java.io.File;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UpdateExchangeRateCommand extends Command {

    // Logger instance to log events and issues that occur during the execution of this class.
    private static final Logger LOGGER = Logger.getLogger(IncomeManager.class.getName());

    static{
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new KaChinnnngException("Failed to create directory " + dir.getAbsolutePath());
                }
            }
            FileHandler fh = new FileHandler("logs/UpdateExchangeRateCommand.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
            LOGGER.setLevel(Level.ALL);
            LOGGER.setUseParentHandlers(false);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error creating log file", e);
        }
    }

    private static final String EXPECTED_FORMAT =
            "update exchange rate <supported_currency> <rate>";
    private static final String INVALID_RATE_ERROR
            = "Invalid <rate>: <rate> must be a positive decimal between 0.001 and 3,000,000";

    ExchangeRateFileHandler exchangeRateFileHandler;
    Ui ui;
    String currency;
    double rate;
    public UpdateExchangeRateCommand(String fullCommand,
                                     ExchangeRateFileHandler exchangeRateFileHandler,
                                     Ui ui)
            throws KaChinnnngException {
        try {
            this.exchangeRateFileHandler = exchangeRateFileHandler;
            this.ui = ui;
            String[] args = parse(fullCommand);
            currency = args[0].trim().toUpperCase();
            LOGGER.log(Level.INFO, "successful parsing of currency"); // logging successful parsing of currency
            rate = Double.parseDouble(args[1]);
            LOGGER.log(Level.INFO, "successful parsing of rate"); // logging successful parsing of rate
        } catch (NumberFormatException | NullPointerException e) {
            throw new KaChinnnngException(INVALID_RATE_ERROR);
        }
    }

    @Override
    public void execute() throws KaChinnnngException {
        ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();
        exchangeRateManager.updateExchangeRate(currency, rate);
        LOGGER.log(Level.INFO, "Successful update of currency rate");
        ui.showUpdateExchangeRateMessage(currency, rate);
        exchangeRateFileHandler.save(exchangeRateManager.getExchangeRates());
    }

    private static String[] parse(String fullCommand) throws KaChinnnngException {
        String[] args = fullCommand.split(" ");
        if (args.length != 5) {
            LOGGER.log(Level.WARNING, "Missing or out-of-place argument(s) detected in user input: "
                    + fullCommand);
            throw new KaChinnnngException("Invalid command: missing argument(s).\nExpected: "+EXPECTED_FORMAT);
        }
        return Arrays.copyOfRange(args,3, 5);
    }
}
