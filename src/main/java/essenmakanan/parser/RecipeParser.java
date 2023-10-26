package essenmakanan.parser;

import essenmakanan.exception.EssenMakananException;
import essenmakanan.exception.EssenMakananOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class RecipeParser {

    public static int getRecipeId(RecipeList recipes, String input) throws EssenMakananOutOfRangeException {
        int index;

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input);
        } else {
            index = recipes.indexOfRecipeByName(input);
        }

        if (index == -1) {
            throw new EssenMakananOutOfRangeException();
        }

        return index;
    }

    public void parseRecipeCommand(RecipeList recipes, String command, String inputDetail)
            throws EssenMakananException {
        Ui ui = new Ui();
        switch(command) {
        case "add":
            String recipeName = inputDetail.substring(2);
            assert !recipeName.contains("r/") : "Recipe name should not contain key characters";

            Recipe newRecipe = new Recipe(recipeName);
            recipes.addRecipe(newRecipe);

            ui.printAddRecipeSuccess(recipeName);
            break;
        case "view":
            ui.printAllRecipes(recipes);
            break;
        default:
            throw new EssenMakananException("Invalid command! Valid commands are: 'add', 'view'");
        }
    }

    public static String parseRecipeTitle(String toAdd) {
        return toAdd.replace("r/", "");
    }

}
