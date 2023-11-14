//@@woodenclock
package seedu.wildwatch.error;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

public class DateChecker {
    private static final Pattern DATE_PATTERN = Pattern.compile(
            "(0[1-9]|[1-2][0-9]|3[0-1])-(0[1-9]|1[0-2])-\\d{4}"   // dd-mm-yyyy
    );
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static boolean isDateValid(String date) {
        try {
            date = date.trim();
            if (DATE_PATTERN.matcher(date).matches()) {
                LocalDate.parse(date, DATE_FORMATTER);
                return true;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return false;
    }
}
