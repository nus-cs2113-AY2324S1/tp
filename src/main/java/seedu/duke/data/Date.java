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
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH) };
    private static DateTimeFormatter toStringFormatter = formatters[formatters.length - 1];
    public String standardString;
    transient LocalDate date;

    /**
     * @param rawData refers to the date String
     * @throws IncorrectFormatException if failed to parse date string input
     */
    public Date(String rawData, Boolean NotAllowPast) throws IncorrectFormatException {
        setRawData(rawData, NotAllowPast);
    }

    /**
     * The method is used to set up the date field of a Date object
     * It contains the actual implementation to parse date information from a string
     * @param rawData refers to a date string
     * @param NotAllowPast if past is not allowed, for example for goal functions
     * @throws IncorrectFormatException if failed to parse date string input
     */
    public void setRawData(String rawData, boolean NotAllowPast) throws IncorrectFormatException {
        for (DateTimeFormatter formatter : formatters) {
            try {
                date = LocalDate.parse(rawData, formatter);
                if (NotAllowPast) {
                    if (date.isBefore(LocalDate.now())) {
                        throw new IncorrectFormatException("Target Deadline has passed! ");
                    }
                    standardString = this.toString();
                    return;
                } else {
                    standardString = this.toString();
                    return;
                }
            } catch (IncorrectFormatException ide) {
                throw new IncorrectFormatException("Target Deadline has passed! ");
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

    public static Date now() throws Exception {
        Date now = new Date(LocalDate.now().format(toStringFormatter), false);
        return now;
    }
}
