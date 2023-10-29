package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;


public class SetThresholdCommand extends Command {

    public static final String COMMAND_WORD = "setthreshold";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set the threshold quantity for a drug. "
            + "(default 100)" + System.lineSeparator()
            + "Example: " + COMMAND_WORD + " /n Doliprane /tq 50";

    public static final String MESSAGE_SUCCESS = "Threshold quantity set for %1$s: %2$d";

    private final String drugName;
    private final long threshold;

    public SetThresholdCommand(String name, long threshold) {
        this.drugName = name;
        this.threshold = threshold;
    }

    public SetThresholdCommand(String name) {
        this.drugName = name;
        this.threshold = 100; // Default threshold value
    }

    @Override
    public CommandResult execute(){
        StockEntry stockEntry = inventory.getStockEntry(drugName);

        if (stockEntry != null) {
            stockEntry.setThresholdQuantity(threshold);
            return new CommandResult(String.format(MESSAGE_SUCCESS, drugName, threshold));
        } else {
            return new CommandResult("Drug not found.");
        }
    }
}

