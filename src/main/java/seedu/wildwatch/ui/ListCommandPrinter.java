package seedu.wildwatch.ui;

import seedu.wildwatch.entry.EntryList;

public class ListCommandPrinter {
    /**
     * Prints out appropriate message when there is no entries to list.
     */
    public static void emptyListMessagePrinter() {
        System.out.println("OOPS!!! Nothing to list. :-(");
    }

    public static void listMessagePrinter() {
        System.out.println("Here are the entries in your list: ");
    }

    public static void entryCountPrinter() {
        if (EntryList.getArraySize() == 0) {
            System.out.println("Now you have no entries in the list.");
        } else if (EntryList.getArraySize() == 1) {
            System.out.println("Now you have 1 entry in the list.");
        } else {
            System.out.println("Now you have " + (EntryList.getArraySize()) + " entries in the list.");
        }
    }
}
