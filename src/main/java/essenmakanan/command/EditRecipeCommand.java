package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class EditRecipeCommand extends Command {

    private String editDetails;
    private RecipeList recipes;

    public EditRecipeCommand(String editDetails, RecipeList recipes) {
        super();
        this.editDetails = editDetails;
        this.recipes = recipes;
    }

    @Override
    public void executeCommand() {
        Recipe existingRecipe;

        this.editDetails = this.editDetails.replace("r/", "");

        String[] splitDetails = this.editDetails.split(" ");
        String recipeName = splitDetails[0];

        existingRecipe = recipes.getRecipeByName(recipeName);

        if (existingRecipe == null) {
            System.out.println("Recipe not found!");
        } else {
            try {
                recipes.editRecipe(existingRecipe, splitDetails);
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }

    }

}
