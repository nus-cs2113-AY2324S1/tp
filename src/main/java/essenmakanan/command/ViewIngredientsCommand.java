package essenmakanan.command;

import essenmakanan.ingredient.IngredientList;

public class ViewIngredientsCommand extends Command {

    private IngredientList ingredients;

    public ViewIngredientsCommand(IngredientList ingredients) {
        super();
        this.ingredients = ingredients;
    }

    @Override
    public void executeCommand() {
        ui.printAllIngredients(ingredients);
    }
}
