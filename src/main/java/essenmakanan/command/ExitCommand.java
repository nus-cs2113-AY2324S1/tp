package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void executeCommand(RecipeList recipes, IngredientList ingredients) {
        ui.bye();
    }

    public static boolean isExitCommand(Command command) {
        return (command instanceof ExitCommand);
    }
}
