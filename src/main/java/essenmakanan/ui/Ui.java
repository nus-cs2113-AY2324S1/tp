package essenmakanan.ui;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class Ui {

    public void start() {
        drawDivider();
        System.out.println("Welcome to Essen Makanan!!! A one-stop place " +
                "to track the\ningredients in your kitchen and store " +
                "your favourite recipes");
        System.out.println("To get started, type [help] for list of commands");
        drawDivider();
    }

    public void bye() {
        drawDivider();
        System.out.println("Hope you had fun! See you again!");
        drawDivider();
    }

    public static void drawDivider() {
        String divider = "--------------------------------------------";
        System.out.println(divider);
    }

    public static void showCommands() {
        System.out.println("Here are the commands currently available:");
        System.out.println("- Add recipe. [add r/RECIPE_NAME]");
        System.out.println("- View all recipes. [view r]");
        System.out.println("- Add ingredient. [add i/INGREDIENT_NAME,QUANTITY,UNIT [i/...] ]");
        System.out.println("\t" + validIngredientUnits());
        System.out.println("- View all ingredients. [view i]");
        System.out.println("- Edit an ingredient. [edit i/INGREDIENT_NAME [n/NEW_NAME] [q/NEW_QUANTITY] [u/NEW_UNIT]");
        System.out.println("- Exit application [exit]");
        drawDivider();
    }

    public void printAddRecipeSuccess(String recipeTitle) {
        System.out.println("Recipe: " + recipeTitle + " has been successfully created!");
        drawDivider();
    }

    public static void printAddIngredientsSuccess(String ingredientTitle) {
        System.out.println("Ingredient: " + ingredientTitle + " has been successfully created!");
        drawDivider();
    }

    public void printAllRecipes(RecipeList recipes) {
        int count = 1;
        for (Recipe recipe : recipes.getRecipes()) {
            assert recipes.getRecipeByIndex(count - 1).getTitle().equals(recipe.getTitle())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + recipe);
            count++;
        }
        drawDivider();
    }

    public static void printAllIngredients(IngredientList ingredients) {
        ingredients.listIngredients();
        drawDivider();
    }

    public static void printDeleteIngredientsSuccess(String ingredientName) {
        System.out.println("You have deleted the following ingredient: " + ingredientName);
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
}
