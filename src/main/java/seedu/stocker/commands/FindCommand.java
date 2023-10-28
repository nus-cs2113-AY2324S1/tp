package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

import java.util.ArrayList;
import java.util.List;

import static seedu.stocker.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Represents a command to find drugs in the inventory that match a given keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    /**
     * Usage message for the 'find' command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + " /n" + ": Finds drug in inventory using name." +
            System.lineSeparator() +
            "Example: " + COMMAND_WORD + " /n panadol" +
            System.lineSeparator() +
            System.lineSeparator() +
            COMMAND_WORD + " /d" + ": Finds drug in inventory using date." +
            System.lineSeparator() +
            "Example: " + COMMAND_WORD + " /d panadol" ;

    /**
     * Success message displayed after successfully finding drugs in the inventory.
     */
    public static final String MESSAGE_SUCCESS = "Listed all drugs with the keyword in the inventory.";

    private final String keyword;
    private final String criterion;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword   The keyword to search for in the inventory.
     */
    public FindCommand(String keyword, String criterion) {

        this.keyword = keyword.toLowerCase();
        this.criterion = criterion;
    }

    private static boolean matches(String criterion, String keyword, StockEntry entry) {
        if (criterion.equals("/n")) {
            return entry.getDrug().getName().toLowerCase().contains(keyword);
        } else {
            return entry.getDrug().getExpiryDate().toLowerCase().contains(keyword);
        }
    }

    /**
     * Executes the 'find' command, searching for drugs that match the keyword.
     *
     * @return A CommandResult containing the outcome of the command execution.
     */
    @Override
    public CommandResult execute() {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new CommandResult<>(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        List<StockEntry> entries = inventory.getStockEntries();
        List<StockEntry> foundEntries = new ArrayList<>();

        for (StockEntry entry : entries) {
            if (matches(this.criterion, this.keyword, entry)) {
                foundEntries.add(entry);
            }
        }

        return new CommandResult<>(MESSAGE_SUCCESS, foundEntries);
    }


}
