package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.storage.ExchangeRateFileHandler;
import seedu.duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The `ExchangeRateManager` class is responsible for managing exchange rates between the Singapore Dollar (SGD) and
 * various foreign currencies.
 * It provides functionality to update, retrieve, and save exchange rates, as well as convert currencies.
 * The class uses a singleton pattern to ensure a single instance of `ExchangeRateManager` throughout the application.
 * Exchange rates are stored in a `HashMap`, and the data is read from and saved to a text file.
 */
public class ExchangeRateManager {
    private static ExchangeRateManager exchangeRateManager = null;
    private static final File file = new File("./data/ExchangeRate.txt");
    private static final List<String> SUPPORTED_CURRENCIES =
            Arrays.asList(
                    "MYR", "USD", "JPY", "KRW", "EUR", "THB", "HKD", "INR", "IDR",
                    "AUD", "GBP", "CNY", "CAD", "TWD", "VND", "PHP"
            );
    private HashMap<String, Double> exchangeRates;

    private ExchangeRateManager() {
        exchangeRates = new HashMap<>();
    }

    public HashMap<String, Double> getExchangeRates() {
        return exchangeRates;
    }

    public static List<String> getSupportedCurrencies() {
        return SUPPORTED_CURRENCIES;
    }

    /**
     * Gets the singleton instance of `ExchangeRateManager`.
     *
     * @return The singleton instance of `ExchangeRateManager`.
     */
    public static ExchangeRateManager getInstance() {
        if (exchangeRateManager == null) {
            exchangeRateManager = new ExchangeRateManager();
        }
        return exchangeRateManager;
    }

    /**
     * Displays the list of supported currencies.
     */
    public void showSupportedCurrencies() {
        Ui.showLineDivider();
        System.out.println("These are the supported currencies:");
        for (String currency : SUPPORTED_CURRENCIES) {
            System.out.print(currency + " ");
        }
        System.out.print("\n");
        Ui.showLineDivider();
    }

    /**
     * Displays the exchange rates for supported currencies.
     */
    public void showExchangeRates() {
        Ui.showLineDivider();
        int count = 0;
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            String currency = entry.getKey();
            Double rate = entry.getValue();
            if (rate != null) {
                System.out.printf("%s, %s\n", currency, rate);
                count += 1;
            }
        }
        if (count == 0) {
            System.out.println("No exchange rate has been updated");
        }
        Ui.showLineDivider();
    }

    /**
     * Displays a message about currency conversion for a specific currency.
     *
     * @param currency The currency to convert.
     */
    public void showCurrencyConversionMessage(String currency) {
        Ui.showLineDivider();
        currency = currency.toUpperCase();
        System.out.printf("We have converted %s to SGD at the SGD/%s rate of %s\n",
                currency, currency, exchangeRates.get(currency));
        Ui.showLineDivider();
    }
    /**
     * Converts an amount from a foreign currency to SGD using the specified currency's exchange rate.
     *
     * @param currency The currency to convert from.
     * @param amount   The amount to convert.
     * @return The converted amount in SGD.
     * @throws KaChinnnngException If the currency is not supported or the exchange rate is not available.
     */
    public double convertCurrency(String currency, double amount) throws KaChinnnngException {
        currency = currency.toUpperCase();
        if (!SUPPORTED_CURRENCIES.contains(currency)) {
            throw new KaChinnnngException("This currency is not supported");
        } else if (exchangeRates.get(currency) == null) {
            throw new KaChinnnngException("Please update the exchange rate for " + currency);
        }
        return amount / exchangeRates.get(currency);
    };

    /**
     * Updates the exchange rate for a specific currency.
     *
     * @param currency The currency to update.
     * @param rate     The new exchange rate.
     * @throws KaChinnnngException If the currency is not supported or the rate is invalid.
     */
    public void updateExchangeRate(String currency, double rate) throws KaChinnnngException {
        currency = currency.toUpperCase();
        if (!SUPPORTED_CURRENCIES.contains(currency)) {
            throw new KaChinnnngException("This currency is not supported");
        }
        if (rate > 3000000.00 || rate <= 0.001) {
            throw new KaChinnnngException("Fail to update exchange rate, " +
                    "the exchange rate is not between 0.001 and 3000000");
        }
        exchangeRates.put(currency, rate);
    }

}
