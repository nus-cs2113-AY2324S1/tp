package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;
import essenmakanan.ui.Ui;

public class UseIngredientCommand extends Command {
    private String toUse;
    private IngredientList ingredients;

    public UseIngredientCommand(IngredientList ingredients, String toUse) {
        super();
        this.toUse = toUse;
        this.ingredients = ingredients;
    }

    /**
     * Iterate through the ingredients to use and update the quantity of the ingredients in the list
     */
    @Override
    public void executeCommand() {
        String[] allIngredients = toUse.split("i/");

        for (String ingredient : allIngredients) {
            if (ingredient.isEmpty()) {
                continue;
            }

            updateIngredientQuantity(ingredient);

        }
    }

    /**
     * Update the quantity of the ingredient in the list
     *
     * @param ingredientString String containing ingredient name, quantity and unit
     */
    public void updateIngredientQuantity(String ingredientString) {
        try {
            Ingredient newIngredient = IngredientParser.parseIngredient(ingredientString);

            // you cannot use negative quantity of ingredients
            if (newIngredient.getQuantity() < 0) {
                Ui.printNegativeIngredientQuantity();
                return;
            }

            if (this.ingredients.exist(newIngredient.getName())) {
                // if ingredient already exists, update the quantity by changing quantity to negative
                newIngredient.setQuantity(-newIngredient.getQuantity());
                this.ingredients.updateIngredient(newIngredient);
            } else {
                // do nothing
                Ui.printIngredientDoesNotExist(newIngredient.getName());
            }
        } catch (EssenFormatException e) {
            e.handleException();
        }
    }
}
