package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

/**
 * Remove a drug from inventory and add it into the sales list
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks out current cart. "
            + "Parameters:" + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "The current cart has been checked out. ";

    public CheckOutCommand() {
    }

    @Override
    public CommandResult<StockEntry> execute() {
        currentCart.checkOut(salesList, inventory);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS));
    }
}
