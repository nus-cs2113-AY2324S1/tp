package essenmakanan.command;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.RecipeList;

public class DeleteRecipeCommand extends Command {
    private RecipeList recipes;
    private String recipeInput;

    public DeleteRecipeCommand(RecipeList recipes, String recipeInput) {
        this.recipes = recipes;
        this.recipeInput = recipeInput;
    }

    @Override
    public void executeCommand() {
        try {
            int recipeIndex = RecipeParser.getRecipeIndex(recipes, recipeInput);
            recipes.deleteRecipe(recipeIndex);
        } catch (EssenMakananOutOfRangeException e) {
            e.handleException();
        } catch (EssenMakananFormatException e) {
            e.handleException();
        }
    }
}
