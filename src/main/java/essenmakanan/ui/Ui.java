package essenmakanan.ui;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.recipe.Recipe;

import java.util.ArrayList;

public class Ui {

    private final String DIVIDER = "--------------------------------------------";

    public void start() {
        drawDivider();
        System.out.println("Welcome to Essen Makanan!!! A one-stop place " +
                "to track the\ningredients in your kitchen and store " +
                "your favourite recipes");
        drawDivider();
    }

    public void bye() {
        System.out.println("Hope you had fun! See you again!");
    }

    public void functionSelect() {
        System.out.println("Enter the number for the function you want");
        System.out.println("1. Recipe");
        System.out.println("2. Ingredient");
        System.out.println("type bye to exit");

        System.out.println(DIVIDER);
    }

    public void drawDivider() {
        System.out.println(DIVIDER);
    }

    public void showCommands() {
        System.out.println("Here are the commands currently available:");
        System.out.println("- Add recipe. [add r/RECIPE_NAME]");
        System.out.println("- View all recipes. [view r]");
        System.out.println("- Add ingredient. [add i/INGREDIENT_NAME]");
        System.out.println("- View all ingredients. [view i]");
    }

    public void showRecentAddedRecipe(String recipeTitle) {
        System.out.println("Recipe: " + recipeTitle + " has been successfully created!");
    }

    public void showRecentAddedIngredient(String ingredientTitle) {
        System.out.println("Ingredient: " + ingredientTitle + " has been successfully created!");
    }

    public void printAllRecipes(ArrayList<Recipe> recipes) {
        int count = 1;
        for (Recipe recipe : recipes) {
            assert recipes.get(count - 1).getTitle().equals(recipe.getTitle())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + recipe);
            count++;
        }
    }

    public void printAllIngredients(ArrayList<Ingredient> ingredients) {
        int ingredientCount = 0;

        for (Ingredient ingredient : ingredients) {
            ingredientCount++;
            System.out.println(ingredientCount + ". " + ingredient.getName());
        }
    }
}
