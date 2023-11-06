package seedu.duke.commands;

import seedu.duke.financialrecords.ExchangeRateManager;
import seedu.duke.storage.ExchangeRateFileHandler;
import seedu.duke.ui.Ui;

import java.util.Arrays;

public class UpdateExchangeRateCommand extends Command {

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
            rate = Double.parseDouble(args[1]);
        } catch (NumberFormatException | NullPointerException e) {
            throw new KaChinnnngException(INVALID_RATE_ERROR);
        }
    }

    @Override
    public void execute() throws KaChinnnngException {
        ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();
        exchangeRateManager.updateExchangeRate(currency, rate);
        ui.showUpdateExchangeRateMessage(currency, rate);
        exchangeRateFileHandler.save(exchangeRateManager.getExchangeRates());
    }

    private static String[] parse(String fullCommand) throws KaChinnnngException {
        String[] args = fullCommand.split(" ");
        if (args.length != 5) {
            throw new KaChinnnngException("Invalid command: missing argument(s).\nExpected: "+EXPECTED_FORMAT);
        }
        return Arrays.copyOfRange(args,3, 5);
    }
}
