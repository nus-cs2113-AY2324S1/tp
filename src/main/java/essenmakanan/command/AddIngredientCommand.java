package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;

public class AddIngredientCommand extends Command{
    private String toAdd;
    private IngredientList ingredients;

    public AddIngredientCommand(String toAdd, IngredientList ingredients) {
        super();
        this.toAdd = toAdd;
        this.ingredients = ingredients;
    }

    @Override
    public void executeCommand() {
        String ingredientTitle = IngredientParser.parseIngredientTitle(toAdd);
        Ingredient newIngredient = new Ingredient(ingredientTitle);
        ingredients.addIngredient(newIngredient);
        ui.showRecentAddedIngredient(ingredientTitle);
    }
}

