package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
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

    /**
     * Get an IngredientList of missing ingredients from the inventory that are needed
     * to plan for recipes
     *
     * @return IngredientList of missing ingredients
     */
    public IngredientList getMissingIngredients() {
        return this.missingIngredients;
    }

    /**
     * Get an IngredientList of all ingredients needed for all the recipes indicated
     *
     * @return IngredientList of all ingredients needed
     */
    public IngredientList getAllIngredientsNeeded() {
        return this.allIngredientsNeeded;
    }

    /**
     * Compare ingredients in inventory and ingredients needed for all recipes indicated.
     * Then, method updates IngredientList of allIngredientsNeeded and missingIngredients respectively.
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

    /**
     * To get an Ingredient List of all ingredients needed for all recipes in the recipe list
     *
     * @param recipes is a RecipeList of all recipes the user wants to process
     * @return all ingredients in the list of recipes
     */
    public static IngredientList getIngredientsFromRecipes(RecipeList recipes) {
        IngredientList allIngredients = new IngredientList();
        RecipeIngredientList recipeIngredients;

        for (Recipe recipe : recipes.getRecipes()) {
            recipeIngredients = recipe.getRecipeIngredients();
            for (Ingredient ingredient : recipeIngredients.getIngredients()) {
                allIngredients.addIngredient(ingredient);
            }
        }
        return allIngredients;
    }

    @Override
    public void executeCommand() {
        try {
            RecipeParser.parsePlanCommandInput(input);

            String[] inputList = input.split(" ", 2);
            int[] recipeIdList = RecipeParser.getRecipeIdList(inputList[1]);

            this.allRecipes = RecipeParser.getRecipes(recipeIdList, recipes);
            this.allIngredientsNeeded = getIngredientsFromRecipes(allRecipes);
            setMissingIngredients();

            Ui.printPlanCommandIngredients(allIngredientsNeeded, missingIngredients, allRecipes);
        } catch (EssenFormatException | EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
