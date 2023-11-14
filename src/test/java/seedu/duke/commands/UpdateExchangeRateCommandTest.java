package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.financialrecords.ExchangeRateManager;
import seedu.duke.storage.ExchangeRateFileHandler;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateExchangeRateCommandTest {

    ExchangeRateFileHandler exchangeRateFileHandler = new ExchangeRateFileHandler("test_exchange_rate.txt");
    ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();
    Ui ui = new Ui();

    @Test
    void testWithValidInput() throws KaChinnnngException {
        String command = "update exchange rate HKD 10.0";
        Command c = new UpdateExchangeRateCommand(command, exchangeRateFileHandler, ui);
        c.execute();
        assertEquals(10.0, exchangeRateManager.getExchangeRate("HKD"));
    }

    @Test
    void testWithExtraElement() {
        String command = "update exchange rate HKD 10.0 10.0 ";
        assertThrows(KaChinnnngException.class, () ->
                new UpdateExchangeRateCommand(command, exchangeRateFileHandler, ui));
    }
    @Test
    void testWithWrongNumberFormat() {
        String command = "update exchange rate HKD ads ";
        assertThrows(KaChinnnngException.class, () ->
                new UpdateExchangeRateCommand(command, exchangeRateFileHandler, ui));
    }
}
