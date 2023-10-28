package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

/**
 * Represents a command to list all drugs in the current cart.
 * This command retrieves the list of drugs from the current cart and provides it as part of the command result.
 * If the inventory is empty, it informs the user that the inventory has no drugs.
 */
public class ViewCartCommand extends Command {

    public static final String COMMAND_WORD = "viewcart";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the current cart items." + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all the content of your cart. ";
    public static final String MESSAGE_FAILURE = "Your cart is empty. ";

    /**
     * Execute the ViewCartCommand
     */
    @Override
    public CommandResult<StockEntry> execute() {
        if (currentCart.isEmpty()) {
            return new CommandResult<>(MESSAGE_FAILURE);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, currentCart.getCurrentCart());
        }
    }

}
