package essenmakanan.shortcut;

import essenmakanan.command.UseShortcutCommand;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Executes tests related to using a shortcut.
 */
public class UseShortcutTest {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private UseShortcutCommand command;

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
     * Executes a test related to using a shortcut.
     */
    @Test
    public void useShortcut_availableShortcut_expectUpdatedIngredient() {
        shortcuts.addShortcut(new Shortcut("bread", 2.0));

        String userInput = "1";
        command = new UseShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        Ingredient ingredient = ingredients.getIngredient(0);
        assertEquals(4, ingredient.getQuantity());
    }
}
