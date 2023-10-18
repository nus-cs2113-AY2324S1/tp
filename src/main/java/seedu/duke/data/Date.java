package seedu.duke.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// import com.google.gson.annotations.SerializedName;

/**
 * Customized class for showing date and parsing supported string to date.
 */
public class Date {
    transient LocalDate date;

    // @SerializedName("rawData")
    public String standardString;

    private static DateTimeFormatter[] formatters = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("M-d-yyyy"),
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH), };
    private static DateTimeFormatter toStringFormatter = formatters[formatters.length - 1];

    /**
     * Modifying an existing date with a rawData String.
     *
     * @param rawData A String that needs to comply with a supported format and
     *                indicates a correct date that will be recorded by this Date
     *                instance.
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public void setRawData(String rawData) throws Exception {
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(rawData, formatter);
                standardString = this.toString();
                return;
            } catch (Exception exception) {
            }
        }
        throw new Exception("Unable to parse date time!");
    }

    /**
     * Create a new date.
     *
     * @param rawData A String that needs to comply with a supported format and
     *                indicates a correct date that will be recorded by this Date
     *                instance.
     * @throws TipsException Any excption will be throw in this type, which contains
     *                       information about this exception and the possible
     *                       solution.
     */
    public Date(String rawData) throws Exception {
        setRawData(rawData);
    }

    @Override
    public String toString() {
        return date.format(toStringFormatter);
    }
}
