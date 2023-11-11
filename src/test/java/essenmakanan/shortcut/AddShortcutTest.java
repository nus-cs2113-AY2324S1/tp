package essenmakanan.shortcut;

import essenmakanan.command.AddShortcutCommand;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;

import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.ShortcutParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddShortcutTest {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private AddShortcutCommand addshortcutcommand;

    @BeforeEach
    public void setup() {
        shortcuts = new ShortcutList();
        ingredients = new IngredientList();
        Ingredient ingredient = new Ingredient("bread", 2.0, IngredientUnit.PIECE);
        ingredients.addIngredient(ingredient);
    }

    @Test
    public void addShortcut_validShortcut_expectShortcutInList() throws EssenOutOfRangeException {
        String userInput = "sc/bread,2";
        addshortcutcommand = new AddShortcutCommand(shortcuts, ingredients, userInput);
        addshortcutcommand.executeCommand();

        assertEquals("bread", shortcuts.getShortcut(0).getIngredientName());
        assertEquals(2.0, shortcuts.getShortcut(0).getQuantity());
    }

    @Test
    public void parseShortcut_invalidFormat_expectEssenFormatException() {
        String shortcutWithMissingQuantity = "bread,";
        assertThrows(EssenFormatException.class, () -> {
            ShortcutParser.parseShortcut(ingredients, shortcutWithMissingQuantity);
        });

        String shortcutWithMissingName = ",2";
        assertThrows(EssenFormatException.class, () -> {
            ShortcutParser.parseShortcut(ingredients, shortcutWithMissingName);
        });

        String blankString = "    ";
        assertThrows(EssenFormatException.class, () -> {
            ShortcutParser.parseShortcut(ingredients, blankString);
        });
    }

    @Test
    public void parseShortcut_invalidQuantity_expectNumberFormatException() {
        String shortcutWithNegativeQuantity = "bread,-1";
        assertThrows(NumberFormatException.class, () -> {
            ShortcutParser.parseShortcut(ingredients, shortcutWithNegativeQuantity);
        });

        String shortcutWithZeroQuantity = "bread,0";
        assertThrows(NumberFormatException.class, () -> {
            ShortcutParser.parseShortcut(ingredients, shortcutWithZeroQuantity);
        });

        String shortcutWithStringQuantity = "bread,1abcd";
        assertThrows(NumberFormatException.class, () -> {
            ShortcutParser.parseShortcut(ingredients, shortcutWithStringQuantity);
        });
    }
}
