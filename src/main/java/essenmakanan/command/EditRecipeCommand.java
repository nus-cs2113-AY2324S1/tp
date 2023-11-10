package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

import java.util.ArrayList;
import java.util.List;

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

        List<String> detailList = new ArrayList();
        for (String detail : splitDetails) {
            if (detail.length() > 0) {
                detailList.add(detail);
            }
        }
        splitDetails = detailList.toArray(new String[0]);

        assert splitDetails.length > 0 : "Details not provided"; // error not thrown by exceptions

        String recipeName = splitDetails[0];
        existingRecipe = recipes.getRecipe(recipeName);

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
