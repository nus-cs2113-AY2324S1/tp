package cashleh.parser;

import exceptions.CashLehDateParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateParser {
    private static final DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[dd/MM/yyyy]" + "[dd.MM.yyyy]" + "[dd MM yyyy]"
            + "[d-M-yyyy]" + "[d/M/yyyy]" + "[d.M.yyyy]" + "[d M yyyy]"
            + "[d-MMM-yyyy]" + "[d/MMM/yyyy]" + "[d.MMM.yyyy]" + "[d MMM yyyy]"
            + "[dd-MMM-yyyy]" + "[dd/MMM/yyyy]" + "[dd.MMM.yyyy]" + "[dd MMM yyyy]"));
    private static final DateTimeFormatter formatter = formatterBuilder.toFormatter();

    /*
     * Parses a date string into a LocalDate object
     * @param dateString String to be parsed
     * @return LocalDate object
     * @throws CashLehDateParsingException if the date string is not in the correct format
     */
    public static LocalDate parse(String dateString) throws CashLehDateParsingException {
        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new CashLehDateParsingException();
        }
    }
}
