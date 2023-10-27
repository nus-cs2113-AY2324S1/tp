package seedu.duke.financialrecords;

import seedu.duke.commands.KaChinnnngException;
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
    private static final String WRONG_FORMAT_ERROR = "The format of this line is wrong";
    private static final String CURRENCY_NOT_SUPPORTED_ERROR = "This currency is not supported";
    private static final File file = new File("./data/ExchangeRate.txt");
    private static final List<String> SUPPORTED_CURRENCIES =
            Arrays.asList(
                    "MYR", "USD", "JPY", "KRW", "EUR", "THB", "HKD", "INR", "IDR",
                    "AUD", "GBP", "CNY", "CAD", "TWD", "VND", "PHP"
            );
    private HashMap<String, Double> exchangeRates;

    private ExchangeRateManager() {
        exchangeRates = new HashMap<>();
        for (String currency : SUPPORTED_CURRENCIES){
            exchangeRates.put(currency, null);
        }
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
     * Reads exchange rates from a file and populates the `exchangeRates` HashMap.
     *
     * @throws KaChinnnngException If there is an issue with file operations.
     * @throws FileNotFoundException If the exchange rate file is not found.
     */
    public void getExchangeRatesFromFile() throws KaChinnnngException, FileNotFoundException {
        if (createFile()) {
            return; // File not found, created new file
        }

        Scanner s = new Scanner(file); // Create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                String[] tokens = textLine.split(",");
                if (tokens.length != 2) {
                    System.out.println(WRONG_FORMAT_ERROR + " : " + textLine);
                    continue; //Skip line if format of line is wrong
                }
                String currency = tokens[0].toUpperCase().trim();
                if (!SUPPORTED_CURRENCIES.contains(currency)) {
                    System.out.println(CURRENCY_NOT_SUPPORTED_ERROR + " : " + textLine);
                    continue; // Skip line if currency is not supported
                }
                double rate = Double.parseDouble(tokens[1].trim());
                if (rate > 3000000.00 || rate <= 0.001) {
                    continue; // Skip line if rate is not within limit
                }
                exchangeRates.put(currency, rate);
            } catch (NumberFormatException | NullPointerException e) {
                // Skip line if rate is not a double
                System.out.println(WRONG_FORMAT_ERROR + " : " + textLine);
            }
        }
    }

    /**
     * Saves exchange rates to a file.
     *
     * @throws KaChinnnngException If there is an issue with file operations.
     */
    private void saveExchangeRateToFile() throws KaChinnnngException {
        try (FileWriter fw = new FileWriter(file)) {
            for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
                String currency = entry.getKey();
                Double rate = entry.getValue();
                if (rate != null) {
                    fw.write(currency + "," + rate + "\n");
                }
            }
        } catch (IOException e) {
            throw new KaChinnnngException("Something went wrong when saving exchange rates");
        }
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
            throw new KaChinnnngException(CURRENCY_NOT_SUPPORTED_ERROR);
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
            throw new KaChinnnngException(CURRENCY_NOT_SUPPORTED_ERROR);
        }
        if (rate > 3000000.00 || rate <= 0.001) {
            throw new KaChinnnngException("Fail to update exchange rate, " +
                    "the exchange rate is not between 0.001 and 3000000");
        }
        exchangeRates.put(currency, rate);
        saveExchangeRateToFile();
    }

    /**
     * Creates the exchange rate file if it does not exist.
     *
     * @return `true` if the file is created, `false` if it already exists.
     * @throws KaChinnnngException If there is an issue with file operations.
     */
    public boolean createFile() throws KaChinnnngException {
        try {
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
