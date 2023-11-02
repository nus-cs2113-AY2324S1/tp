package essenmakanan.command;

import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class ViewRecipesCommand extends Command {

    private RecipeList recipes;

    public ViewRecipesCommand(RecipeList recipes) {
        super();
        this.recipes = recipes;
    }

    @Override
    public void executeCommand() {
        Ui.printAllRecipes(recipes);
    }

}
