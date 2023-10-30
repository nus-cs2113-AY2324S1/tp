package essenmakanan.parser;

import essenmakanan.exception.EssenException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class RecipeParser {

    public static int getRecipeIndex(RecipeList recipes, String input)
            throws EssenOutOfRangeException {
        int index;
        input = input.replace("r/", "");

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = recipes.getIndexOfRecipe(input);
        }

        if (!recipes.recipeExist(index)) {
            throw new EssenOutOfRangeException();
        }

        return index;
    }

    public void parseRecipeCommand(RecipeList recipes, String command, String inputDetail)
            throws EssenException {
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
            throw new EssenException("Invalid command! Valid commands are: 'add', 'view'");
        }
    }

    public static String parseRecipeTitle(String toAdd) {
        return toAdd.replace("r/", "");
    }

}
