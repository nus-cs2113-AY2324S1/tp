package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public abstract class Command {
    protected Ui ui;

    public Command() {
        ui = new Ui();
    }

    public abstract void executeCommand(RecipeList recipes, IngredientList ingredients);
}
