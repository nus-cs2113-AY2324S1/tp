package seedu.duke.data;

import seedu.duke.data.exception.IncorrectFormatException;

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
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH) };
    private static DateTimeFormatter toStringFormatter = formatters[formatters.length - 1];
    public String standardString;
    transient LocalDate date;

    /**
     * @param rawData refers to the date String
     * @throws InvalidDateException if failed to parse date string input
     */
    public Date(String rawData) throws IncorrectFormatException {
        setRawData(rawData);
    }

    /**
     * The method is used to set up the date field of a Date object
     * It contains the actual implementation to parse date information from a string
     * 
     * @param rawData refers to a date string
     * @throws InvalidDateException if failed to parse date string input
     */
    public void setRawData(String rawData) throws IncorrectFormatException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(rawData, formatter);
                standardString = this.toString();
                return;
            } catch (Exception exception) {
                continue;
            }
        }
        throw new IncorrectFormatException("Please use a valid date with correct format!");
    }

    @Override
    public String toString() {
        return date.format(toStringFormatter);
    }

    public static Date Now() throws Exception {
        Date now = new Date(LocalDate.now().format(toStringFormatter));
        return now;
    }
}
