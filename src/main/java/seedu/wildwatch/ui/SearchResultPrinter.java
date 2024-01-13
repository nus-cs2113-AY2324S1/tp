package seedu.wildwatch.ui;

import java.util.ArrayList;

public class SearchResultPrinter {
    /**
     * Prints out the entries that match the search key word.
     * If no match, prints out appropriate message that there was no match found.
     *
     * @param hasMatch
     * @param matchingEntries
     */
    public static void findEntryMessagePrinter(boolean hasMatch, ArrayList<Integer> matchingEntries) {
        if (!hasMatch) {
            System.out.println("No matching entries found.");
        } else {
            System.out.println("Here are the matching entries in your list:");
            for (int i = 0; i < matchingEntries.size(); i++) {
                System.out.print((i+1) + ".");
                EntryPrinter.printEntry(matchingEntries.get(i));
            }
        }
    }
}
