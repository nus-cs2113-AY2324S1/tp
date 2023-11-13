package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

/**
 * Represents a command to set the threshold quantity for a drug in the inventory.
 */
public class SetThresholdCommand extends Command {

    public static final String COMMAND_WORD = "setThreshold";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the threshold quantity for a drug. "
            + "(default 100)" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " /s TC150 /tq 50";

    public static final String MESSAGE_SUCCESS = "Threshold quantity set for %1$s: %2$d";

    private final String serialNumber;
    private final long threshold;

    /**
     * Constructs a SetThresholdCommand with a specified drug serial number and threshold quantity.
     * @param serialNumber The serial number of the drug.
     * @param threshold The threshold quantity to set.
     */
    public SetThresholdCommand(String serialNumber, long threshold) {
        this.serialNumber = serialNumber;
        this.threshold = threshold;
    }


    /**
     * Executes the SetThresholdCommand by setting the threshold quantity for the specified drug.
     * @return A CommandResult indicating the success or failure of the command.
     */
    @Override
    public  <T> CommandResult<T> execute() {

        if (inventory.getStockEntries().isEmpty()) {
            return new CommandResult<>("Inventory is empty.");
        }

        StockEntry stockEntry = inventory.get(serialNumber);

        if (stockEntry != null) {
            stockEntry.setThresholdQuantity(threshold);
            return new CommandResult<>(String.format(MESSAGE_SUCCESS, stockEntry.getDrug().getName(), threshold));
        } else {
            return new CommandResult<>("Drug not found.");
        }
    }
}
