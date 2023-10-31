package seedu.duke.commands;

import seedu.duke.financialrecords.ExchangeRateManager;
import seedu.duke.storage.ExchangeRateFileHandler;

import java.util.Arrays;

public class UpdateExchangeRateCommand extends Command {
    ExchangeRateFileHandler exchangeRateFileHandler;
    String currency;
    double rate;
    public UpdateExchangeRateCommand(String fullCommand, ExchangeRateFileHandler exchangeRateFileHandler)
            throws KaChinnnngException {
        try {
            this.exchangeRateFileHandler = exchangeRateFileHandler;
            String[] args = parse(fullCommand);
            currency = args[0];
            rate = Double.parseDouble(args[1]);
        } catch (NumberFormatException | NullPointerException e) {
            throw new KaChinnnngException("Invalid command");
        }
    }

    @Override
    public void execute() throws KaChinnnngException {
        ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();
        exchangeRateManager.updateExchangeRate(currency, rate);
        System.out.printf("The SGD/%s rate has been updated to %s.\n", currency.toUpperCase(), rate);
        exchangeRateFileHandler.save(exchangeRateManager.getExchangeRates());
    }

    private static String[] parse(String fullCommand) throws KaChinnnngException {
        String[] args = fullCommand.split(" ");
        if (args.length != 5) {
            throw new KaChinnnngException("Invalid command");
        }
        return Arrays.copyOfRange(args,3, 5);
    }
}
