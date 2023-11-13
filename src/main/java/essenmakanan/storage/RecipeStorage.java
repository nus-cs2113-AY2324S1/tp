package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenInvalidEnumException;
import essenmakanan.exception.EssenStorageDuplicateException;
import essenmakanan.exception.EssenStorageFormatException;
import essenmakanan.exception.EssenStorageNumberException;
import essenmakanan.logger.EssenLogger;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A handler for storing recipes.
 */
public class RecipeStorage {

    private String dataPath;

    private ArrayList<Recipe> recipeListPlaceholder;

    /**
     * Creates a recipe storage handler.
     *
     * @param path The path for storing recipe data.
     */
    public RecipeStorage(String path) {
        recipeListPlaceholder = new ArrayList<>();
        dataPath = path;
    }

    /**
     * Converts a recipe into string form.
     *
     * @param recipe A recipe.
     * @return A recipe that has been converted into a string.
     */
    public String convertToString(Recipe recipe) {
        String recipeStepString;
        recipeStepString = RecipeParser.convertSteps(recipe.getRecipeSteps().getSteps());

        String ingredientString;
        ingredientString = RecipeParser.convertIngredient(recipe.getRecipeIngredients().getIngredients());

        return recipe.getTitle() + " || " + recipeStepString + " || " + ingredientString;
    }

    /**
     * Saves recipe data into a text file.
     *
     * @param recipes The recipe list.
     */
    public void saveData(ArrayList<Recipe> recipes)  {
        try {
            FileWriter writer = new FileWriter(dataPath, false);
            String dataString;

            EssenLogger.logInfo("Transferring recipe data");
            for (Recipe recipe : recipes) {
                dataString = convertToString(recipe);
                writer.write(dataString);
                writer.write(System.lineSeparator());
            }

            writer.close();
            EssenLogger.logInfo("Recipe data has been successfully saved");
        } catch (IOException exception) {
            Ui.handleIOException(exception);
            EssenLogger.logSevere("Unable to save recipe data", exception);
        }
    }

    /**
     * Searches duplicates in the data.
     *
     * @param recipeName The recipe name.
     * @return Confirmation if there is a duplicate in the list of data.
     */
    private boolean searchDuplicate(String recipeName) {
        for (Recipe recipe : recipeListPlaceholder) {
            if (recipe.getTitle().equals(recipeName)) {
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
        String[] parsedRecipe = dataString.trim().split(" \\|\\| ");

        EssenLogger.logInfo("Retrieving recipe data");
        try {
            if (parsedRecipe.length != 3 || parsedRecipe[1].isEmpty()) {
                throw new EssenStorageFormatException();
            }

            String recipeDescription = parsedRecipe[0];
            if (searchDuplicate(recipeDescription)) {
                throw new EssenStorageDuplicateException();
            }

            RecipeStepList steps;
            steps = RecipeParser.parseDataSteps(parsedRecipe[1].trim());

            RecipeIngredientList ingredientList;
            ingredientList = RecipeParser.parseDataRecipeIngredients(parsedRecipe[2].trim());

            recipeListPlaceholder.add(new Recipe(recipeDescription, steps, ingredientList));
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
        EssenLogger.logInfo("Saved recipe data has been received");
    }

    /**
     * Restores saved data from the previous session.
     *
     * @return The recipe list.
     * @throws EssenFileNotFoundException If the text file is not found.
     */
    public ArrayList<Recipe> restoreSavedData() throws EssenFileNotFoundException {
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

        return recipeListPlaceholder;
    }
}
