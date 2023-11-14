package essenmakanan.storage;

import essenmakanan.exception.EssenFileNotFoundException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.recipe.Recipe;
import essenmakanan.recipe.RecipeList;
import essenmakanan.recipe.Step;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Executes tests related to storage.
 */
public class StorageTest {

    private static String DATA_INVALID_PATH = "src/test/data/invalid.txt";
    private static String DATA_RECIPE_TEST_PATH = "src/test/data/recipes.txt";
    private static String DATA_INGREDIENT_TEST_PATH = "src/test/data/ingredients.txt";
    private static String DATA_SHORTCUT_TEST_PATH = "src/test/data/shortcuts.txt";
    private static String DATA_INVALID_RECIPE_PATH = "src/test/data/invalid_recipes.txt";
    private static String DATA_INVALID_INGREDIENT_PATH = "src/test/data/invalid_ingredients.txt";
    private static String DATA_INVALID_SHORTCUT_PATH = "src/test/data/invalid_shortcuts.txt";

    /**
     * Executes a test related to invalid path for ingredient storage.
     */
    @Test
    public void accessIngredientDatabase_invalidPath_expectEssenFileNotFoundException() {
        IngredientStorage ingredientStorage = new IngredientStorage(DATA_INVALID_PATH);
        assertThrows(EssenFileNotFoundException.class, ingredientStorage::restoreSavedData);
    }

    /**
     * Executes a test related to invalid path for recipe storage.
     */
    @Test
    public void accessRecipeDatabase_invalidPath_expectEssenFileNotFoundException() {
        RecipeStorage recipeStorage = new RecipeStorage(DATA_INVALID_PATH);
        assertThrows(EssenFileNotFoundException.class, recipeStorage::restoreSavedData);
    }

    /**
     * Executes a test related to invalid path for shortcut storage.
     */
    @Test
    public void accessShortcutDatabase_invalidPath_expectEssenFileNotFoundException() {
        ShortcutStorage shortcutStorage = new ShortcutStorage(DATA_INVALID_PATH, new IngredientList());
        assertThrows(EssenFileNotFoundException.class, shortcutStorage::restoreSavedData);
    }

    /**
     * Executes a test to restore saved ingredients.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     */
    @Test
    public void restoreSavedIngredients_storeValidIngredients_expectFilledIngredientList()
            throws EssenFileNotFoundException {
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

    /**
     * Executes a test to restore saved ingredients with invalid format.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     */
    @Test
    public void restoreSavedIngredients_invalidDataFormat_expectEmptyList() throws EssenFileNotFoundException {
        IngredientStorage ingredientStorage = new IngredientStorage(DATA_INVALID_INGREDIENT_PATH);
        IngredientList ingredients = new IngredientList(ingredientStorage.restoreSavedData());

        assertTrue(ingredients.getIngredients().isEmpty());
    }

    /**
     * Executes a test to restore saved recipes.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     */
    @Test
    public void restoreSavedRecipes_storedValidRecipes_expectFilledRecipeList() throws EssenFileNotFoundException {
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

    /**
     * Executes a test to restore saved recipes with invalid format.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     */
    @Test
    public void restoreSavedRecipes_invalidDataFormat_expectEmptyList() throws EssenFileNotFoundException {
        RecipeStorage recipeStorage = new RecipeStorage(DATA_INVALID_RECIPE_PATH);
        RecipeList recipes = new RecipeList(recipeStorage.restoreSavedData());
        System.out.println(recipes.getRecipes().size());
        assertTrue(recipes.getRecipes().isEmpty());
    }

    /**
     * Executes a test to restore saved shortcuts.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     * @throws EssenOutOfRangeException If application tries to access out of bounds data.
     */
    @Test
    public void restoreSavedShortcuts_storedValidShortcuts_expectFilledShortcutList()
            throws EssenFileNotFoundException, EssenOutOfRangeException {
        IngredientList ingredientList = new IngredientList();
        ingredientList.addIngredient(new Ingredient("bread", 2.0, IngredientUnit.PIECE));
        ingredientList.addIngredient(new Ingredient("egg", 2.0, IngredientUnit.PIECE));

        ShortcutStorage shortcutStorage = new ShortcutStorage(DATA_SHORTCUT_TEST_PATH, ingredientList);
        ShortcutList shortcutList = new ShortcutList(shortcutStorage.restoreSavedData());

        Shortcut shortcut = shortcutList.getShortcut(0);
        assertEquals("bread", shortcut.getIngredientName());
        assertEquals(1, shortcut.getQuantity());

        shortcut = shortcutList.getShortcut(1);
        assertEquals("egg", shortcut.getIngredientName());
        assertEquals(0.1, shortcut.getQuantity());
    }

    /**
     * Executes a test to restore saved shortcuts that has no matching ingredients.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     */
    @Test
    public void restoreSavedShortcuts_storedShortcutsWithNoMatchingIngredient_expectEmptyList()
            throws EssenFileNotFoundException {
        IngredientList ingredientList = new IngredientList();

        ShortcutStorage shortcutStorage = new ShortcutStorage(DATA_SHORTCUT_TEST_PATH, ingredientList);
        ShortcutList shortcutList = new ShortcutList(shortcutStorage.restoreSavedData());

        assertTrue(shortcutList.getShortcuts().isEmpty());
    }

    /**
     * Executes a test to restore saved shortcuts with invalid format.
     *
     * @throws EssenFileNotFoundException If the text file is missing.
     */
    @Test
    public void restoreSavedShortcuts_invalidDataFormat_expectEmptyList() throws EssenFileNotFoundException {
        ShortcutStorage shortcutStorage = new ShortcutStorage(DATA_INVALID_SHORTCUT_PATH, new IngredientList());
        ShortcutList shortcuts = new ShortcutList(shortcutStorage.restoreSavedData());

        assertTrue(shortcuts.getShortcuts().isEmpty());
    }
}
