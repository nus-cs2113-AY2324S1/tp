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

public class PlanRecipesCommand extends Command {
    public IngredientList missingIngredients;
    public IngredientList allIngredientsNeeded;

    private String input; //either id or name of recipe
    private IngredientList ingredients;
    private RecipeList recipes;
    private RecipeList allRecipes;

    public PlanRecipesCommand(IngredientList ingredients, RecipeList recipes, String input) {
        this.ingredients = ingredients;
        this.recipes = recipes;
        this.input = input;

        this.allRecipes = new RecipeList();
        this.allIngredientsNeeded = new IngredientList();
        this.missingIngredients = new IngredientList();
    }

    public IngredientList getMissingIngredients() {
        return this.missingIngredients;
    }

    public IngredientList getAllIngredientsNeeded() {
        return this.allIngredientsNeeded;
    }

    /**
     * Compare ingredients in inventory and ingredients needed for all
     */
    public void setMissingIngredients() {
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
            int[] recipeIdList = RecipeParser.getRecipeIdList(inputList[1]);

            this.allRecipes = RecipeParser.getRecipes(recipeIdList, recipes);
            this.allIngredientsNeeded = IngredientParser.getIngredientsFromRecipes(allRecipes);
            setMissingIngredients();

            Ui.printPlanCommandIngredients(allIngredientsNeeded, missingIngredients, allRecipes);
        } catch (EssenFormatException | EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
