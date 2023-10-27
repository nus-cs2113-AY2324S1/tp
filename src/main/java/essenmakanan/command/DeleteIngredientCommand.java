package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;

public class DeleteIngredientCommand extends Command {
    private IngredientList ingredients;
    private String ingredientInput;

    public DeleteIngredientCommand(IngredientList ingredients, String ingredientInput) {
        this.ingredients = ingredients;
        this.ingredientInput = ingredientInput;
    }

    @Override
    public void executeCommand() {
        try {
            int ingredientIndex = IngredientParser.getIngredientIndex(ingredients, ingredientInput);
            ingredients.deleteIngredient(ingredientIndex);
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
