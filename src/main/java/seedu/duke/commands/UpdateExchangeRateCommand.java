package seedu.duke.commands;

import seedu.duke.financialrecords.ExchangeRateManager;

import java.util.Arrays;

public class UpdateExchangeRateCommand extends Command {
    String currency;
    double rate;
    public UpdateExchangeRateCommand(String fullCommand) throws KaChinnnngException {
        try {
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
    }

    private static String[] parse(String fullCommand) throws KaChinnnngException {
        String[] args = fullCommand.split(" ");
        if (args.length != 5) {
            throw new KaChinnnngException("Invalid command");
        }
        return Arrays.copyOfRange(args,3, 5);
    }
}
