package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Execute tests related to shortcut parser.
 */
public class ShortcutParserTest {

    private IngredientList ingredients;

    /**
     * Sets up attributes before each test.
     */
    @BeforeEach
    public void setup() {
        ingredients = new IngredientList();
        Ingredient ingredient = new Ingredient("bread", 2.0, IngredientUnit.PIECE);
        ingredients.addIngredient(ingredient);
    }

    /**
     * Execute tests for parsing a shortcut with invalid format.
     */
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

    /**
     * Execute tests for parsing a shortcut with invalid quantity.
     */
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
