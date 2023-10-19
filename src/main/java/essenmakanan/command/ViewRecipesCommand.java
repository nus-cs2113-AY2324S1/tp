package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class ViewRecipesCommand extends Command {

    public ViewRecipesCommand() {
        super(false);
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients) {
        ui.printAllRecipes(recipes);
    }

}
