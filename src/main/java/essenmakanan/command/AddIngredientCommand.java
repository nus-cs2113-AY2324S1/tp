package essenmakanan.command;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;
import essenmakanan.ui.Ui;

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
        String[] allIngredients = toAdd.split("i/");

        for (String ingredient : allIngredients) {
            if (ingredient.isEmpty()) {
                continue;
            }

            ingredient = ingredient.replace("i/", "");
            Ingredient newIngredient = null;
            try {
                newIngredient = IngredientParser.parseIngredient(ingredients, ingredient);
                ingredients.addIngredient(newIngredient);
                Ui.printAddIngredientsSuccess(newIngredient.getName());
            } catch (EssenMakananFormatException e) {
                e.handleException();
            }
        }

    }
}

