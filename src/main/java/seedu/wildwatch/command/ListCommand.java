//@@woodenclock
package seedu.wildwatch.command;

import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.ui.EntryPrinter;
import seedu.wildwatch.ui.ListCommandPrinter;
import seedu.wildwatch.error.InvalidInputErrorType;

/**
 * Command class for listing all entries in EntryList
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Lists out all entry in EntryList
     */
    public static void listEntry() {
        int arraySize = EntryList.getArraySize();
        for (int i = 0; i < arraySize; i++) {
            System.out.print(i + 1 + ".");
            EntryPrinter.printEntry(i);
        }
        ListCommandPrinter.entryCountPrinter();
    }
    /**
     * Print the list of Entry
     */
    public void execute() throws InvalidInputException {
        if (EntryList.isArrayEmpty()) {
            throw new InvalidInputException(InvalidInputErrorType.EMPTY_LIST);
        }

        ListCommandPrinter.listMessagePrinter();
        listEntry();
    }
}

