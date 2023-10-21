package essenmakanan.command;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class AddRecipeCommand extends Command {
    private String toAdd;
    private RecipeList recipes;

    public AddRecipeCommand(String toAdd, RecipeList recipes) {
        super();
        this.toAdd = toAdd;
        this.recipes = recipes;
    }

    // not sure if this is necessary or not. If yes, move to parser later
    public String parseRecipeTitle(String input) {
        return input.replace("r/", "");
    }

    @Override
    public void executeCommand() {
        String recipeTitle = parseRecipeTitle(toAdd);
        Recipe newRecipe = new Recipe(recipeTitle);
        recipes.addRecipe(newRecipe);
        ui.showRecentAddedRecipe(recipeTitle);
    }
}
