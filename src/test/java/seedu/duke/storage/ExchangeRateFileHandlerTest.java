package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.ExchangeRateManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExchangeRateFileHandlerTest {
    private ExchangeRateFileHandler fileHandler;
    private ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();

    @Test
    public void testLoad_validFileFormat_exchangeRateMatches() throws FileNotFoundException, KaChinnnngException {
        fileHandler = new ExchangeRateFileHandler("./src/test/testData/ExchangeRatesTestLoad.txt");
        fileHandler.load();
        // Verify that the loaded exchange rates match the original ones
        assertEquals(1.0, exchangeRateManager.getExchangeRate("USD"));
        assertEquals(0.85, exchangeRateManager.getExchangeRate("EUR"));
    }

    @Test
    public void testSave_validInput_fileContentMatches() throws KaChinnnngException, FileNotFoundException {
        // load or create filepath
        String filePath = "./src/test/testData/ExchangeRatesTestSave.txt";
        createFile(filePath);
        new PrintWriter(filePath).close();
        fileHandler = new ExchangeRateFileHandler(filePath);
        fileHandler.load();
        // Define test data
        exchangeRateManager.updateExchangeRate("USD", 1.0);

        // Save the exchange rates to the file
        fileHandler.save(exchangeRateManager.getExchangeRates());

        File file = new File(filePath);
        Scanner s = new Scanner(file); // Create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            assertEquals("USD,1.0", textLine);
        }

    }
    private boolean createFile(String filePath) throws KaChinnnngException {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return false;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return file.createNewFile();
        } catch (IOException e) {
            throw new KaChinnnngException("Cannot create file; reason: " + e.getMessage());
        }
    }
}
