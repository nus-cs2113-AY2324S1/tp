package seedu.duke.financialrecords;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.KaChinnnngException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

public class ExchangeRateManagerTest {
    private ExchangeRateManager exchangeRateManager;

    @BeforeEach
    public void setUp() {
        exchangeRateManager = ExchangeRateManager.getInstance();
    }

    @Test
    public void testGetInstance() {
        // Ensure that getInstance returns the same instance of ExchangeRateManager
        ExchangeRateManager instance1 = ExchangeRateManager.getInstance();
        ExchangeRateManager instance2 = ExchangeRateManager.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testSupportedCurrencies() {
        // Ensure that the list of supported currencies is not empty
        assertFalse(ExchangeRateManager.getSupportedCurrencies().isEmpty());
    }

    @Test
    public void testUpdateExchangeRate_validInput_updateSameAsRetrieved() {
        String currency = "USD";
        double rate = 1.3;

        // Update the exchange rate
        try {
            exchangeRateManager.updateExchangeRate(currency, rate);
        } catch (KaChinnnngException e) {
            fail("Should not throw an exception");
        }

        // Retrieve and compare the exchange rate
        double retrievedRate = exchangeRateManager.getExchangeRates().get(currency);
        assertEquals(rate, retrievedRate);
    }

    @Test
    public void testConvertCurrency() throws KaChinnnngException {
        String currency = "USD";
        double rate = 1.3;
        double amount = 100.0;

        // Update the exchange rate
        try {
            exchangeRateManager.updateExchangeRate(currency, rate);
        } catch (KaChinnnngException e) {
            fail("Should not throw an exception");
        }

        // Convert currency and check the result
        double convertedAmount = exchangeRateManager.convertCurrency(currency, amount);
        assertEquals(76.923, convertedAmount, 0.001);
    }
}
