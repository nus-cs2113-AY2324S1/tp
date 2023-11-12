package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.Step;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    private static String DATA_INVALID_PATH = "src/test/data/invalid.txt";
    private static String DATA_RECIPE_TEST_PATH = "src/test/data/recipes.txt";
    private static String DATA_INGREDIENT_TEST_PATH = "src/test/data/ingredients.txt";

    @Test
    public void accessIngredientDatabase_invalidPath_expectEssenFileNotFoundException() {
        IngredientStorage ingredientStorage = new IngredientStorage(DATA_INVALID_PATH);
        assertThrows(EssenFileNotFoundException.class, ingredientStorage::restoreSavedData);
    }

    @Test
    public void accessRecipeDatabase_invalidPath_expectEssenFileNotFoundException() {
        RecipeStorage recipeStorage = new RecipeStorage(DATA_INVALID_PATH);
        assertThrows(EssenFileNotFoundException.class, recipeStorage::restoreSavedData);
    }

    @Test
    public void restoreSavedIngredients_storeValidIngredients_returnsFilledIngredientList() throws Exception {
        IngredientStorage ingredientStorage = new IngredientStorage(DATA_INGREDIENT_TEST_PATH);
        IngredientList ingredients = new IngredientList(ingredientStorage.restoreSavedData());

        assertEquals("bread", ingredients.getIngredients().get(0).getName());
        assertEquals(2.0, ingredients.getIngredients().get(0).getQuantity());
        assertEquals("g", ingredients.getIngredients().get(0).getUnit().getValue());

        assertEquals("cheese", ingredients.getIngredients().get(1).getName());
        assertEquals(10.0, ingredients.getIngredients().get(1).getQuantity());
        assertEquals("pc", ingredients.getIngredients().get(1).getUnit().getValue());

        assertEquals("carrot", ingredients.getIngredients().get(2).getName());
        assertEquals(5.0, ingredients.getIngredients().get(2).getQuantity());
        assertEquals("kg", ingredients.getIngredients().get(2).getUnit().getValue());
    }

    @Test
    public void restoreSavedRecipes_storedValidRecipes_returnFilledRecipeList() throws Exception {
        RecipeStorage recipeStorage = new RecipeStorage(DATA_RECIPE_TEST_PATH);
        RecipeList recipes = new RecipeList(recipeStorage.restoreSavedData());

        Recipe recipe = recipes.getRecipe(0);

        assertEquals("bread", recipe.getTitle());

        Step step = recipe.getRecipeSteps().getStepByIndex(0);
        assertEquals("step1", step.getDescription());
        assertEquals(1, step.getTag().getPriority());

        Ingredient ingredient = recipe.getRecipeIngredients().getIngredientByIndex(0);

        assertEquals("bread", ingredient.getName());
        assertEquals(5.0, ingredient.getQuantity());
        assertEquals("kg", ingredient.getUnit().getValue());
    }

}
