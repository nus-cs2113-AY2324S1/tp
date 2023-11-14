//@@woodenclock
package seedu.wildwatch.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Entry {
    private LocalDate date;
    private String species;
    private String name;
    private String remark;

    public Entry(String date, String species, String name, String remark) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = LocalDate.parse(date, inputFormat);
        this.species = species;
        this.name = name;
        this.remark = remark;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public void setDate(String date) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = LocalDate.parse(date, inputFormat);
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    //@@woodenclock

    public String entryString() {
        return String.format("Date: [%s] | Species: [%s] | Name: [%s] | Remark: [%s]",
                getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), getSpecies(), getName(), getRemark());
    }

    /**
     * Returns true if both entries have the same value for each field.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Entry)) {
            return false;
        }

        Entry otherEntry = (Entry) other;
        return date.equals(otherEntry.date)
                && species.equals(otherEntry.species)
                && name.equals(otherEntry.name)
                && remark.equals(otherEntry.remark);
    }
}
