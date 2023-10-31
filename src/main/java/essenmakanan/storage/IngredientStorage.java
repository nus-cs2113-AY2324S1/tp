package essenmakanan.storage;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.ui.Ui;
import essenmakanan.parser.IngredientParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IngredientStorage {

    private final String DATA_PATH = "data/ingredients.txt";
    private final String DATA_DIRECTORY = "data";

    private ArrayList<Ingredient> ingredientListPlaceholder;

    public IngredientStorage() {
        ingredientListPlaceholder = new ArrayList<>();
    }

    public void saveData(ArrayList<Ingredient> ingredients) throws IOException  {
        FileWriter writer = new FileWriter(DATA_PATH, false);
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
        IngredientUnit ingredientUnit = null;

        ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2]);

        ingredientListPlaceholder.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
    }

    public ArrayList<Ingredient> restoreSavedData() {
        try {
            File file = new File(DATA_PATH);
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            handleFileNotFoundException();
        }

        return ingredientListPlaceholder;
    }

    private void createDukeDirectory(File newDirectory) {
        if (!newDirectory.isDirectory() && newDirectory.mkdir()) {
            System.out.println("Directory successfully created");
        } else {
            System.out.println("Directory located");
        }
    }

    private void createDukeFile(File newDatabase) throws IOException {
        if (!newDatabase.isFile() && newDatabase.createNewFile()) {
            System.out.println("Data text file successfully created");
        } else {
            System.out.println("Text file located");
        }
    }

    public void handleFileNotFoundException() {
        System.out.println("Creating database");

        File newDirectory = new File(DATA_DIRECTORY);
        File newDatabase = new File(DATA_PATH);

        try {
            createDukeDirectory(newDirectory);
            createDukeFile(newDatabase);
        } catch (IOException exception){
            Ui.handleIOException(exception);
        }

        System.out.println("Please try to run the app again.");
        System.exit(-1);
    }
}
