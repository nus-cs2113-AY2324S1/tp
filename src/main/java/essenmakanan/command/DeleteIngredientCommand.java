package essenmakanan.command;

import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;
import essenmakanan.recipe.RecipeList;

public class DeleteIngredientCommand extends Command {
    private IngredientList ingredients;
    private String ingredient;

    public DeleteIngredientCommand(IngredientList ingredients, String ingredient) {
        this.ingredients = ingredients;
        this.ingredient = ingredient;
    }

    @Override
    public void executeCommand() {
        try {
            int ingredientId = IngredientParser.getIngredientId(ingredients, ingredient);
            ingredients.deleteIngredient(ingredientId);
        } catch (EssenMakananOutOfRangeException e) {
            e.handleException();
        }
    }
}
