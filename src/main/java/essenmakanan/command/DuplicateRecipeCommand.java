package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

/**
 * Represents a duplicate recipe command.
 */
public class DuplicateRecipeCommand extends Command {

    private RecipeList recipes;
    private String toDuplicate;

    /**
     * Creates a duplicate recipe command.
     *
     * @param recipes The recipe list.
     * @param toDuplicate The given input.
     */
    public DuplicateRecipeCommand(RecipeList recipes, String toDuplicate) {
        super();
        this.recipes = recipes;
        this.toDuplicate = toDuplicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        try {
            int index = RecipeParser.getRecipeIndex(recipes, toDuplicate);
            Recipe recipe = recipes.getRecipe(index);
            Recipe copiedRecipe = new Recipe(recipe.getTitle() + " (copy)", recipe.getRecipeSteps()
                    , recipe.getRecipeIngredients());
            recipes.addRecipe(copiedRecipe);
            Ui.printDuplicatedRecipe(recipe.getTitle());
        } catch(EssenOutOfRangeException | EssenFormatException exception) {
            exception.handleException();
        }
    }
}
