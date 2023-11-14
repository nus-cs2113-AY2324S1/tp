package essenmakanan.shortcut;

import essenmakanan.command.AddShortcutCommand;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;

import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Executes tests related to adding shortcuts.
 */
public class AddShortcutTest {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private AddShortcutCommand command;

    /**
     * Sets up attributes before each test.
     */
    @BeforeEach
    public void setup() {
        shortcuts = new ShortcutList();
        ingredients = new IngredientList();
        Ingredient ingredient = new Ingredient("bread", 2.0, IngredientUnit.PIECE);
        ingredients.addIngredient(ingredient);
    }

    /**
     * Executes test related to adding shortcuts with valid input.
     *
     * @throws EssenOutOfRangeException If application tries to access out of bounds data.
     */
    @Test
    public void addShortcut_validShortcut_expectShortcutInList() throws EssenOutOfRangeException {
        String userInput = "sc/bread,2";
        command = new AddShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        assertEquals("bread", shortcuts.getShortcut(0).getIngredientName());
        assertEquals(2.0, shortcuts.getShortcut(0).getQuantity());
    }
}
