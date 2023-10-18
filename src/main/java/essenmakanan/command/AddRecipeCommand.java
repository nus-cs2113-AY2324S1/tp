package essenmakanan.command;

import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class AddRecipeCommand extends RecipeCommand {

    public AddRecipeCommand() {
        super();
    }

    // not sure if this is necessary or not. If yes, move to parser later
    public String parseRecipeTitle(String input) {
        return input.replace("r/", "");
    }

    @Override
    public void executeCommand(RecipeList recipes, String input) {
        String recipeTitle = parseRecipeTitle(input);
        Recipe newRecipe = new Recipe(recipeTitle);
        recipes.addRecipe(newRecipe);
        ui.showRecentAddedRecipe(recipeTitle);
    }
}
