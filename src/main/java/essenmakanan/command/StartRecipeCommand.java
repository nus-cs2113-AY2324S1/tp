package essenmakanan.command;

import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class StartRecipeCommand extends Command {
    private String input; //either id or name of recipe
    private IngredientList ingredients;
    private RecipeList recipes;
    private RecipeIngredientList recipeIngredients;
    public StartRecipeCommand(String input, RecipeList recipes, IngredientList ingredients) {
        this.input = input;
        this.ingredients = ingredients;
        this.recipes = recipes;
    }

    private IngredientList getMissingIngredients() {
        IngredientList missingIngredients = new IngredientList();
        String recipeIngredientName;

        for (Ingredient recipeIngredient : recipeIngredients.getIngredients()) {
            recipeIngredientName = recipeIngredient.getName();
            if (!ingredients.ingredientExist(recipeIngredientName)) {
                missingIngredients.addIngredient(recipeIngredient);
            }
        }
        return missingIngredients;
    }

    @Override
    public void executeCommand() {
        try {
            int recipeIndex = RecipeParser.getRecipeIndex(recipes, input);
            Recipe recipe = recipes.getRecipe(recipeIndex);
            recipeIngredients = recipe.getRecipeIngredients();

            IngredientList missingIngredients = getMissingIngredients();
            Ui.printStartRecipeMessage(missingIngredients);
        } catch (EssenOutOfRangeException e) {
            e.handleException();
        }
    }
}
