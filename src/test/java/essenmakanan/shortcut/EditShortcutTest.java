package essenmakanan.shortcut;

import essenmakanan.command.EditShortcutCommand;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Executes tests related to editing shortcut.
 */
public class EditShortcutTest {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private EditShortcutCommand command;

    /**
     * Sets up attributes before each test.
     */
    @BeforeEach
    public void setup() {
        shortcuts = new ShortcutList();
        ingredients = new IngredientList();
        ingredients.addIngredient(new Ingredient("bread", 2.0, IngredientUnit.PIECE));
        ingredients.addIngredient(new Ingredient("egg", 2.0, IngredientUnit.PIECE));
        shortcuts.addShortcut(new Shortcut("bread", 2.0));
    }

    /**
     * Executes a test related to editing a shortcut.
     *
     * @throws EssenOutOfRangeException If application tries to access out of bounds data.
     */
    @Test
    public void editShortcut_validInput_expectEditedShortcut() throws EssenOutOfRangeException {
        String userInput = "sc/bread n/egg q/3";
        command = new EditShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        Shortcut shortcut = shortcuts.getShortcut(0);
        assertEquals("egg", shortcut.getIngredientName());
        assertEquals(3, shortcut.getQuantity());

        userInput = "sc/1 q/0.1 n/bread";
        command = new EditShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        shortcut = shortcuts.getShortcut(0);
        assertEquals("bread", shortcut.getIngredientName());
        assertEquals(0.1, shortcut.getQuantity());
    }

    /**
     * Executes a test related to editing a shortcut with no changes.
     *
     * @throws EssenOutOfRangeException If application tries to access out of bounds data.
     */
    @Test
    public void editShortcut_sameProperty_expectShortcutWithUnchangedProperty() throws EssenOutOfRangeException {
        String userInput = "sc/bread n/bread q/3";
        command = new EditShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        Shortcut shortcut = shortcuts.getShortcut(0);
        assertEquals("bread", shortcut.getIngredientName());
        assertEquals(3, shortcut.getQuantity());

        userInput = "sc/bread n/egg q/3";
        command = new EditShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        shortcut = shortcuts.getShortcut(0);
        assertEquals("egg", shortcut.getIngredientName());
        assertEquals(3, shortcut.getQuantity());
    }

    /**
     * Executes a test related to editing a shortcut with multiple of the same flags.
     *
     * @throws EssenOutOfRangeException If application tries to access out of bounds data.
     */
    @Test
    public void editShortcut_moreThanOneSameFlag_expectShortcutWithOneChange() throws EssenOutOfRangeException {
        String userInput = "sc/bread n/egg n/bread";
        command = new EditShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        Shortcut shortcut = shortcuts.getShortcut(0);
        assertEquals("egg", shortcut.getIngredientName());

        userInput = "sc/bread q/3 q/4";
        command = new EditShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        shortcut = shortcuts.getShortcut(0);
        assertEquals("egg", shortcut.getIngredientName());
        assertEquals(2, shortcut.getQuantity());
    }
}
