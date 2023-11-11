package seedu.duke.data;

import seedu.duke.data.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// import com.google.gson.annotations.SerializedName;

/**
 * Customized class for showing date and parsing supported string to date.
 */
public class Date {
    private static DateTimeFormatter[] formatters = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy/M/d"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),};
    private static DateTimeFormatter toStringFormatter = formatters[formatters.length - 1];
    public String standardString;
    transient LocalDate date;

    // @SerializedName("rawData")

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
    public Date(String rawData) throws InvalidDateException {
        setRawData(rawData);
    }

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
    public void setRawData(String rawData) throws InvalidDateException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(rawData, formatter);
                if (date.isBefore(LocalDate.now())) {
                    throw new InvalidDateException("Target Deadline has passed! ");
                }
                standardString = this.toString();
                return;
            } catch (InvalidDateException ide) {
                throw new InvalidDateException("Target Deadline has passed! ");
            } catch (Exception exception) {
                continue;
            }
        }
        throw new InvalidDateException("Please use a valid date with correct format!");
    }

    @Override
    public String toString() {
        return date.format(toStringFormatter);
    }
}
