package seedu.stocker.commands;

import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.CartEntry;

/**
 * Represents a command to list all items in the sales list.
 */
public class ListSalesCommand extends Command {
    public static final String COMMAND_WORD = "listSales";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all items in the sales list."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all items in the sales list.";
    public static final String MESSAGE_FAILURE = "The sales list is empty.";

    @Override
    public CommandResult execute() {
        StringBuilder resultMessage = new StringBuilder(MESSAGE_SUCCESS + System.lineSeparator());
        int index = 1;

        if (salesList.getAllSales().isEmpty()) {
            return new CommandResult(MESSAGE_FAILURE);
        }

        for (Cart cart : salesList.getAllSales()) {
            for (CartEntry entry : cart.getCurrentCart()) {
                resultMessage.append("\t").append(index).append(". ")
                        .append("Serial Number: ").append(entry.getSerialNumber())
                        .append(", Quantity: ").append(entry.getQuantity())
                        .append(", Selling Price: ").append(entry.getSellingPrice())
                        .append(System.lineSeparator());
                index++;
            }
        }

        return new CommandResult(resultMessage.toString().trim());
    }
}
