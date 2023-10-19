package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(false);
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients) {
        ui.showCommands();
    }
}
