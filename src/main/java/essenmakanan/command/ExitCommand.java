package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients) {
        ui.bye();
    }
}
