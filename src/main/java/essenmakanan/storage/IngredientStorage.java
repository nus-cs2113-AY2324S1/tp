package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenInvalidEnumException;
import essenmakanan.exception.EssenStorageDuplicateException;
import essenmakanan.exception.EssenStorageFormatException;
import essenmakanan.exception.EssenStorageNumberException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.logger.EssenLogger;
import essenmakanan.parser.IngredientParser;
import essenmakanan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A handler for storing ingredients.
 */
public class IngredientStorage {

    private String dataPath;

    private ArrayList<Ingredient> ingredientListPlaceholder;

    /**
     * Creates an ingredient storage handler.
     *
     * @param path The path for storing ingredient data.
     */
    public IngredientStorage(String path) {
        ingredientListPlaceholder = new ArrayList<>();
        dataPath = path;
    }

    /**
     * Saves ingredient data into a text file.
     *
     * @param ingredients The ingredient list.
     */
    public void saveData(ArrayList<Ingredient> ingredients) {
        try {
            FileWriter writer = new FileWriter(dataPath, false);
            String dataString;

            EssenLogger.logInfo("Transferring ingredient data");
            for (Ingredient ingredient : ingredients) {
                dataString = IngredientParser.convertToString(ingredient);
                writer.write(dataString);
                writer.write(System.lineSeparator());
            }

            writer.close();
            EssenLogger.logInfo("Ingredient data has been successfully saved");
        } catch (IOException exception) {
            Ui.handleIOException(exception);
            EssenLogger.logSevere("Unable to save ingredient data", exception);
        }
    }

    /**
     * Searches duplicates in the data.
     *
     * @param ingredientName The ingredient name
     * @return Confirmation if there is a duplicate in the list of data.
     */
    private boolean searchDuplicate(String ingredientName) {
        for (Ingredient ingredient : ingredientListPlaceholder) {
            if (ingredient.getName().equals(ingredientName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Creates a new data based on the current line of data.
     *
     * @param scan The scanner that refers to the text file.
     */
    private void createNewData(Scanner scan) {
        String dataString = scan.nextLine();
        String[] parsedIngredient = dataString.trim().split(" \\| ");

        EssenLogger.logInfo("Retrieving ingredient data");
        try {
            if (parsedIngredient.length != 3 || parsedIngredient[1].isBlank()) {
                throw new EssenStorageFormatException();
            }

            String ingredientName = parsedIngredient[0];
            if (searchDuplicate(ingredientName)) {
                throw new EssenStorageDuplicateException();
            }


            double ingredientQuantity = Double.parseDouble(parsedIngredient[1]);
            IngredientUnit ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2]);

            if (!IngredientParser.checkForValidQuantity(ingredientQuantity)) {
                throw new NumberFormatException();
            }

            ingredientListPlaceholder.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
        } catch (EssenStorageFormatException exception) {
            exception.handleException(dataString);
            String message = "Data: " + dataString + " has an invalid format";
            EssenLogger.logWarning(message, exception);
        } catch (NumberFormatException exception) {
            EssenStorageNumberException.handleException(dataString);
            String message = "Data: " + dataString + " has an invalid quantity";
            EssenLogger.logWarning(message, exception);
        } catch (IllegalArgumentException exception) {
            EssenInvalidEnumException.handleException(dataString);
            String message = "Data: " + dataString + " has an invalid enum";
            EssenLogger.logWarning(message, exception);
        } catch (EssenStorageDuplicateException exception) {
            exception.handleException(dataString);
            String message = "Data: " + dataString + " cannot be created due to duplicates";
            EssenLogger.logWarning(message, exception);
        }
        EssenLogger.logInfo("Saved ingredient data has been received");
    }

    /**
     * Restores saved data from the previous session.
     *
     * @return The ingredient list.
     * @throws EssenFileNotFoundException If the text file is not found.
     */
    public ArrayList<Ingredient> restoreSavedData() throws EssenFileNotFoundException {
        try {
            File file = new File(dataPath);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            EssenLogger.logWarning("Text file not found", exception);
            throw new EssenFileNotFoundException();
        }

        return ingredientListPlaceholder;
    }
}
