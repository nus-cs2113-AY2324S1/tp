package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class PlanCommand extends Command {
    public IngredientList missingIngredients;
    public IngredientList allIngredientsNeeded;

    private String input; //either id or name of recipe
    private IngredientList ingredients;
    private RecipeList recipes;
    private RecipeList allRecipes;

    public PlanCommand(IngredientList ingredients, RecipeList recipes, String input) {
        this.ingredients = ingredients;
        this.recipes = recipes;
        this.input = input;

        this.allRecipes = new RecipeList();
        this.allIngredientsNeeded = new IngredientList();
        this.missingIngredients = new IngredientList();
    }

    public void getMissingIngredients() {
        String ingredientName;
        Ingredient ingredientAvailable;
        Double missingQuantity = 0.0;
        IngredientUnit ingredientUnit;

        for (Ingredient ingredientNeeded : allIngredientsNeeded.getIngredients()) {
            ingredientName = ingredientNeeded.getName();
            ingredientUnit = ingredientNeeded.getUnit();
            if (ingredients.exist(ingredientName)) {
                ingredientAvailable = ingredients.getIngredient(ingredientName);
                assert ingredientUnit == ingredientAvailable.getUnit() :
                        "Unit must be standardised for the same ingredient";
                missingQuantity = IngredientParser.getInsufficientQuantity(ingredientNeeded, ingredientAvailable);
                if (missingQuantity != 0.0) {
                    Ingredient lackingIngredient = new Ingredient(ingredientName, missingQuantity, ingredientUnit);
                    missingIngredients.addIngredient(lackingIngredient);
                }
            } else {
                missingIngredients.addIngredient(ingredientNeeded);
            }
        }
    }

    @Override
    public void executeCommand() {
        try {
            RecipeParser.parsePlanCommandInput(input);

            String[] inputList = input.split(" ", 2);
            int numberOfRecipes = Integer.parseInt(inputList[0]);
            int[] recipeIdList = RecipeParser.getRecipeIdList(inputList[1]);

            allRecipes = RecipeParser.getRecipes(recipeIdList, recipes);
            allIngredientsNeeded = IngredientParser.getIngredientsFromRecipes(allRecipes);
            getMissingIngredients();

            Ui.printPlanCommandIngredients(allIngredientsNeeded, missingIngredients);
        } catch (EssenFormatException e) {
            e.handleException();
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
