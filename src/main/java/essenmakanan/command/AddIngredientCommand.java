package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;

public class AddIngredientCommand extends IngredientCommand{

    public AddIngredientCommand() {
        super();
    }

    public String parseIngredientTitle(String input) {
        return input.replace("i/", "");
    }

    @Override
    public void executeCommand(IngredientList ingredients, String input) {
        String ingredientTitle = parseIngredientTitle(input);
        Ingredient newIngredient = new Ingredient(ingredientTitle);
        ingredients.addIngredient(newIngredient);
        ui.showRecentAddedIngredient(ingredientTitle);
    }
}

