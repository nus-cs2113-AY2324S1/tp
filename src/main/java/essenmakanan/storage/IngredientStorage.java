package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenInvalidEnumException;
import essenmakanan.exception.EssenStorageFormatException;
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

public class IngredientStorage {

    private String dataPath;

    private ArrayList<Ingredient> ingredientListPlaceholder;

    public IngredientStorage(String path) {
        ingredientListPlaceholder = new ArrayList<>();
        dataPath = path;
    }

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
            //logger.log(Level.SEVERE, "Unable to save ingredient data", exception);
        }
    }

    private void createNewData(Scanner scan) {
        String dataString = scan.nextLine();
        String[] parsedIngredient = dataString.trim().split(" \\| ");

        EssenLogger.logInfo("Retrieving ingredient data");
        try {
            if (parsedIngredient.length != 3 || parsedIngredient[1].isBlank()) {
                throw new EssenStorageFormatException();
            }

            String ingredientName = parsedIngredient[0];
            String ingredientQuantity = parsedIngredient[1];
            IngredientUnit ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2]);

            ingredientListPlaceholder.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
        } catch (EssenStorageFormatException exception) {
            exception.handleException(dataString);
            //logger.log(Level.WARNING, "Data: " + dataString + " has an invalid format", exception);
        } catch (IllegalArgumentException exception) {
            EssenInvalidEnumException.handleException(dataString);
            //logger.log(Level.WARNING, "Data: " + dataString + " has an invalid enum", exception);
        }
        EssenLogger.logInfo("Saved ingredient data has been received");

    }

    public ArrayList<Ingredient> restoreSavedData() throws EssenFileNotFoundException {
        try {
            File file = new File(dataPath);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            //logger.log(Level.WARNING, "Text file not found");
            throw new EssenFileNotFoundException();
        }

        return ingredientListPlaceholder;
    }
}
