package essenmakanan.ui;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class Ui {

    private final String DIVIDER = "--------------------------------------------";

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

    public void drawDivider() {
        System.out.println(DIVIDER);
    }

    public void showCommands() {
        drawDivider();
        System.out.println("Here are the commands currently available:");
        System.out.println("- Add recipe. [add r/RECIPE_NAME]");
        System.out.println("- View all recipes. [view r]");
        System.out.println("- Add ingredient. [add i/INGREDIENT_NAME]");
        System.out.println("- View all ingredients. [view i]");
        System.out.println("- Exit application [exit]");
        drawDivider();
    }

    public void showRecentAddedRecipe(String recipeTitle) {
        drawDivider();
        System.out.println("Recipe: " + recipeTitle + " has been successfully created!");
        drawDivider();
    }

    public void showRecentAddedIngredient(String ingredientTitle) {
        drawDivider();
        System.out.println("Ingredient: " + ingredientTitle + " has been successfully created!");
        drawDivider();
    }

    public void printAllRecipes(RecipeList recipes) {
        drawDivider();
        int count = 1;
        for (Recipe recipe : recipes.getRecipes()) {
            assert recipes.getRecipe(count - 1).getTitle().equals(recipe.getTitle())
                    : "Title is not matching with the current index";

            System.out.println(count + ". " + recipe);
            count++;
        }
        drawDivider();
    }

    public void printAllIngredients(IngredientList ingredients) {
        ingredients.listIngredients();
    }

    public void printAddRecipeSuccess(String recipeName) {
        System.out.println("Recipe: " + recipeName + " has been successfully created!");
    }

    public void printAddIngredientsSuccess(String ingredientName) {
        System.out.println("Ingredient: " + ingredientName + " has been successfully added!");
    }
}
