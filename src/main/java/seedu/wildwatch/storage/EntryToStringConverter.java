//@@woodenclock
package seedu.wildwatch.storage;

import seedu.wildwatch.entry.Entry;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EntryToStringConverter {
    private static final DateTimeFormatter STD_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Returns String that is in the format to be written to file.
     *
     * @param entry Entry of interest.
     * @return String.
     * @throws IOException when there is problem with formatting the task.
     */
    public static String toFileString(Entry entry) throws IOException {
        assert entry != null : "Trying to convert a null entry to file string.";
        String date = entry.getDate().format(STD_FORMAT);
        String species = entry.getSpecies();
        String name = entry.getName();
        String remark = entry.getRemark();

        return String.format("%s / %s / %s / %s", date, species, name, remark);
    }
    //@@woodenclock

    public static String toCsvString(Entry entry, int id, ArrayList<String> columns) {
        assert entry != null : "Trying to convert a null entry to file string.";

        String items = String.format("%d", id);

        if (columns.contains("date")) {
            String date = entry.getDate().format(CSV_DATE_FORMAT);
            items = String.join(",", items, date);
        }

        if (columns.contains("species")) {
            String species = entry.getSpecies();
            items = String.join(",", items, species);
        }

        if (columns.contains("name")) {
            String name = entry.getName();
            items = String.join(",", items, name);
        }

        if (columns.contains("remark")) {
            String remark = entry.getRemark();
            items = String.join(",", items, remark);
        }

        return items + "\n";
    }
}
