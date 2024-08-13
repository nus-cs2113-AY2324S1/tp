package seedu.wildwatch.ui;

import seedu.wildwatch.entry.EntryList;

import java.time.format.DateTimeFormatter;

public class EntryPrinter {
    /**
     * Prints the nth entry of the wildlife data stored in WildWatch.
     *
     * @param nthEntry
     */
    public static void printEntry(int nthEntry) {
        System.out.print("Date: [" +
                EntryList.getEntryDate(nthEntry).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "] | ");
        System.out.print("Species: [" + EntryList.getEntrySpecies(nthEntry) + "] | ");
        System.out.print("Name: [" + EntryList.getEntryName(nthEntry) + "] | ");
        System.out.print("Remark: [" + EntryList.getEntryRemark(nthEntry) + "]");
        System.out.print(System.lineSeparator());
    }
}
