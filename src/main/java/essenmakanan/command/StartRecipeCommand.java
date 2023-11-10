package essenmakanan.command;

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

public class StartRecipeCommand extends Command {

    public IngredientList missingIngredients;
    public IngredientList insufficientIngredients;
    public IngredientList diffUnitIngredients;
    private String input; //either id or name of recipe
    private IngredientList ingredients;
    private RecipeList recipes;
    private RecipeIngredientList recipeIngredients;

    public StartRecipeCommand(String input, RecipeList recipes, IngredientList ingredients) {
        this.input = input;
        this.ingredients = ingredients;
        this.recipes = recipes;

        this.missingIngredients = new IngredientList();
        this.insufficientIngredients = new IngredientList();
        this.diffUnitIngredients = new IngredientList();
    }

    public IngredientList getMissingIngredients() {
        return this.missingIngredients;
    }

    public IngredientList getInsufficientIngredients() {
        return this.insufficientIngredients;
    }

    /**
     * Compare ingredients in a recipe and ingredients in the inventory.
     * Update missingIngredients, diffUnitIngredients and insufficientIngredients accordingly.
     */
    private void getIngredientsStillNeeded() {
        String recipeIngredientName;
        IngredientUnit recipeIngredientUnit;

        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            recipeIngredientName = recipeIngredient.getName();
            recipeIngredientUnit = recipeIngredient.getUnit();
            if (!ingredients.exist(recipeIngredientName)) {
                missingIngredients.addIngredient(recipeIngredient);
            } else {
                Ingredient inventoryIngredient = ingredients.getIngredient(recipeIngredientName);
                boolean isSameUnit = IngredientParser.sameUnit(inventoryIngredient, recipeIngredient);

                if (!isSameUnit) {
                    diffUnitIngredients.addIngredient(recipeIngredient);
                }

                Double missingQuantity = 0.0;
                if (isSameUnit) {
                    missingQuantity = IngredientParser.getInsufficientQuantity(recipeIngredient, inventoryIngredient);
                }
                if (isSameUnit && !missingQuantity.equals(0.0)) {
                    Ingredient lackingIngredient = new Ingredient(
                            recipeIngredientName, missingQuantity, recipeIngredientUnit);
                    insufficientIngredients.addIngredient(lackingIngredient);
                }
            }

        }
    }

    @Override
    public void executeCommand() {
        try {
            int recipeIndex = RecipeParser.getRecipeIndex(recipes, input);
            Recipe recipe = recipes.getRecipe(recipeIndex);

            String recipeTitle = recipe.getTitle();
            recipeIngredients = recipe.getRecipeIngredients();

            getIngredientsStillNeeded();

            Ui.printStartRecipeMessage(missingIngredients, insufficientIngredients, diffUnitIngredients, recipeTitle);
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
