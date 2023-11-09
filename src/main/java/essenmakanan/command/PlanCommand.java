package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;

public class PlanCommand extends Command {

    private String input; //either id or name of recipe
    private IngredientList ingredients;
    private RecipeList recipes;
    private RecipeList allRecipes;
    private IngredientList allIngredientsNeeded;
    private IngredientList missingIngredients;

    public PlanCommand(IngredientList ingredients, RecipeList recipes, String input) {
        this.ingredients = ingredients;
        this.recipes = recipes;
        this.input = input;

        this.allRecipes = new RecipeList();
        this.allIngredientsNeeded = new IngredientList();
        this.missingIngredients = new IngredientList();
    }

    @Override
    public void executeCommand() {
        try {
            RecipeParser.parsePlanCommandInput(input);

            String[] inputList = input.split(" ", 2);
            int numberOfRecipes = Integer.parseInt(inputList[0]);
            int[] recipeIdList = RecipeParser.getRecipeIdList(inputList[1]);

            allRecipes = RecipeParser.getRecipes(recipeIdList, recipes);

            //check all ingredients needed
            //allIngredientsNeeded = IngredientParser.getIngredientsFromRecipes(allRecipes);
            //check for missing ingredients
        } catch (EssenFormatException e) {
            e.handleException();
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
