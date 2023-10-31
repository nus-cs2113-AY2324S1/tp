package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.IngredientParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.recipe.Step;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class RecipeStorage {

    private final String DATA_PATH = "data/recipes.txt";
    private final String DATA_DIRECTORY = "data";

    private ArrayList<Recipe> recipeListPlaceholder;

    public RecipeStorage() {
        recipeListPlaceholder = new ArrayList<>();
    }

    private String convertSteps(ArrayList<Step> steps) {
        StringJoiner joiner = new StringJoiner(" | ");

        for (Step step: steps) {
            joiner.add(step.getDescription());
        }

        return joiner.toString();
    }

    private String convertIngredient(ArrayList<Ingredient> ingredients) {
        StringJoiner joiner = new StringJoiner(" , ");

        for (Ingredient ingredient: ingredients) {
            joiner.add(IngredientParser.convertToString(ingredient));
        }

        return joiner.toString();
    }

    public String convertToString(Recipe recipe) {
        String recipeStepString = convertSteps(recipe.getRecipeSteps().getSteps());
        String ingredientString = convertIngredient(recipe.getRecipeIngredients().getIngredients());
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

    public RecipeStepList parseSteps(String stepsString) {
        String[] parsedSteps = stepsString.split(" \\| ");
        return new RecipeStepList(parsedSteps);
    }

    public RecipeIngredientList parseIngredients(String ingredientsString) {
        String[] parsedIngredients = ingredientsString.split(" , ");
        ArrayList<Ingredient> ingredientList =  new ArrayList<>();

        for (String ingredient: parsedIngredients) {
            String[] parsedIngredient = ingredient.split(" \\| ");
            String ingredientName = parsedIngredient[0];
            String ingredientQuantity = parsedIngredient[1];
            IngredientUnit ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2]);
            ingredientList.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
        }

        return new RecipeIngredientList(ingredientList);
    }

    private void createNewData(Scanner scan) {
        String[] parsedRecipe = scan.nextLine().split(" \\|\\| ");

        String recipeDescription = parsedRecipe[0];
        RecipeStepList steps = parseSteps(parsedRecipe[1]);
        RecipeIngredientList ingredientList = parseIngredients(parsedRecipe[2]);

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
