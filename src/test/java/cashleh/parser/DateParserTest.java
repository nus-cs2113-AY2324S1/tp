package cashleh.parser;
import cashleh.exceptions.CashLehDateParsingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void ddmmyyyySlashTest() throws CashLehDateParsingException {
        String inputString = "01/01/2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmyyyyDashTest() throws CashLehDateParsingException {
        String inputString = "01-01-2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmyyyyDotTest() throws CashLehDateParsingException {
        String inputString = "01.01.2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmyyyySpaceTest() throws CashLehDateParsingException {
        String inputString = "01 01 2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmyyyySlashTest() throws CashLehDateParsingException {
        String inputString = "1/1/2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmyyyyDashTest() throws CashLehDateParsingException {
        String inputString = "1-1-2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmyyyyDotTest() throws CashLehDateParsingException {
        String inputString = "1.1.2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmyyyySpaceTest() throws CashLehDateParsingException {
        String inputString = "1 1 2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmmmyyyySlashTest() throws CashLehDateParsingException {
        String inputString = "1/Jan/2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmmmyyyyDashTest() throws CashLehDateParsingException {
        String inputString = "1-Jan-2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmmmyyyyDotTest() throws CashLehDateParsingException {
        String inputString = "1.Jan.2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void dmmmyyyySpaceTest() throws CashLehDateParsingException {
        String inputString = "1 Jan 2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmmyyyySlashTest() throws CashLehDateParsingException {
        String inputString = "1/Jan/2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmmyyyyDashTest() throws CashLehDateParsingException {
        String inputString = "1-Jan-2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmmyyyyDotTest() throws CashLehDateParsingException {
        String inputString = "1.Jan.2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
    @Test
    public void ddmmmyyyySpaceTest() throws CashLehDateParsingException {
        String inputString = "01 Jan 2020";
        assertEquals(DateParser.parse(inputString), LocalDate.of(2020, 1, 1));
    }
}
