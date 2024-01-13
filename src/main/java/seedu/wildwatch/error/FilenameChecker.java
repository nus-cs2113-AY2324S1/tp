package seedu.wildwatch.error;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilenameChecker {

    private static final Pattern CSV_FILENAME_FORMAT = Pattern.compile("\\w+.csv");

    /**
     * Checks if filename for a csv file is in the format {@code [a-zA-Z0-9_].csv}.
     *
     * @param filename Input to test
     * @return true if filename is valid, else false
     */
    public static boolean isValidCsvFilenameChecker(String filename) {
        final Matcher matcher = CSV_FILENAME_FORMAT.matcher(filename);
        return matcher.matches();
    }
}
