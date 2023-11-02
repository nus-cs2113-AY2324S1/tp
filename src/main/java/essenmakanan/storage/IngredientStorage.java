package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenInvalidUnitException;
import essenmakanan.exception.EssenStorageFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.IngredientParser;

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

    public void saveData(ArrayList<Ingredient> ingredients) throws IOException  {
        FileWriter writer = new FileWriter(dataPath, false);
        String dataString;

        for (Ingredient ingredient: ingredients) {
            dataString = IngredientParser.convertToString(ingredient);
            writer.write(dataString);
            writer.write(System.lineSeparator());
        }

        writer.close();
    }

    private void createNewData(Scanner scan) {
        String dataString = scan.nextLine();
        String[] parsedIngredient = dataString.trim().split(" \\| ");

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
        } catch (IllegalArgumentException exception) {
            EssenInvalidUnitException.handleException(parsedIngredient[2], dataString);
        }
    }

    public ArrayList<Ingredient> restoreSavedData() throws EssenFileNotFoundException {
        try {
            File file = new File(dataPath);
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            throw new EssenFileNotFoundException();
        }

        return ingredientListPlaceholder;
    }
}
