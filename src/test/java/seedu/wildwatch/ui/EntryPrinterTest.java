package seedu.wildwatch.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.wildwatch.entry.EntryList;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryPrinterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream mockOutput = new ByteArrayOutputStream();
    private MockedStatic<EntryList> mockEntryList;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(mockOutput));
        mockEntryList = Mockito.mockStatic(EntryList.class);
        LocalDate testDate = LocalDate.of(2023, 4, 5);
        mockEntryList.when(() -> EntryList.getEntryDate(1)).thenReturn(testDate);
        mockEntryList.when(() -> EntryList.getEntrySpecies(1)).thenReturn("Lion");
        mockEntryList.when(() -> EntryList.getEntryName(1)).thenReturn("Leo");
        mockEntryList.when(() -> EntryList.getEntryRemark(1)).thenReturn("Seen near the river");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        mockEntryList.close(); // Close the mock when done
    }

    @Test
    public void printEntry_validEntry_printsCorrectFormat() {
        // Action
        EntryPrinter.printEntry(1);

        // Assertion
        String expectedOutput = "Date: [05-04-2023] | Species: [Lion] | Name: [Leo] | Remark: [Seen near the river]" +
                System.lineSeparator();
        assertEquals(expectedOutput, mockOutput.toString());
    }
}
