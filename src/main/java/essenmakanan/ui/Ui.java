package essenmakanan.ui;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.RecipeList;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    /**
     * Prints out a welcome message.
     */
    public static void start() {
        drawDivider();
        System.out.println("Welcome to Essen Makanan!!! A one-stop place " +
                "to track the\ningredients in your kitchen and store " +
                "your favourite recipes");
        System.out.println("To get started, type [help] for list of commands");
        drawDivider();
    }

    /**
     * Prints out a goodbye message.
     */
    public static void bye() {
        drawDivider();
        System.out.println("Hope you had fun! See you again!");
        drawDivider();
    }

    /**
     * Prints out a divider.
     */
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
                + "\t- Add recipe. [add r/RECIPE_TITLE t/TAG_ID s/STEP1 s/STEP2 t/TAG_ID s/STEP3 [s/...] "
                + "i/INGREDIENT_NAME [i/...]]\n"
                + "\t\t Tags:\n"
                + "\t\t\t1 - NIGHT_BEFORE\n\t\t\t2 - MORNING_OF_COOKING\n\t\t\t"
                + "3 - MORE_THAN_ONE_DAY\n\t\t\t4 - ACTUAL_COOKING\n"
                + "\t- View a recipe. [view r/RECIPE_TITLE]\n"
                + "\t- Edit a recipe. [edit r/RECIPE_TITLE n/NEW_TITLE s/STEP_TO_EDIT,NEW_STEP]\n"
                + "\t- Delete a recipe. [delete r/RECIPE_INDEX] OR [delete r/RECIPE_TITLE]\n"
                + "\t- Filter recipes based on ingredients. [filter recipe i/INGREDIENTNAME [i/...] ]\n"
                + "\t- View all available recipes. [view ar]\n"
        );
    }

    public static void showIngredientCommands() {
        System.out.println("INGREDIENT");
        System.out.println("\t- View all ingredients. [view i]\n"
                + "\t- Add an ingredient. [add i/INGREDIENT_NAME,QUANTITY,UNIT [i/...] ]\n"
                + "\t\t" + validIngredientUnits() + "\n"
                + "\t- View an Ingredient. [view i/INGREDIENT_NAME] or [view i/INGREDIENT_ID]\n"
                + "\t- Edit an ingredient. [edit i/INGREDIENT_NAME [n/NEW_NAME]"
                + " [q/NEW_QUANTITY] [u/NEW_UNIT]\n"
                + "\t- Delete an ingredient. [delete i/INGREDIENT_INDEX] OR [delete i/INGREDIENT_NAME]\n");
    }

    /**
     * Prints out the list of shortcut commands.
     */
    public static void showShortcutCommands() {
        System.out.println("SHORTCUT");
        System.out.println("\t- View all shortcuts. [view sc]\n"
                + "\t- Add a shortcut. [add i/INGREDIENT_NAME,QUANTITY]\n"
                + "\t- Edit a shortcut. [edit i/INGREDIENT_NAME [n/NEW_NAME]"
                + " [q/NEW_QUANTITY]\n"
                + "\t- Delete a shortcut. [delete i/SHORTCUT_INDEX] OR [delete i/INGREDIENT_NAME]\n");
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
        showShortcutCommands();
        showOtherCommands();
        drawDivider();
    }

    /**
     * Prints out recent added recipe title.
     *
     * @param recipeTitle The recipe title.
     */
    public static void printAddRecipeSuccess(String recipeTitle) {
        System.out.println("Recipe: " + recipeTitle + " has been successfully created!");
        drawDivider();
    }

    /**
     * Prints out recent added ingredient title.
     *
     * @param ingredientTitle The ingredient title.
     */
    public static void printAddIngredientsSuccess(String ingredientTitle) {
        System.out.println("Ingredient: " + ingredientTitle + " has been successfully created!");
        drawDivider();
    }

    public static void printUpdateIngredientsSuccess(String name, Double existingQuantity, Double newQuantity) {
        System.out.println("Ingredient: " + name + " has been successfully updated from: " + existingQuantity
                + " to: " + newQuantity);
        drawDivider();
    }

    public static void printAllIngredients(IngredientList ingredients) {
        if (ingredients.getIngredients().size() == 0) {
            System.out.println("The Inventory of Ingredients is empty now, please add something first!");
            return;
        }
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
            System.out.println("(Use the execute command after you've executed your recipe " +
                    "- this is to update your ingredients inventory)");
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
            System.out.println("Start your recipe again after getting the above ingredients!");
        }
        drawDivider();
    }

    public static void printAllRecipes(RecipeList recipes) {
        if (recipes.getRecipes().size() == 0) {
            System.out.println("Your Recipe List is empty right now, please create your own recipe first :D!");
            drawDivider();
        } else {
            System.out.println("Here's a list of your recipes!");
            recipes.listRecipeTitles();
            drawDivider();
        }
    }

    public static void printAllAvailableRecipes(RecipeList recipes) {
        if (recipes.getRecipes().size() == 0) {
            System.out.println("You don't have sufficient ingredients for any recipes at the moment :(");
        } else {
            System.out.println("Here are the recipes you can execute with your current ingredients!");
            recipes.listRecipeTitles();
            System.out.println("Use the execute command after executing any of these recipes!");
        }
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
        return("Valid ingredient units are: g, kg, ml, l, tsp, tbsp, cup, pc");
    }

    public static void printEditIngredientNameSuccess(String oldName, String newName) {
        System.out.println("You have successfully edited the ingredient name from: " + oldName +
                " to: " + newName);
        drawDivider();
    }

    public static void printEditIngredientQuantitySuccess(Double oldQuantity, Double newQuantity) {
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

    /**
     * Prints out IO exception message.
     *
     * @param exception IO exception.
     */
    public static void handleIOException(IOException exception) {
        System.out.println("Unable to save data");
        System.out.println(exception.getMessage());
    }

    /**
     * Prints out recent duplicated recipe.
     *
     * @param recipeTitle The recipe title.
     */
    public static void printDuplicatedRecipe(String recipeTitle) {
        drawDivider();
        System.out.println(recipeTitle + " has been duplicated.");
        drawDivider();
    }

    public static String readUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static void printPlanCommandIngredients(
            IngredientList allIngredientsNeeded, IngredientList missingIngredients, RecipeList recipes) {
        printAllRecipes(recipes);
        System.out.println("Here is a list of all ingredients you need: ");
        allIngredientsNeeded.listIngredients();
        drawDivider();
        System.out.println("Here are the ingredients you need to buy because your inventory is running low: ");
        missingIngredients.listIngredients();
    }

    public static void printValidIngredientExample() {
        System.out.println("Invalid Ingredient! Example of valid ingredient: i/Chicken,1,kg");
    }

    public static void printNegativeIngredientQuantity() {
        System.out.println("You cannot add an ingredient with negative quantity.");
    }

    public static void printIngredientDoesNotExist(String name) {
        System.out.println("You do not have any " + name + " to use.");
    }

    public static void printExecuteRecipeFail(String title) {
        System.out.println("You are missing some ingredients to execute " + title +
                "\nPlease use the [check] command to check what you are missing: \"check " + title +"\"");
        drawDivider();
    }

    public static void printExecuteRecipeSuccess(String title) {
        System.out.println("You have successfully executed " + title);
        drawDivider();
    }

    /**
     * Prints out all shortcuts in the list.
     *
     * @param shortcuts The shortcut list.
     */
    public static void printAllShortcuts(ShortcutList shortcuts) {
        Ui.drawDivider();
        System.out.println("Here's a list of your shortcuts!");
        shortcuts.listShortcuts();
        drawDivider();
    }

    /**
     * Prints out recent added shortcut.
     *
     * @param shortcut A shortcut.
     */
    public static void printAddShortcutSuccess(Shortcut shortcut, IngredientUnit unit) {
        Ui.drawDivider();
        System.out.println("Shortcut to add " + shortcut.getQuantity() + unit.getValue() + " of '"
                + shortcut.getIngredientName() + "' has been created!");
        Ui.drawDivider();
    }

    /**
     * Prints out deleted shortcut.
     *
     * @param ingredientName The name that shortcut refers to.
     */
    public static void printDeletedShortcut(String ingredientName) {
        Ui.drawDivider();
        System.out.println("Shortcut to add '" + ingredientName + "' has been deleted!");
        Ui.drawDivider();
    }

    /**
     * Prints out successful name edit on a shortcut.
     *
     * @param oldName The old shortcut name.
     * @param newName The new shortcut name.
     */
    public static void printEditShortcutName(String oldName, String newName) {
        Ui.drawDivider();
        System.out.println("Shortcut to ingredient '" + oldName + "' has changed to ingredient '" + newName + "'.");
        Ui.drawDivider();
    }

    /**
     * Prints out successful quantity edit on a shortcut.
     *
     * @param oldQuantity The old shortcut quantity.
     * @param newQuantity The new shortcut quantity.
     */
    public static void printEditShortcutQuantity(double oldQuantity, double newQuantity) {
        Ui.drawDivider();
        System.out.println("Shortcut's quantity has beed changed from " + oldQuantity + " to " + newQuantity + ".");
        Ui.drawDivider();
    }
}
