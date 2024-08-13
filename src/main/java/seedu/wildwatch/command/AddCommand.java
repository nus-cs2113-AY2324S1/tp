//@@lctxct
package seedu.wildwatch.command;

import java.util.regex.Pattern;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.ui.AddCommandPrinter;
import seedu.wildwatch.ui.EntryPrinter;
import seedu.wildwatch.ui.ListCommandPrinter;

/**
 * Command class for adding entry to EntryList
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final Pattern ADD_DEFAULT_COMMAND_FORMAT =
            Pattern.compile("add"
                    + "\\s* D/\\s*(?<date>[^/]+)"
                    + "\\s* S/\\s*(?<species>[^/]+)"
                    + "\\s* N/\\s*(?<name>[^/]+)"
                    + "\\s*(?: R/\\s*(?<remark>.*)\\s*)?");

    public static final Pattern ADD_INTERACTIVE_COMMAND_FORMAT =
            Pattern.compile("add\\s+i/\\s*");

    private final Entry newEntry;

    public AddCommand(Entry entry) {
        newEntry = entry;
    }
    /**
     * Add a new Entry
     */
    @Override
    public void execute() {
        EntryList.addEntry(newEntry);

        AddCommandPrinter.entryAddedMessagePrinter();
        EntryPrinter.printEntry(EntryList.getArraySize()-1);
        ListCommandPrinter.entryCountPrinter();
    }
    /**
     * Override the default == operator to compare objects.
     * This is used to compare two AddCommand, to determine if the Entry that
     * that is being added are duplicates
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return newEntry.equals(otherAddCommand.newEntry);
    }
}

