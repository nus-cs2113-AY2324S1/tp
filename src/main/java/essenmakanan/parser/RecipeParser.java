package essenmakanan.parser;

import essenmakanan.exception.EssenMakananException;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class RecipeParser {

    private Ui ui = new Ui();
    public void parseRecipeCommand(RecipeList recipes, String command, String inputDetail) throws EssenMakananException{
        switch(command) {
        case "add":
            String recipeName = inputDetail.substring(2);
            Recipe newRecipe = new Recipe(recipeName);

            recipes.addRecipe(newRecipe);
            System.out.println("Recipe: " + recipeName + " has been successfully created!");
            break;
        case "view":
            Ui.printAllRecipes(recipes);
            break;
        default:
            throw new EssenMakananException("Invalid command! Valid commands are: 'add', 'view'");
        }
    }
}
