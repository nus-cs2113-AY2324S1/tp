package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class ViewAllAvailableRecipesCommand extends Command {
    private IngredientList ingredients;
    private RecipeList recipes;
    private String input;
    private CheckRecipeCommand checkRecipeCommand;
    private RecipeList allAvailableRecipes;

    public ViewAllAvailableRecipesCommand(RecipeList recipes, IngredientList ingredients, String input) {
        this.ingredients = ingredients;
        this.recipes = recipes;
        this.input = input;
        this.allAvailableRecipes = new RecipeList();
    }


    @Override
    public void executeCommand() {
        for (Recipe recipe : recipes.getRecipes()) {
            String recipeTitleToStart = recipe.getTitle();
            checkRecipeCommand = new CheckRecipeCommand(recipeTitleToStart, recipes, ingredients);

            RecipeIngredientList recipeIngredients = recipe.getRecipeIngredients();

            if (checkRecipeCommand.allIngredientsReady(recipeIngredients)) {
                allAvailableRecipes.addRecipe(recipe);
            }
        }
        Ui.printAllAvailableRecipes(allAvailableRecipes);
    }
}
