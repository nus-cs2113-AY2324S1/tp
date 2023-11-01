package essenmakanan.storage;

import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class StorageTest {

    private static String DATA_INVALID_PATH = "src/test/data/invalid.txt";
    private static String DATA_RECIPE_TEST_PATH = "src/test/data/recipes.txt";
    private static String DATA_INGREDIENT_TEST_PATH = "src/test/data/ingredients.txt";

    @Test
    public void accessIngredientDatabase_invalidPath_throwsEssenFileNotFoundException() {
        IngredientStorage ingredientStorage = new IngredientStorage(DATA_INVALID_PATH);
        assertThrows(FileNotFoundException.class, ingredientStorage::restoreSavedData);
    }

    @Test
    public void accessRecipeDatabase_invalidPath_throwsEssenFileNotFoundException() {
        RecipeStorage recipeStorage = new RecipeStorage(DATA_INVALID_PATH);
        assertThrows(FileNotFoundException.class, recipeStorage::restoreSavedData);
    }

    @Test
    public void restoreSavedIngredients_storedIngredients_returnsFilledIngredientList() throws Exception {
        IngredientStorage ingredientStorage = new IngredientStorage(DATA_INGREDIENT_TEST_PATH);
        IngredientList ingredients = new IngredientList(ingredientStorage.restoreSavedData());

        assertEquals("bread", ingredients.getIngredients().get(0).getName());
        assertEquals("2", ingredients.getIngredients().get(0).getQuantity());
        assertEquals("g", ingredients.getIngredients().get(0).getUnit().getValue());

        assertEquals("cheese", ingredients.getIngredients().get(1).getName());
        assertEquals("10", ingredients.getIngredients().get(1).getQuantity());
        assertEquals("pc", ingredients.getIngredients().get(1).getUnit().getValue());

        assertEquals("carrot", ingredients.getIngredients().get(2).getName());
        assertEquals("5", ingredients.getIngredients().get(2).getQuantity());
        assertEquals("kg", ingredients.getIngredients().get(2).getUnit().getValue());
    }

    @Test
    public void restoreSavedRecipes_storedRecipes_returnFilledRecipeList() throws Exception {
        RecipeStorage recipeStorage = new RecipeStorage(DATA_RECIPE_TEST_PATH);
        RecipeList recipes = new RecipeList(recipeStorage.restoreSavedData());

        Recipe recipe = recipes.getRecipe(0);

        assertEquals("soup", recipe.getTitle());
        assertEquals("step1", recipe.getRecipeSteps().getStepByIndex(0).getDescription());
        assertEquals("step2", recipe.getRecipeSteps().getStepByIndex(1).getDescription());

        Ingredient ingredient = recipe.getRecipeIngredients().getIngredientByIndex(0);

        assertEquals("bread", ingredient.getName());
        assertEquals("2", ingredient.getQuantity());
        assertEquals("kg", ingredient.getUnit().getValue());

        ingredient = recipe.getRecipeIngredients().getIngredientByIndex(1);

        assertEquals("apple", ingredient.getName());
        assertEquals("2", ingredient.getQuantity());
        assertEquals("kg", ingredient.getUnit().getValue());

        recipe = recipes.getRecipe(1);

        assertEquals("steak", recipe.getTitle());
        assertEquals("step1", recipe.getRecipeSteps().getStepByIndex(0).getDescription());
        assertEquals("step2", recipe.getRecipeSteps().getStepByIndex(1).getDescription());

        ingredient = recipe.getRecipeIngredients().getIngredientByIndex(0);

        assertEquals("beef", ingredient.getName());
        assertEquals("1", ingredient.getQuantity());
        assertEquals("g", ingredient.getUnit().getValue());
    }
}
