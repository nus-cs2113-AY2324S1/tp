package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.ui.Ui;

public abstract class IngredientCommand extends Command{
    protected Ui ui;

    public IngredientCommand() {
        super(false);
        ui = new Ui();
    }

    public abstract void executeCommand(IngredientList ingredients, String input);
}
