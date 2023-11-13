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

public class CheckRecipeCommand extends Command {

    public IngredientList missingIngredients;
    public IngredientList insufficientIngredients;
    public IngredientList diffUnitIngredients;
    private String input; //either id or name of recipe
    private IngredientList ingredients;
    private RecipeList recipes;
    private RecipeIngredientList recipeIngredients;

    public CheckRecipeCommand(String input, RecipeList recipes, IngredientList ingredients) {
        this.input = input;
        this.ingredients = ingredients;
        this.recipes = recipes;

        this.missingIngredients = new IngredientList();
        this.insufficientIngredients = new IngredientList();
        this.diffUnitIngredients = new IngredientList();
    }

    /**
     * To get the missing ingredient list (ingredients that are not in the inventory)
     *
     * @return IngredientList of missing ingredients
     */
    public IngredientList getMissingIngredients() {
        return this.missingIngredients;
    }

    /**
     * To get the list of insufficient ingredients (ingredients in the inventory that have insufficient quantity)
     *
     * @return IngredientList of insufficient ingredients
     */
    public IngredientList getInsufficientIngredients() {
        return this.insufficientIngredients;
    }


    /**
     * To check if two ingredients have the same unit
     *
     * @param ingredient1 first ingredient to compare
     * @param ingredient2 second ingredient to compare
     * @return boolean of if the units of both ingredients are the same
     */
    public static boolean sameUnit(Ingredient ingredient1, Ingredient ingredient2) {
        return ingredient1.getUnit().equals(ingredient2.getUnit());
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
                boolean isSameUnit = sameUnit(inventoryIngredient, recipeIngredient);

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

    /**
     * To check if all ingredients needed  for a recipe are in the ingredient inventory
     *
     * @param recipeIngredients that we want to check if it is available in our ingredient inventory
     * @return boolean of whether user has all ingredients
     */
    public boolean allIngredientsReady(RecipeIngredientList recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
        this.getIngredientsStillNeeded();
        boolean allEmpty = this.missingIngredients.isEmpty()
                && this.insufficientIngredients.isEmpty()
                && this.diffUnitIngredients.isEmpty();
        if (allEmpty) {
            return true;
        } else {
            return false;
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
        } catch (EssenOutOfRangeException | EssenFormatException e) {
            e.handleException();
        }
    }

}
