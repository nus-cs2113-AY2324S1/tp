package seedu.stocker.commands;

import seedu.stocker.drugs.StockEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            "Example: " + COMMAND_WORD + " /d panadol" +
            System.lineSeparator() +
            System.lineSeparator() +
            COMMAND_WORD + " /s" + ": Finds drug in inventory using serial number." +
            System.lineSeparator() +
            "Example: " + COMMAND_WORD + " /s ABC123";

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
        if (criterion.equals("/s")) {
            this.keyword = keyword;
        } else {
            this.keyword = keyword.toLowerCase();
        }
        this.criterion = criterion;
    }

    private String getResultString(StockEntry entry, String serialNumber) {
        return "Name: " + entry.getDrug().getName()
                + ", Expiry date: " + entry.getDrug().getExpiryDate()
                + ", Serial number: " + serialNumber
                + ", Quantity: " + entry.getQuantity();
    }

    private static boolean matches(String criterion, String keyword, StockEntry entry, String serialNumber) {
        switch (criterion) {
        case "/n":
            return entry.getDrug().getName().toLowerCase().contains(keyword);
        case "/d":
            return entry.getDrug().getExpiryDate().toLowerCase().contains(keyword);
        case "/s":
            return serialNumber.equals(keyword);
        default:
            return false;
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

        List<Map.Entry<String, StockEntry>> entries = inventory.getStockEntries();
        List<String> foundResults = new ArrayList<>();

        for (Map.Entry<String, StockEntry> entry : entries) {
            if (matches(this.criterion, this.keyword, entry.getValue(), entry.getKey())) {
                foundResults.add(getResultString(entry.getValue(), entry.getKey()));
            }
        }

        if (foundResults.isEmpty()) {
            return new CommandResult<>("No drugs found with the specified criteria.");
        } else {
            return new CommandResult<>(MESSAGE_SUCCESS, foundResults);
        }
    }


}
