package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
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
            if (recipes.getRecipes().size() == 0) {
                System.out.println("You haven't added any recipes yet, please add some recipes first!");
                return;
            }
            int recipeIndex = RecipeParser.getRecipeIndex(recipes, input);
            Ui.printSpecificRecipe(this.recipes, recipeIndex);
        } catch (EssenOutOfRangeException | EssenFormatException e) {
            e.getMessage();
        }
    }
}
