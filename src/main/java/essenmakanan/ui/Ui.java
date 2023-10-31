package essenmakanan.ui;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.RecipeList;

public class Ui {

    public static void start() {
        drawDivider();
        System.out.println("Welcome to Essen Makanan!!! A one-stop place " +
                "to track the\ningredients in your kitchen and store " +
                "your favourite recipes");
        System.out.println("To get started, type [help] for list of commands");
        drawDivider();
    }

    public static void bye() {
        drawDivider();
        System.out.println("Hope you had fun! See you again!");
        drawDivider();
    }

    public static void drawDivider() {
        String divider = "------------------------------------------------";
        System.out.println(divider);
    }

    public static void printNewLine() {
        System.out.println("\n");
    }

    public static void showRecipeCommands() {
        System.out.println("RECIPE");
        System.out.println("\t- View all recipes. [view r]\n"
                + "\t- Start a recipe to see if you are missing any ingredients.\n"
                + "\t\t [start RECIPE_TITLE] or [start RECIPE_ID]\n"
                + "\t- Add recipe. [add r/RECIPE_TITLE s/STEP1 s/STEP2]\n"
                + "\t- View a recipe. [view r/RECIPE_TITLE]\n"
                + "\t- Edit a recipe. [edit r/RECIPE_TITLE n/NEW_TITLE s/STEP_TO_EDIT,NEW_STEP]\n"
                + "\t- Delete a recipe. [delete r/RECIPE_INDEX] OR [delete r/RECIPE_TITLE]\n"
                + "\t- Filter recipes based on ingredients. [filter recipe i/INGREDIENTNAME [i/...] ]\n"
        );
    }

    public static void showIngredientCommands() {
        System.out.println("INGREDIENT");
        System.out.println("\t- View all ingredients. [view i]\n"
                + "\t- Add ingredient. [add i/INGREDIENT_NAME,QUANTITY,UNIT [i/...] ]\n"
                + "\t\t" + validIngredientUnits() + "\n"
                + "\t- View an Ingredient. [view i/INGREDIENT_NAME] or [view i/INGREDIENT_ID]\n"
                + "\t- Edit an ingredient. [edit i/INGREDIENT_NAME [n/NEW_NAME]"
                + " [q/NEW_QUANTITY] [u/NEW_UNIT]\n"
                + "\t- Delete an ingredient. [delete i/INGREDIENT_INDEX] OR [delete i/INGREDIENT_NAME]\n");
    }

    public static void showOtherCommands() {
        System.out.println("OTHERS");
        System.out.println("\t- View all commands [help]\n"
                + "\t- Exit application [exit]");
    }

    public static void showCommands() {
        System.out.println("Here are the commands currently available:\n");
        showRecipeCommands();
        showIngredientCommands();
        showOtherCommands();
        drawDivider();
    }

    public static void printAddRecipeSuccess(String recipeTitle) {
        System.out.println("Recipe: " + recipeTitle + " has been successfully created!");
        drawDivider();
    }

    public static void printAddIngredientsSuccess(String ingredientTitle) {
        System.out.println("Ingredient: " + ingredientTitle + " has been successfully created!");
        drawDivider();
    }

    public static void printAllIngredients(IngredientList ingredients) {
        System.out.println("Here's a list of your ingredients!");
        ingredients.listIngredients();
        drawDivider();
    }

    public static void printStartRecipeMessage(IngredientList missingIngredients,
                                               IngredientList insufficientIngredients,
                                               IngredientList diffUnitIngredients,
                                               String recipeTitle) {
        System.out.println("Starting Recipe: " + recipeTitle + "\n");
        boolean allEmpty = missingIngredients.isEmpty()
                && insufficientIngredients.isEmpty()
                && diffUnitIngredients.isEmpty();
        if (allEmpty) {
            System.out.println("You have all the ingredients you need! You are ready to go!");
        } else {
            if (!missingIngredients.isEmpty()) {
                System.out.println("You are missing these ingredient(s): ");
                missingIngredients.listIngredients();
                printNewLine();
            }
            if (!insufficientIngredients.isEmpty()) {
                System.out.println("You need to get more of these ingredient(s)\n" +
                        "(the stated quantity is the additional amount you need)");
                insufficientIngredients.listIngredients();
                printNewLine();
            }
            if (!diffUnitIngredients.isEmpty()) {
                System.out.println("You may or may not need these ingredients!!!\n" +
                        "They are of different units so we couldn't tell :(");
                diffUnitIngredients.listIngredients();
                printNewLine();
            }
        }
        System.out.println("Start your recipe again after getting the above ingredients!");
        drawDivider();
    }

    public static void printAllRecipes(RecipeList recipes) {
        System.out.println("Here's a list of your recipes!");
        recipes.listRecipeTitles();
        drawDivider();
    }

    public static void printDeleteIngredientsSuccess(String ingredientName) {
        System.out.println("You have deleted the following ingredient: " + ingredientName);
        drawDivider();
    }

    public static void printDeleteRecipeSuccess(String recipeTitle) {
        System.out.println("You have deleted the following recipe: " + recipeTitle);
        drawDivider();
    }

    public static String validIngredientUnits() {
        return("Valid ingredient units are: g, kg, ml, l, tsp, tbsp, cup, pcs");
    }

    public static void printEditIngredientNameSuccess(String oldName, String newName) {
        System.out.println("You have successfully edited the ingredient name from: " + oldName +
                " to: " + newName);
        drawDivider();
    }

    public static void printEditIngredientQuantitySuccess(String oldQuantity, String newQuantity) {
        System.out.println("You have successfully edited the ingredient quantity from: " + oldQuantity +
                " to: " + newQuantity);
        drawDivider();
    }

    public static void printEditIngredientUnitSuccess(IngredientUnit oldUnit, IngredientUnit newUnit) {
        System.out.println("You have successfully edited the ingredient unit from: " + oldUnit +
                " to: " + newUnit);
        drawDivider();
    }

    public static void printSpecificRecipe(RecipeList recipes, int recipeIndex) {
        recipes.viewRecipe(recipeIndex);
        drawDivider();
    }

    public static void printFilteredRecipes(RecipeList filteredRecipes, String ingredientName) {
        System.out.println("Here are the recipes containing ingredient " + ingredientName + ":");
        if (filteredRecipes.isEmpty()) {
            System.out.println("---NO RECIPES contain the ingredient---");
        } else {
            filteredRecipes.listRecipeTitles();
        }
        drawDivider();
    }

    public static void printEditRecipeNameSuccess (String oldName, String newName) {
        System.out.println("You have successfully edited the recipe name from: " + oldName +
                " to: " + newName);
        drawDivider();
    }

    public static void printEditRecipeStepSuccess (String oldSteps, String newSteps) {
        System.out.println("You have successfully edited the recipe steps\nfrom: " + oldSteps +
                "\nto: " + newSteps);
        drawDivider();
    }
}
