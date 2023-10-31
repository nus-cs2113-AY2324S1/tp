package essenmakanan.command;

import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;

public class ViewSpecificIngredientCommand extends Command {
    private IngredientList ingredients;
    private String input;
    public ViewSpecificIngredientCommand(IngredientList ingredients, String input) {
        this.ingredients = ingredients;
        this.input = input;
    }

    @Override
    public void executeCommand() {
        try {
            int ingredientIndex = IngredientParser.getIngredientIndex(ingredients, input);
            Ingredient ingredient = ingredients.getIngredient(ingredientIndex);
            System.out.println(ingredient);
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
