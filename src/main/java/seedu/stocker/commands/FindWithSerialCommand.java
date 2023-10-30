package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

import java.util.ArrayList;
import java.util.List;

import static seedu.stocker.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Represents a command to find drugs in the inventory that match a given serial number.
 */
public class FindWithSerialCommand extends Command {
    public static final String COMMAND_WORD = "findwithserial";

    /**
     * Usage message for the 'findwithserial' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " /s" + ": Finds drug in inventory using serial number." +
            System.lineSeparator() +
            "Example: " + COMMAND_WORD + " /s ABC123";

    /**
     * Success message displayed after successfully finding drugs in the inventory.
     */
    public static final String MESSAGE_SUCCESS = "Listed all drugs with the keyword in the inventory.";

    private final String serialNumber;

    /**
     * Creates a FindWithSerialCommand with the specified serial number.
     *
     * @param serialNumber The serial number to search for in the inventory.
     */
    public FindWithSerialCommand(String serialNumber) {
        this.serialNumber = serialNumber.toLowerCase();
    }

    private static boolean matches(String serialNumber, StockEntry entry) {
        return entry.getSerialNumber().toLowerCase().contains(serialNumber);
    }

    /**
     * Executes the 'findwithserial' command, searching for drugs that match the serial number.
     *
     * @return A CommandResult containing the outcome of the command execution.
     */
    @Override
    public CommandResult execute() {
        if (serialNumber == null || serialNumber.trim().isEmpty()) {
            return new CommandResult<>(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        List<StockEntry> entries = inventory.getStockEntries();
        List<StockEntry> foundEntries = new ArrayList<>();

        for (StockEntry entry : entries) {
            if (matches(this.serialNumber, entry)) {
                foundEntries.add(entry);
            }
        }

        return new CommandResult<>(MESSAGE_SUCCESS, foundEntries);
    }
}
