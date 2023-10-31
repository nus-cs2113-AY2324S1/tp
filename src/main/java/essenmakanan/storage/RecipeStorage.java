package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeStepList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeStorage {

    private final String DATA_PATH = "data/recipes.txt";
    private final String DATA_DIRECTORY = "data";

    private ArrayList<Recipe> recipeListPlaceholder;

    public RecipeStorage() {
        recipeListPlaceholder = new ArrayList<>();
    }

    public String convertToString(Recipe recipe) {
        String recipeStepString = RecipeParser.convertSteps(recipe.getRecipeSteps().getSteps());
        String ingredientString = RecipeParser.convertIngredient(recipe.getRecipeIngredients().getIngredients());
        return recipe.getTitle() + " || " + recipeStepString + " || " + ingredientString;
    }

    public void saveData(ArrayList<Recipe> recipes) throws IOException {
        FileWriter writer = new FileWriter(DATA_PATH, false);
        String dataString;

        for (Recipe recipe : recipes) {
            dataString = convertToString(recipe);
            writer.write(dataString);
            writer.write(System.lineSeparator());
        }

        writer.close();
    }

    private void createNewData(Scanner scan) {
        String[] parsedRecipe = scan.nextLine().split(" \\|\\| ");

        String recipeDescription = parsedRecipe[0];
        RecipeStepList steps = RecipeParser.parseDataSteps(parsedRecipe[1]);
        RecipeIngredientList ingredientList = RecipeParser.parseDataRecipeIngredients(parsedRecipe[2]);

        recipeListPlaceholder.add(new Recipe(recipeDescription, steps, ingredientList));
    }

    public ArrayList<Recipe> restoreSavedData() {
        try {
            File file = new File(DATA_PATH);
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            EssenFileNotFoundException.handleFileNotFoundException(DATA_DIRECTORY, DATA_PATH);;
        }

        return recipeListPlaceholder;
    }
}
