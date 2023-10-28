package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a command to display a report of stock levels sorted by quantity in ascending order.
 */
public class ShowStockLevelCommand extends Command {

    public static final String COMMAND_WORD = "stocklevel";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all drugs by quantity level "
            + "tracked by the system in ascending order. " + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Stock Level Report (Sorted by Quantity)";

    /**
     * Executes the "stocklevel" command. Displays a report of stock levels sorted by quantity in ascending order.
     *
     * @return A CommandResult containing the success message and a list of stock entries sorted by quantity.
     */
    @Override
    public CommandResult<StockEntry> execute() {
        // Assertion: Check if the inventory is properly initialized
        assert inventory != null : "Inventory should be initialized before executing ShowStockLevelCommand.";
        // Retrieve the list of drugs from the inventory
        List<StockEntry> stockEntries = inventory.getStockEntries();

        // Check if the inventory is empty
        if (stockEntries.isEmpty()) {
            // Return a CommandResult indicating that the inventory is empty
            return new CommandResult<>("The inventory is empty.");
        } else {
            // Sort the stockEntries by quantity in ascending order
            stockEntries.sort(Comparator.comparingLong(StockEntry::getQuantity));
            // Create a new list to store the sorted stockEntries
            List<StockEntry> arrangedListbyQuantity = new ArrayList<>(stockEntries);

            return new CommandResult<>(MESSAGE_SUCCESS, arrangedListbyQuantity);
        }
    }
}
