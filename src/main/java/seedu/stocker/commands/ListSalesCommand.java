package seedu.stocker.commands;

import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.CartEntry;
import seedu.stocker.drugs.StockEntry;

/**
 * Represents a command to list all items in the sales list.
 */
public class ListSalesCommand extends Command {
    public static final String COMMAND_WORD = "listSales";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all sold items."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all items in the sales list.";
    public static final String MESSAGE_FAILURE = "The sales list is empty.";

    @Override
    public  <T> CommandResult<T> execute() {
        StringBuilder resultMessage = new StringBuilder(MESSAGE_SUCCESS + System.lineSeparator());
        int index = 1;
        double totalCost = 0.0;

        if (salesList.getAllSales().isEmpty()) {
            return new CommandResult<>(MESSAGE_FAILURE);
        }

        for (Cart cart : salesList.getAllSales()) {
            for (CartEntry entry : cart.getCurrentCart()) {
                String serialNumber = entry.getSerialNumber();
                StockEntry stockEntry = inventory.get(serialNumber);
                if (stockEntry != null) {
                    String name = stockEntry.getDrug().getName();
                    double sellingPrice = entry.getSellingPrice();
                    double cost = sellingPrice * entry.getQuantity();
                    totalCost += cost;

                    resultMessage.append("\t").append(index).append(". ")
                            .append("Name: ").append(name)
                            .append(", Serial Number: ").append(serialNumber)
                            .append(", Quantity: ").append(entry.getQuantity())
                            .append(", Selling Price: ").append(sellingPrice)
                            .append(", Cost: ").append(cost)
                            .append(System.lineSeparator());
                    index++;
                }
            }
        }

        resultMessage.append("Total Sales: ").append(totalCost);

        return new CommandResult<>(resultMessage.toString().trim());
    }
}
