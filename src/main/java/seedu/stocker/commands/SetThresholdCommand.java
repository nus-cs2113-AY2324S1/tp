package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

/**
 * Represents a command to set the threshold quantity for a drug in the inventory.
 */
public class SetThresholdCommand extends Command {

    public static final String COMMAND_WORD = "setthreshold";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the threshold quantity for a drug. "
            + "(default 100)" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " /n Doliprane /tq 50";

    public static final String MESSAGE_SUCCESS = "Threshold quantity set for %1$s: %2$d";

    private final String drugName;
    private final long threshold;

    /**
     * Constructs a SetThresholdCommand with a specified drug name and threshold quantity.
     * @param name The name of the drug.
     * @param threshold The threshold quantity to set.
     */
    public SetThresholdCommand(String name, long threshold) {
        this.drugName = name;
        this.threshold = threshold;
    }


    /**
     * Executes the SetThresholdCommand by setting the threshold quantity for the specified drug.
     * @return A CommandResult indicating the success or failure of the command.
     */
    @Override
    public CommandResult execute() {

        if (inventory.getStockEntries().isEmpty()) {
            return new CommandResult("Inventory is empty.");
        }

        StockEntry stockEntry = inventory.getStockEntry(drugName);

        if (stockEntry != null) {
            stockEntry.setThresholdQuantity(threshold);
            return new CommandResult(String.format(MESSAGE_SUCCESS, drugName, threshold));
        } else {
            return new CommandResult("Drug not found.");
        }
    }
}
