package essenmakanan.parser;

import essenmakanan.exception.EssenException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.recipe.Step;
import essenmakanan.ui.Ui;

import java.util.ArrayList;
import java.util.StringJoiner;

public class RecipeParser {

    public static int getRecipeIndex(RecipeList recipes, String input)
            throws EssenOutOfRangeException {
        int index;
        input = input.replace("r/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = recipes.getIndexOfRecipeByName(input);
        }

        if (!recipes.recipeExist(index)) {
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    public void parseRecipeCommand(RecipeList recipes, String command, String inputDetail)
            throws EssenException {
        Ui ui = new Ui();
        switch(command) {
        case "add":
            String recipeName = inputDetail.substring(2);
            assert !recipeName.contains("r/") : "Recipe name should not contain key characters";

            Recipe newRecipe = new Recipe(recipeName);
            recipes.addRecipe(newRecipe);

            ui.printAddRecipeSuccess(recipeName);
            break;
        case "view":
            ui.printAllRecipes(recipes);
            break;
        default:
            throw new EssenException("Invalid command! Valid commands are: 'add', 'view'");
        }
    }

    public static String parseRecipeTitle(String toAdd) {
        return toAdd.replace("r/", "");
    }

    public static String convertSteps(ArrayList<Step> steps) {
        StringJoiner joiner = new StringJoiner(" | ");

        for (Step step: steps) {
            joiner.add(step.getDescription());
        }

        return joiner.toString();
    }

    public static String convertIngredient(ArrayList<Ingredient> ingredients) {
        StringJoiner joiner = new StringJoiner(" , ");

        for (Ingredient ingredient: ingredients) {
            joiner.add(IngredientParser.convertToString(ingredient));
        }

        return joiner.toString();
    }

    public static RecipeStepList parseDataSteps(String stepsString) {
        String[] parsedSteps = stepsString.split(" \\| ");
        return new RecipeStepList(parsedSteps);
    }

    public static RecipeIngredientList parseDataRecipeIngredients(String ingredientsString) {
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
}
