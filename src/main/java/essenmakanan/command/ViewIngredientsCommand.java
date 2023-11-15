package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;
import essenmakanan.ui.Ui;

public class ViewIngredientsCommand extends Command {

    private IngredientList ingredients;

    public ViewIngredientsCommand(IngredientList ingredients) {
        super();
        this.ingredients = ingredients;
    }

    @Override
    public void executeCommand() {
        Ui.printAllIngredients(ingredients);
    }
}
