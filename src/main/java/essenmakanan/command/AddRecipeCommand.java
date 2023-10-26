package essenmakanan.command;

import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class AddRecipeCommand extends Command {
    private String toAdd;
    private RecipeList recipes;

    public AddRecipeCommand(String toAdd, RecipeList recipes) {
        super();
        this.toAdd = toAdd;
        this.recipes = recipes;
    }

    @Override
    public void executeCommand() {
        if (toAdd.contains("r/") & toAdd.contains("s/")) {
            String[] allToAdd = toAdd.split("s/");
            String recipeTitle = RecipeParser.parseRecipeTitle(allToAdd[0].trim());
            String[] steps = new String[allToAdd.length-1];
            for (int i = 1; i < allToAdd.length; i++) {
                steps[i-1] = allToAdd[i].trim();
            }
            Recipe newRecipe = new Recipe(recipeTitle, steps);
            recipes.addRecipe(newRecipe);
            ui.printAddRecipeSuccess(recipeTitle);
        } else {
            String recipeTitle = RecipeParser.parseRecipeTitle(toAdd);
            Recipe newRecipe = new Recipe(recipeTitle);
            recipes.addRecipe(newRecipe);
            ui.printAddRecipeSuccess(recipeTitle);
        }
    }

}
