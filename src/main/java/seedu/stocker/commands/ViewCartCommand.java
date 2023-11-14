package seedu.stocker.commands;

import seedu.stocker.drugs.CartEntry;
import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.StockEntry;

/**
 * Represents a command to list all drugs in the current cart.
 * This command retrieves the list of drugs from the current cart and provides it as part of the command result.
 * If the inventory is empty, it informs the user that the inventory has no drugs.
 */
public class ViewCartCommand extends Command {

    public static final String COMMAND_WORD = "viewCart";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View the current cart items and the total cost." + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all the content of your cart.";
    public static final String MESSAGE_FAILURE = "Your cart is empty. ";

    /**
     * Execute the ViewCartCommand
     */
    public  <T> CommandResult<T> execute() {
        if (currentCart.isEmpty()) {
            return new CommandResult<>(MESSAGE_FAILURE);
        } else {
            StringBuilder resultMessage = new StringBuilder(MESSAGE_SUCCESS + System.lineSeparator());
            int index = 1;
            double totalCost = 0.0;

            for (CartEntry cartEntry : currentCart.getCurrentCart()) {
                String serialNumber = cartEntry.getSerialNumber();
                long quantity = cartEntry.getQuantity();

                StockEntry entry = inventory.get(serialNumber);
                if (entry != null) {
                    Drug drug = entry.getDrug();
                    double sellingPrice = drug.getSellingPrice();
                    double cost = sellingPrice * quantity;
                    totalCost += cost;

                    resultMessage.append("\t").append(index).append(". ")
                            .append("Name: ").append(drug.getName())
                            .append(", Quantity: ").append(quantity)
                            .append(", Selling Price: ").append(sellingPrice)
                            .append(", Cost: ").append(cost)
                            .append(System.lineSeparator());

                    index++;
                }
            }

            resultMessage.append(System.lineSeparator()).append("Total Cost: ").append(totalCost);

            return new CommandResult<>(resultMessage.toString());
        }
    }
}
