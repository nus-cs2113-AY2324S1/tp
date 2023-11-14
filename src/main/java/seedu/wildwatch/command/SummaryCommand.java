package seedu.wildwatch.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.wildwatch.entry.Entry;
import seedu.wildwatch.entry.EntryList;
import seedu.wildwatch.exception.InvalidInputException;
import seedu.wildwatch.ui.SummaryCommandPrinter;

/**
 * Command class for `summary`
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    private String speciesName;

    public SummaryCommand(String speciesName) {
        this.speciesName = speciesName.toLowerCase();
    }

    /**
     * Group the wildlife entries by their species
     *
     * @param entries
     * @return A map of the wildlife entries grouped by their species
     */
    private static Map<String, List<Entry>> groupEntriesBySpecies(List<Entry> entries) {

        Map<String, List<Entry>> map = new HashMap<String, List<Entry>>();
        for (Entry entry : entries) {
            String key = entry.getSpecies().toLowerCase();
            if (map.containsKey(key)){
                List<Entry> list = map.get(key);
                list.add(entry);
            } else {
                List<Entry> list = new ArrayList<Entry>();
                list.add(entry);
                map.put(key, list);
            }
        }
        return map;
    }

    /**
     * Group a set of wildlife entries by their name
     *
     * @param filteredEntries
     * @return A map of filteredEntries grouped by their name
     */
    private static Map<String, List<Entry>> groupSpeciesByName(List<Entry> filteredEntries) {
        Map<String, List<Entry>> filteredMap = new HashMap<String, List<Entry>>();

        for (Entry entry : filteredEntries) {
            String key = entry.getName();
            if (filteredMap.containsKey(key)) {
                List<Entry> list = filteredMap.get(key);
                list.add(entry);
            } else {
                List<Entry> list = new ArrayList<Entry>();
                list.add(entry);
                filteredMap.put(key, list);
            }
        }
        return filteredMap;
    }

    /**
     * Prints out the summary
     */
    public void execute() throws InvalidInputException {
        boolean hasArgument = !this.speciesName.isEmpty();

        ArrayList<Entry> entries = EntryList.getArray();
        // Group entries by .species attribute
        Map<String, List<Entry>> map = groupEntriesBySpecies(entries);
        // change message based on whether `summary` or `summary <species>`
        if (hasArgument) {
            SummaryCommandPrinter.printSummaryNameMessage(speciesName);
            List<Entry> filteredEntries = map.get(speciesName);
            Map<String, List<Entry>> filteredMap = groupSpeciesByName(filteredEntries);
            filteredMap.forEach((key, value) -> {
                System.out.println(key + " - (" + value.size() + ")");
                for (Entry v : value) {
                    System.out.println(v.entryString());
                }
            });

        } else {
            SummaryCommandPrinter.printSummarySpecieMessage();
            map.forEach((key, value) -> System.out.println(key + " - (" + value.size() + ")"));
        }
    }
}
