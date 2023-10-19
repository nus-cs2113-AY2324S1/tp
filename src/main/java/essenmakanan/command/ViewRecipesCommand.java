package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class ViewRecipesCommand extends Command {

    public ViewRecipesCommand() {
        super(false);
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients, String input) {
        Ui.printAllRecipes(recipes);
    }
}
