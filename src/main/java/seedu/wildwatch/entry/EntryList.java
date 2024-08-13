package seedu.wildwatch.entry;

import java.time.LocalDate;
import java.util.ArrayList;
import seedu.wildwatch.storage.Saver;

public class EntryList {
    private static ArrayList<Entry> entries = new ArrayList<>(); //Keeps track of all Entry instances made

    public static void addEntry(Entry newEntry) {
        entries.add(newEntry);
    }

    public static void clearEntryList(){
        entries.clear();
    }

    public static void deleteEntry(int numberInput){
        entries.remove(numberInput);
    }

    public static void editEntry(int nthEntry, Entry updatedEntry) {
        entries.set(nthEntry, updatedEntry);
    }

    public static ArrayList<Entry> getArray() {
        return entries;
    }

    public static int getArraySize() {
        return entries.size();
    }

    public static Entry getEntry(int nthEntry) {
        return entries.get(nthEntry);
    }

    public static LocalDate getEntryDate(int nthEntry) {
        return getEntry(nthEntry).getDate();
    }

    public static String getEntrySpecies(int nthEntry) {
        return getEntry(nthEntry).getSpecies();
    }

    public static String getEntryName(int nthEntry) {
        return getEntry(nthEntry).getName();
    }

    public static String getEntryRemark(int nthEntry) {
        return getEntry(nthEntry).getRemark();
    }

    public static boolean isArrayEmpty() {
        return entries.isEmpty();
    }

    public static void saveEntry() {
        Saver.save(entries);
    }

    public static boolean hasSpecies(String speciesName) {
        for(int i=0;i < entries.size();i++) {
            Entry entry = entries.get(i);
            if( entry.getSpecies().equalsIgnoreCase(speciesName) ) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the combination of (date, species, name) from a new entry
     * {@code newEntry} is already present in any of the entries in {@code EntryList}.
     * Enforce uniqueness of (date, species, name) combination.g
     *
     * @param newEntry Entry to check
     * @return -1 if entry is not in list, 1-index of duplicate otherwise
     */
    public static int checkEntryExists(Entry newEntry) {
        final LocalDate date = newEntry.getDate();
        final String species = newEntry.getSpecies();
        final String name = newEntry.getName();

        for (int i = 0; i < entries.size(); i++) {
            Entry entry = entries.get(i);
            if (entry.getDate().equals(date)
                    && entry.getSpecies().equals(species)
                    && entry.getName().equals(name)) {
                return i+1;
            }
        }

        return -1;
    }
}


