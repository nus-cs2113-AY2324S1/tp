package essenmakanan.shortcut;

import essenmakanan.command.AddShortcutCommand;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;

import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddShortcutTest {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private AddShortcutCommand command;

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
        command = new AddShortcutCommand(shortcuts, ingredients, userInput);
        command.executeCommand();

        assertEquals("bread", shortcuts.getShortcut(0).getIngredientName());
        assertEquals(2.0, shortcuts.getShortcut(0).getQuantity());
    }
}
