package essenmakanan.parser;

import essenmakanan.exception.EssenException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.exception.EssenStorageFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.RecipeStepList;
import essenmakanan.recipe.Step;
import essenmakanan.recipe.Tag;
import essenmakanan.ui.Ui;

import java.util.ArrayList;
import java.util.StringJoiner;

public class RecipeParser {

    /**
     * Validate the recipe id or the recipe title.
     *
     * @param recipes is a RecipeList of all recipes the user has
     * @param input is the recipe title or recipe id
     * @return the index of the recipe
     * @throws EssenOutOfRangeException when the recipe id or recipe title does not exist
     * @throws EssenFormatException when there is no input of recipe id or recipe title
     */
    public static int getRecipeIndex(RecipeList recipes, String input)
            throws EssenOutOfRangeException, EssenFormatException {
        if (input.isEmpty()) {
            throw new EssenFormatException();
        }

        int index;
        input = input.replace("r/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = recipes.getIndexOfRecipe(input);
        }

        if (!recipes.recipeExist(index) || index < 0) {
            System.out.println("Your recipe name or id does not exist or it is invalid.");
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    /**
     * To remove the unused item at position zero after splitting the string.
     * For example, ["", "fish", "rice"] as recipeInputString, will return ["fish", "rice"] after passing through method
     *
     * @param recipeInputString is the userInput in the format "r/RECIPE_ID [r/...]"
     * @return recipeInputList which is a String[] that excludes the first unused variable
     */
    public static String[] getPlannedRecipesString(String recipeInputString) {
        String[] recipeInputListTemp = recipeInputString.split("r/");
        String[] recipeInputList = new String[recipeInputListTemp.length - 1];
        System.arraycopy(recipeInputListTemp, 1, recipeInputList, 0, recipeInputList.length); //remove first space item
        return recipeInputList;
    }

    /**
     * Check for errors within the plan command input by user
     * If no error, returns an integer list
     *
     * @param userInput is the input by user
     * @throws EssenFormatException when there is an error in user input
     */
    public static void parsePlanCommandInput(String userInput) throws EssenFormatException {
        // to check if compulsory fields are filled in
        if (userInput.split(" ").length < 2 || !userInput.contains("r/")) {
            System.out.println("Note the format `plan NUMBER_OF_RECIPES r/RECIPE_ID [r/...]`\n" +
                    "NUMBER_OF_RECIPES must be larger than or equal to 1 and it is a compulsory field\n" +
                    "r/RECIPE_ID is a compulsory field");
            throw new EssenFormatException();
        }

        String[] input = userInput.split(" ", 2);
        String numberOfRecipesString = input[0];
        String recipeInputString = input[1];

        try {
            // to check if at least 1 recipe is added
            int numberOfRecipes = Integer.parseInt(numberOfRecipesString);
            if (numberOfRecipes <= 0) {
                System.out.println("NUMBER_OF_RECIPES must be larger than or equal to 1");
                throw new EssenFormatException();
            }

            String[] recipeInputList = getPlannedRecipesString(recipeInputString);
            for (int i = 0; i < recipeInputList.length; i++) {
                recipeInputList[i] = recipeInputList[i].trim();
            }

            // to check if NUMBER_OF_RECIPES corresponds to the total number of recipes input
            int numberOfRecipesInInput = recipeInputList.length;

            if (numberOfRecipesInInput != numberOfRecipes) {
                System.out.println("Number of recipes in your input marked " +
                        "by 'r/' does not correspond to your NUMBER_OF_RECIPES");
                throw new EssenFormatException();
            }

            // to check if each RECIPE_INDEX is an integer
            for (String recipe : recipeInputList) {
                Integer.parseInt(recipe); //to check if can be converted to integer, else, will throw an exception
            }
        } catch (NumberFormatException e) {
            System.out.println("NUMBER_OF_RECIPES and RECIPE_INDEX should be integers!");
            throw new EssenFormatException();
        }
    }

    /**
     * Parse a recipe title.
     *
     * @param toAdd The given input.
     * @return A title for the recipe.
     */
    public static String parseRecipeTitle(String toAdd) {
        return toAdd.replace("r/", "");
    }

    /**
     * Converts a step into string form.
     *
     * @param step A step.
     * @return A step that has been converted into string.
     */
    private static String convertStep(Step step)  {
        return step.getDescription() + " | " + step.getTag() + " | " + step.getEstimatedDuration();
    }

    /**
     * Joins all the steps into string form.
     *
     * @param steps The step list.
     * @return Steps that has been converted into string.
     */
    public static String convertSteps(ArrayList<Step> steps) {
        StringJoiner joiner = new StringJoiner(" , ");

        for (Step step: steps) {
            joiner.add(convertStep(step));
        }

        return joiner.toString();
    }

    /**
     * Joins all the ingredients into string form.
     *
     * @param ingredients The ingredient list.
     * @return Ingredients that has been converted into string.
     */
    public static String convertIngredient(ArrayList<Ingredient> ingredients) {
        StringJoiner joiner = new StringJoiner(" , ");

        for (Ingredient ingredient: ingredients) {
            joiner.add(IngredientParser.convertToString(ingredient));
        }

        return joiner.toString();
    }

    /**
     * Parse steps from a string.
     *
     * @param stepsString The string containing steps.
     * @return The list of recipe steps.
     * @throws EssenStorageFormatException If the storage format is invalid.
     * @throws IllegalArgumentException If the data cannot be converted into enum.
     */
    public static RecipeStepList parseDataSteps(String stepsString) throws EssenStorageFormatException
            , IllegalArgumentException {
        String[] parsedSteps = stepsString.split(" , ");
        ArrayList<Step> stepList = new ArrayList<>();

        for (String step : parsedSteps) {
            String[] parsedStep = step.split(" \\| ");

            if (parsedStep.length != 3) {
                throw new EssenStorageFormatException();
            }

            String stepDescription = parsedStep[0].trim();
            Tag stepTag = Tag.valueOf(parsedStep[1].trim());
            int stepDuration = Integer.parseInt(parsedStep[2].trim());

            if (stepDuration < 0) {
                throw new EssenStorageFormatException();
            }

            stepList.add(new Step(stepDescription, stepTag, stepDuration));
        }

        return new RecipeStepList(stepList);
    }

    /**
     * Parse ingredient list from string.
     *
     * @param ingredientsString The string containing ingredients.
     * @return The list of ingredients of a recipe.
     * @throws EssenStorageFormatException If the storage format is invalid.
     * @throws NumberFormatException If the data cannot be converted into enum.
     */
    public static RecipeIngredientList parseDataRecipeIngredients(String ingredientsString)
            throws EssenStorageFormatException, NumberFormatException {
        String[] parsedIngredients = ingredientsString.split(" , ");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        for (String ingredientData : parsedIngredients) {
            String[] parsedIngredient = ingredientData.split(" \\| ");

            if (parsedIngredient.length != 3 || parsedIngredient[1].isBlank()) {
                throw new EssenStorageFormatException();
            }

            String ingredientName = parsedIngredient[0].trim();

            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getName().equals(ingredientName)) {
                    throw new EssenStorageFormatException();
                }
            }

            double ingredientQuantity = Double.parseDouble(parsedIngredient[1].trim());

            if (!IngredientParser.checkForValidQuantity(ingredientQuantity)) {
                throw new NumberFormatException();
            }

            IngredientUnit ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2].trim());
            ingredientList.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
        }

        return new RecipeIngredientList(ingredientList);
    }


    /**
     * To check if filter recipes contains "i/"
     *
     * @param input the filter recipe command input
     * @return a String of the input stripped of whitespaces
     * @throws EssenFormatException when "i/" is not found in input
     */
    public static String parseFilterRecipeInput(String input) throws EssenFormatException {
        input = input.replace("recipe ", "");
        if (!input.contains("i/")) {
            throw new EssenFormatException();
        }
        return input.strip();
    }

    /**
     * Parse duration of a step from user input to minutes
     *
     * @param time duration of a step, in min/h
     * @return int duration in minutes
     * @throws EssenFormatException if unit of duration is not specified
     */
    public static int parseStepsDuration(String time) throws EssenFormatException{

        if (time.contains("minutes") || time.contains("mins") || time.contains("min")) {
            time = time.replace("minutes", "")
                .replace("mins", "")
                .replace("min", "")
                .trim();
            return Integer.parseInt(time);
        } else if (time.contains("hours") || time.contains("h") || time.contains("hour")) {
            time = time.replace("hours", "")
                .replace("h", "")
                .replace("hour", "")
                .trim();
            return (int) (Double.parseDouble(time)*60);
        } else {
            System.out.println("Please specify unit of duration (min/h)");
            throw new EssenFormatException();
        }

    }
}
