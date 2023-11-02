package essenmakanan.command;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class FilterRecipesCommand extends Command {
    private String input;
    private RecipeList recipes;
    private RecipeIngredientList recipeingredientList;

    public FilterRecipesCommand(String input, RecipeList recipes) {
        this.input = input;
        this.recipes = recipes;
    }

    private RecipeList filterRecipes(String ingredientName) {
        RecipeList filteredRecipes = new RecipeList();

        for (Recipe recipe : recipes.getRecipes()) {
            recipeingredientList = recipe.getRecipeIngredients();
            if (recipeingredientList.ingredientExist(ingredientName)) {
                filteredRecipes.addRecipe(recipe);
            }
        }
        return filteredRecipes;
    }


    @Override
    public void executeCommand() {
        boolean isFirst = true;
        for (String ingredientName : input.split("i/")) {
            if (!isFirst) {
                ingredientName = ingredientName.trim();
                RecipeList filteredRecipes = filterRecipes(ingredientName);
                Ui.printFilteredRecipes(filteredRecipes, ingredientName);
            }
            isFirst = false;
        }
    }
}
