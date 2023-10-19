package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public abstract class Command {
    protected Ui ui;
    protected boolean isExit;


    public Command(boolean isExit) {
        this.isExit = isExit;
        ui = new Ui();
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void executeCommand(RecipeList recipes, IngredientList ingredients);
}
