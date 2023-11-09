package seedu.stocker.commands;

import seedu.stocker.drugs.*;

/**
 * Remove a drug from inventory and add it into the sales list
 */
public class CheckOutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks out current cart. "
            + "Parameters:" + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "The current cart has been checked out.";
    public static final String MESSAGE_FAILURE = "An error happened, the cart hasn't been checked out. ";

    public CheckOutCommand() {
    }

    @Override
    public CommandResult execute() {
        if (currentCart.isEmpty()) {
            return new CommandResult(MESSAGE_FAILURE);
        } else {
            StringBuilder resultMessage = new StringBuilder(MESSAGE_SUCCESS + System.lineSeparator());
            int index = 1;
            double totalCost = 0.0;

            for (CartEntry entry : currentCart.getCurrentCart()) {
                String serialNumber = entry.getSerialNumber();
                long quantity = entry.getQuantity();

                // Assuming that the StockEntry and Drug classes have similar structures in CheckOutCommand and ViewCartCommand
                StockEntry stockEntry = inventory.get(serialNumber);
                if (stockEntry != null) {
                    Drug drug = stockEntry.getDrug();
                    double sellingPrice = drug.getSellingPrice();
                    double cost = sellingPrice * quantity;
                    totalCost += cost;
                }
            }

            resultMessage.append(System.lineSeparator()).append("Total Cost: ").append(totalCost);

            return new CommandResult<>(resultMessage.toString());
        }
    }
}
