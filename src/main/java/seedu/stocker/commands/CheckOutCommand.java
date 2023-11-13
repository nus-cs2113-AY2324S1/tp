package seedu.stocker.commands;
import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.CartEntry;
import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.StockEntry;
import seedu.stocker.exceptions.DrugNotFoundException;

import java.util.ArrayList;
import java.util.List;

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
    public  <T> CommandResult<T> execute() {
        try {
            StringBuilder resultMessage = new StringBuilder(MESSAGE_SUCCESS + System.lineSeparator());
            int index = 1;
            double totalCost = 0.0;
            // Create a temporary holder for sold items
            List<CartEntry> soldItems = new ArrayList<>();
            for (CartEntry entry : currentCart.getCurrentCart()) {
                String serialNumber = entry.getSerialNumber();
                long quantity = entry.getQuantity();
                StockEntry stockEntry = inventory.get(serialNumber);
                if (stockEntry != null) {
                    Drug drug = stockEntry.getDrug();
                    double sellingPrice = drug.getSellingPrice();
                    double cost = sellingPrice * quantity;
                    totalCost += cost;

                    // Add the sold item to the temporary holder
                    soldItems.add(new CartEntry(serialNumber, quantity, drug));
                }
            }

            // Add the temporary holder to the sales list
            salesList.addSale(new Cart(soldItems));

            // Call the checkOut function to update inventory quantities
            currentCart.checkOut(salesList, inventory);

            resultMessage.append(System.lineSeparator()).append("Total Cost: ").append(totalCost);

            return new CommandResult<>(resultMessage.toString());
        } catch (DrugNotFoundException e ) {
            return new CommandResult<>(String.format(MESSAGE_FAILURE));
        }
    }
}
