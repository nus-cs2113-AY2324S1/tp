package essenmakanan.command;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.RecipeList;

public class DeleteRecipeCommand extends Command {
    private RecipeList recipes;
    private String recipe;

    public DeleteRecipeCommand(RecipeList recipes, String recipe) {
        this.recipes = recipes;
        this.recipe = recipe;
    }

    @Override
    public void executeCommand() {
        try {
            int recipeId = RecipeParser.getRecipeId(recipes, recipe);
            recipes.deleteRecipe(recipeId);
        } catch (EssenMakananOutOfRangeException e) {
            e.handleException();
        } catch (EssenMakananFormatException e) {
            e.handleException();
        }
    }
}
