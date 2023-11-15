package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenNullInputException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.RecipeParser;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeIngredientList;
import essenmakanan.recipe.RecipeList;
import essenmakanan.ui.Ui;

public class ExecuteRecipeCommand extends Command {
    private String recipeTitleToStart;
    private RecipeList recipes;
    private IngredientList allIngredientsList;
    private CheckRecipeCommand checkRecipeCommand;
    private IngredientList recipeIngredients;


    public ExecuteRecipeCommand(IngredientList allIngredientsList, RecipeList recipes, String recipeTitleToStart) {
        super();
        this.recipeTitleToStart = recipeTitleToStart;
        this.recipes = recipes;
        this.allIngredientsList = allIngredientsList;
        this.checkRecipeCommand = new CheckRecipeCommand(recipeTitleToStart, recipes, allIngredientsList);
    }

    /**
     * Check if structure of command is valid and execute recipe by decreasing quantity of ingredients used for recipe
     */
    @Override
    public void executeCommand() {
        Recipe recipe = null;
        try {
            recipe = this.getRecipe();
            if (recipe == null) {
                return;
            }
        } catch (EssenNullInputException e) {
            e.handleException();
        }

        RecipeIngredientList recipeIngredients = recipe.getRecipeIngredients();
        if (checkRecipeCommand.allIngredientsReady(recipeIngredients)){
            // if recipe is null, program should have terminated before this
            assert recipe != null : "Recipe should not be null";
            updateAllIngredientQuantity(recipeIngredients);
            Ui.printExecuteRecipeSuccess(recipe.getTitle());
        } else {
            Ui.printExecuteRecipeFail(recipe.getTitle());
        }

    }

    /**
     * Get recipe from recipe list based on title
     *
     * @return Recipe object
     * @throws EssenNullInputException if recipe title is empty
     */
    public Recipe getRecipe() throws EssenNullInputException {
        try {
            if (recipeTitleToStart.isEmpty()) {
                System.out.println("Recipe Title is empty! Please enter valid title after \"execute\"");
                throw new EssenNullInputException();
            }

            int recipeIndex = RecipeParser.getRecipeIndex(recipes, recipeTitleToStart);
            Recipe recipe = recipes.getRecipe(recipeIndex);
            return recipe;
        } catch (EssenOutOfRangeException | EssenFormatException e) {
            e.handleException();
        }
        return null;
    }

    /**
     * Update quantity of ingredients used for recipe by decreasing the quantity used.
     *
     * @param recipeIngredients
     */
    public void updateAllIngredientQuantity(RecipeIngredientList recipeIngredients) {
        for (Ingredient ingredient: recipeIngredients.getIngredients()) {
            try {
                // Decrease ingredient quantity in allIngredientsList
                Ingredient tempIngredient = new Ingredient(ingredient.getName(), ingredient.getQuantity(),
                                                            ingredient.getUnit());
                tempIngredient.setQuantity(-tempIngredient.getQuantity());
                allIngredientsList.updateIngredient(tempIngredient);
            } catch (EssenFormatException e) {
                e.handleException();
            }
        }
    }

}
