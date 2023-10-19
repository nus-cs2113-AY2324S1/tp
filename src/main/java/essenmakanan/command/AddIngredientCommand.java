package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.RecipeList;

public class AddIngredientCommand extends Command{
    private String toAdd;

    public AddIngredientCommand(String toAdd) {
        super(false);
        this.toAdd = toAdd;
    }

    public String parseIngredientTitle(String input) {
        return input.replace("i/", "");
    }

    @Override
    public void executeCommand(RecipeList recipes,IngredientList ingredients) {
        String ingredientTitle = parseIngredientTitle(toAdd);
        Ingredient newIngredient = new Ingredient(ingredientTitle);
        ingredients.addIngredient(newIngredient);
        ui.showRecentAddedIngredient(ingredientTitle);
    }
}

