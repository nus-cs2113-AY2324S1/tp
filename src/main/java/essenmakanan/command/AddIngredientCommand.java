package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class AddIngredientCommand extends Command{

    public AddIngredientCommand() {
        super(false);
    }

    public String parseIngredientTitle(String input) {
        return input.replace("i/", "");
    }

    @Override
    public void executeCommand(RecipeList recipes,IngredientList ingredients, String input) {
        String ingredientTitle = parseIngredientTitle(input);
        Ingredient newIngredient = new Ingredient(ingredientTitle);
        ingredients.addIngredient(newIngredient);
        ui.showRecentAddedIngredient(ingredientTitle);
    }
}

