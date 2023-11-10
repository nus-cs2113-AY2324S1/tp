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

    public static int getRecipeIndex(RecipeList recipes, String input)
            throws EssenOutOfRangeException {
        int index;
        input = input.replace("r/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = recipes.getIndexOfRecipe(input);
        }

        if (!recipes.recipeExist(index)) {
            System.out.println("Your recipe name or id does not exist or it is invalid.");
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    /**
     * Get a RecipeList with recipes from a recipe ID list
     *
     * @param recipeIdList a list of ids of recipes
     * @param recipes all recipes the user has
     * @return RecipeList containing of all recipes that correspond to the index in the recipe ID list
     * @throws EssenOutOfRangeException when the ID in recipe ID list is invalid
     */
    public static RecipeList getRecipes(int[] recipeIdList, RecipeList recipes) throws EssenOutOfRangeException {
        RecipeList allRecipes = new RecipeList();
        for (int id : recipeIdList) {
            if (!recipes.recipeExist(id)) {
                System.out.println("Your recipe Id is wrong");
                throw new EssenOutOfRangeException();
            }
            allRecipes.addRecipe(recipes.getRecipe(id));
        }
        return allRecipes;
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
     * To transform a string of input "r/... r/..." to an integer array such as [1, 4, ...]
     *
     * @param input is a String from the user
     * @return an integer array consisting of index of recipes
     */
    public static int[] getRecipeIdList(String input) {
        String[] recipeInputList = getPlannedRecipesString(input);
        int[] recipeIdList = new int[recipeInputList.length];

        String recipeString;
        for (int i = 0; i < recipeInputList.length; i++) {
            recipeString = recipeInputList[i].trim();
            recipeIdList[i] = Integer.parseInt(recipeString) - 1;
        }
        return recipeIdList;
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
            System.out.println("NUMBER_OF_RECIPES should be an integer!");
            throw new EssenFormatException();
        }
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

    private static String convertStep(Step step)  {
        return step.getDescription() + " | " + step.getTag() + " | " + step.getEstimatedDuration();
    }

    public static String convertSteps(ArrayList<Step> steps) {
        StringJoiner joiner = new StringJoiner(" , ");

        for (Step step: steps) {
            joiner.add(convertStep(step));
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

    public static RecipeStepList parseDataSteps(String stepsString) throws EssenStorageFormatException
            ,IllegalArgumentException {
        String[] parsedSteps = stepsString.split(" , ");
        ArrayList<Step> stepList = new ArrayList<>();

        for (String step : parsedSteps) {
            String[] parsedStep = step.split(" \\| ");

            if (parsedStep.length != 3) {
                throw new EssenStorageFormatException();
            }

            String stepDescription = parsedStep[0];
            Tag stepTag = Tag.valueOf(parsedStep[1]);
            int stepDuration = Integer.parseInt(parsedStep[2]);
            stepList.add(new Step(stepDescription, stepTag, stepDuration));
        }

        return new RecipeStepList(stepList);
    }

    public static RecipeIngredientList parseDataRecipeIngredients(String ingredientsString)
            throws EssenStorageFormatException {
        String[] parsedIngredients = ingredientsString.split(" , ");
        ArrayList<Ingredient> ingredientList = new ArrayList<>();

        for (String ingredient : parsedIngredients) {
            String[] parsedIngredient = ingredient.split(" \\| ");

            if (parsedIngredient.length != 3 || parsedIngredient[1].isBlank()) {
                throw new EssenStorageFormatException();
            }

            String ingredientName = parsedIngredient[0];
            Double ingredientQuantity = Double.parseDouble(parsedIngredient[1]);
            IngredientUnit ingredientUnit = IngredientUnit.valueOf(parsedIngredient[2]);
            ingredientList.add(new Ingredient(ingredientName, ingredientQuantity, ingredientUnit));
        }

        return new RecipeIngredientList(ingredientList);
    }


    public static String parseFilterRecipeInput(String input) throws EssenFormatException {
        input = input.replace("recipe ", "");
        if (!input.contains("i/")) {
            throw new EssenFormatException();
        }
        return input.strip();
    }

    /**
     * Parse duration of a step from user input to minutes
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
