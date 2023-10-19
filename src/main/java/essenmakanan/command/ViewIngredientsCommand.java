package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class ViewIngredientsCommand extends Command {

    public ViewIngredientsCommand() {
        super(false);
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients, String input) {
        Ui.printAllIngredients(ingredients);
    }
}
