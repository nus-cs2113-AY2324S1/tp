package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
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
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
