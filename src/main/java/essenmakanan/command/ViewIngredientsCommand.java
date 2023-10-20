package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class ViewIngredientsCommand extends Command {

    public ViewIngredientsCommand() {
        super();
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients) {
        ui.printAllIngredients(ingredients);
    }
}
