package essenmakanan.command;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Executes tests related to duplicate recipe command.
 */
public class DuplicateRecipeCommandTest {
    private RecipeList recipes;
    private Recipe recipe;
    private Recipe duplicatedRecipe;

    /**
     * Sets up attributes before each test.
     */
    @BeforeEach
    public void setup() {
        recipes = new RecipeList();
        String recipeTitle = "sandwich";
        String[] ingredients = {"i/bread,2,pc", "i/meat,1,pc"};
        String[] steps = {"step1", "step2"};
        recipe = new Recipe(recipeTitle, steps, ingredients);
        recipes.addRecipe(recipe);
    }

    /**
     * Execute test for duplicating a recipe with index.
     */
    @Test
    public void duplicateRecipe_numericalIndex_expectSimilarRecipe() {
        DuplicateRecipeCommand command = new DuplicateRecipeCommand(recipes, "1");
        command.executeCommand();

        duplicatedRecipe = recipes.getRecipe(1);

        assertEquals(recipe.getTitle() + " (copy)", duplicatedRecipe.getTitle());

        assertEquals(recipe.getRecipeStepByIndex(0).getDescription( )
                , duplicatedRecipe.getRecipeStepByIndex(0).getDescription());
        assertEquals(recipe.getRecipeStepByIndex(1).getDescription()
                , duplicatedRecipe.getRecipeStepByIndex(1).getDescription());

        ArrayList<Ingredient> recipeIngredients = recipe.getRecipeIngredients().getIngredients();
        ArrayList<Ingredient> duplicatedIngredients = duplicatedRecipe.getRecipeIngredients().getIngredients();

        assertEquals(recipeIngredients.get(0).getName(), duplicatedIngredients.get(0).getName());
        assertEquals(recipeIngredients.get(0).getQuantity(), duplicatedIngredients.get(0).getQuantity());
        assertEquals(recipeIngredients.get(0).getUnit(), duplicatedIngredients.get(0).getUnit());

        assertEquals(recipeIngredients.get(1).getName(), duplicatedIngredients.get(1).getName());
        assertEquals(recipeIngredients.get(1).getQuantity(), duplicatedIngredients.get(1).getQuantity());
        assertEquals(recipeIngredients.get(1).getUnit(), duplicatedIngredients.get(1).getUnit());
    }

    /**
     * Execute test for duplicating a recipe with recipe name.
     */
    @Test
    public void duplicateRecipe_recipeName_expectSimilarRecipe() {
        DuplicateRecipeCommand command = new DuplicateRecipeCommand(recipes, "sandwich");
        command.executeCommand();

        duplicatedRecipe = recipes.getRecipe(1);

        assertEquals(recipe.getTitle() + " (copy)", duplicatedRecipe.getTitle());

        assertEquals(recipe.getRecipeStepByIndex(0).getDescription()
                , duplicatedRecipe.getRecipeStepByIndex(0).getDescription());
        assertEquals(recipe.getRecipeStepByIndex(1).getDescription()
                , duplicatedRecipe.getRecipeStepByIndex(1).getDescription());

        ArrayList<Ingredient> recipeIngredients = recipe.getRecipeIngredients().getIngredients();
        ArrayList<Ingredient> duplicatedIngredients = duplicatedRecipe.getRecipeIngredients().getIngredients();

        assertEquals(recipeIngredients.get(0).getName(), duplicatedIngredients.get(0).getName());
        assertEquals(recipeIngredients.get(0).getQuantity(), duplicatedIngredients.get(0).getQuantity());
        assertEquals(recipeIngredients.get(0).getUnit(), duplicatedIngredients.get(0).getUnit());

        assertEquals(recipeIngredients.get(1).getName(), duplicatedIngredients.get(1).getName());
        assertEquals(recipeIngredients.get(1).getQuantity(), duplicatedIngredients.get(1).getQuantity());
        assertEquals(recipeIngredients.get(1).getUnit(), duplicatedIngredients.get(1).getUnit());
    }
}
