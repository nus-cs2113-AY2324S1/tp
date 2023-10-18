package essenmakanan.command;

import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public abstract class RecipeCommand extends Command {
    protected Ui ui;

    public RecipeCommand() {
        super(false);
        ui = new Ui();
    }

    public abstract void executeCommand(RecipeList recipes, String input);
}
