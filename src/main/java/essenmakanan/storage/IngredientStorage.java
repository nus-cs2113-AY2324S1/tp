package essenmakanan.storage;

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
    private String dataDirectory;

    private ArrayList<Ingredient> ingredientListPlaceholder;

    public IngredientStorage(String path, String directory) {
        ingredientListPlaceholder = new ArrayList<>();
        dataPath = path;
        dataDirectory = directory;
    }

    public void saveData(ArrayList<Ingredient> ingredients) throws IOException  {
        FileWriter writer = new FileWriter(dataPath, false);
        String dataString;

        for (Ingredient ingredient : ingredients) {
            dataString = IngredientParser.convertToString(ingredient);
            writer.write(dataString);
            writer.write(System.lineSeparator());
        }

        writer.close();
    }

    private void createNewData(Scanner scan) {
        String[] parsedIngredient = scan.nextLine().split(" \\| ");

        String ingredientName = parsedIngredient[0];
        String ingredientQuantity = parsedIngredient[1];
        IngredientUnit ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2]);

        ingredientListPlaceholder.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
    }

    public ArrayList<Ingredient> restoreSavedData() throws FileNotFoundException {
        File file = new File(dataPath);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            createNewData(scan);
        }

        return ingredientListPlaceholder;
    }
}
