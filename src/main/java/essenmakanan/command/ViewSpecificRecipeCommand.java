package essenmakanan.command;

import essenmakanan.exception.EssenMakananFormatException;
import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class ViewSpecificRecipeCommand extends Command {

    private String input;
    private RecipeList recipes;

    public ViewSpecificRecipeCommand(RecipeList recipes, String input) {
        super();
        this.recipes = recipes;
        this.input = input;
    }

    @Override
    public void executeCommand() {
        try {
            int recipeIndex = RecipeParser.getRecipeIndex(recipes, input);
            Ui.printSpecificRecipe(this.recipes, recipeIndex);
        } catch (EssenMakananFormatException | EssenMakananOutOfRangeException e) {
            e.getMessage();
        }
    }
}
