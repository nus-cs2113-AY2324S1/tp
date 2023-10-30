package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to list all drugs and their threshold levels in the inventory.
 */
public class ListThresholdCommand extends Command {

    public static final String COMMAND_WORD = "listthreshold";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all drugs and their threshold levels."
            + System.lineSeparator()
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all drugs by threshold level in the inventory.";

    /**
     * Executes the ListThresholdCommand by retrieving a list of StockEntry objects and their threshold levels.
     *
     * @return A CommandResult containing the list of drugs and their threshold levels or a message indicating the
     *          inventory is empty.
     */
    @Override
    public CommandResult execute() {
        assert inventory != null : "Inventory should be initialized before executing ListThresholdCommand.";

        List<StockEntry> stockEntries = inventory.getStockEntries();

        if (stockEntries.isEmpty()) {
            return new CommandResult("The inventory is empty.");
        } else {
            List<String> resultElements = new ArrayList<>();
            for (StockEntry entry : stockEntries) {
                long thresholdQuantity = entry.getThresholdQuantity();
                String drugName = entry.getDrug().getName();
                resultElements.add(drugName + ": " + thresholdQuantity);
            }
            return new CommandResult<>(MESSAGE_SUCCESS, resultElements);
        }
    }
}
