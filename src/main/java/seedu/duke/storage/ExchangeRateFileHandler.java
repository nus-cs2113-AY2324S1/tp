package seedu.duke.storage;

import seedu.duke.commands.KaChinnnngException;
import seedu.duke.financialrecords.ExchangeRateManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExchangeRateFileHandler {
    private static final String WRONG_FORMAT_ERROR = "Invalid row in exchange rate file";
    private String filePath;

    public  ExchangeRateFileHandler(String filePath) {
        this.filePath = filePath;
    }
    public void load() throws KaChinnnngException, FileNotFoundException {
        ExchangeRateManager exchangeRateManager = ExchangeRateManager.getInstance();
        if (createFile(filePath)) {
            return; // File not found, created new file
        }
        File file = new File(filePath);
        Scanner s = new Scanner(file); // Create a Scanner using the File as the source
        while (s.hasNext()) {
            String textLine = s.nextLine();
            try {
                String[] tokens = textLine.split(",");
                String currency = tokens[0].toUpperCase().trim();
                double rate = Double.parseDouble(tokens[1].trim());
                exchangeRateManager.updateExchangeRate(currency, rate);
            } catch (IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                // Skip line if rate is not a double
                System.out.println(WRONG_FORMAT_ERROR + " : " + textLine);
            }
        }
    }
    public void save(HashMap<String, Double> exchangeRates) throws KaChinnnngException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
                String currency = entry.getKey();
                Double rate = entry.getValue();
                fw.write(currency + "," + rate + "\n");
            }
        } catch (IOException e) {
            throw new KaChinnnngException("Something went wrong when saving exchange rates");
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
