package essenmakanan.ui;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

import java.util.ArrayList;

public class Ui {

    private static final String DIVIDER = "--------------------------------------------";
    public static void start() {

        System.out.println("Welcome to Essen Makanan!!! A one-stop place " +
                "to track the\n ingredients in your kitchen and store " +
                "your favourite recipes");

        System.out.println(DIVIDER);
    }

    public static void bye() {
        System.out.println("Hope you had fun! See you again!");
    }

    public static void functionSelect() {
        System.out.println("Enter the number for the function you want");
        System.out.println("1. Recipe");
        System.out.println("2. Ingredient");
        System.out.println("type bye to exit");

        System.out.println(DIVIDER);
    }

    public static void drawDivider() {
        System.out.println(DIVIDER);
    }

    public static void showRecipeFunctions() {
        System.out.println("- Add recipe. [add r/RECIPE_NAME]");
        System.out.println("- Delete recipe. [delete r/RECIPE_ID]");
        System.out.println("- View all recipes. [view r]");
    }

    public static void showIngredientFunctions() {
        System.out.println("- Add ingredient. [add i/INGREDIENT_NAME]");
        System.out.println("- Delete ingredient. [delete i/INGREDIENT_ID]");
        System.out.println("- View all ingredients. [view i]");
    }

    public static void showRecentAddedRecipe(String recipeTitle) {
        System.out.println("Recipe: " + recipeTitle + " has been successfully created!");
    }

    public static void showRecentAddedIngredient(String ingredientTitle) {
        System.out.println("Ingredient: " + ingredientTitle + " has been successfully created!");
    }

    public static void printAllRecipes(RecipeList recipes) {
        recipes.listRecipes();
    }

    public static void printAllIngredients(IngredientList ingredients) {
        ingredients.listIngredients();
    }
}
