package seedu.stocker.commands;
import seedu.stocker.drugs.Cart;
import seedu.stocker.drugs.CartEntry;
import seedu.stocker.drugs.Drug;
import seedu.stocker.drugs.StockEntry;

<<<<<<< HEAD
import seedu.stocker.drugs.*;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> AzfarulMatin-Price

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

<<<<<<< HEAD
=======
            // Create a temporary holder for sold items
            List<CartEntry> soldItems = new ArrayList<>();

>>>>>>> AzfarulMatin-Price
            for (CartEntry entry : currentCart.getCurrentCart()) {
                String serialNumber = entry.getSerialNumber();
                long quantity = entry.getQuantity();

<<<<<<< HEAD
                // Assuming that the StockEntry and Drug classes have similar structures in CheckOutCommand and ViewCartCommand
=======
>>>>>>> AzfarulMatin-Price
                StockEntry stockEntry = inventory.get(serialNumber);
                if (stockEntry != null) {
                    Drug drug = stockEntry.getDrug();
                    double sellingPrice = drug.getSellingPrice();
                    double cost = sellingPrice * quantity;
                    totalCost += cost;
<<<<<<< HEAD
                }
            }

=======

                    // Add the sold item to the temporary holder
                    soldItems.add(new CartEntry(serialNumber, quantity, sellingPrice));
                }
            }

            // Add the temporary holder to the sales list
            salesList.addSale(new Cart(soldItems));

>>>>>>> AzfarulMatin-Price
            resultMessage.append(System.lineSeparator()).append("Total Cost: ").append(totalCost);

            return new CommandResult<>(resultMessage.toString());
        }
    }
}
