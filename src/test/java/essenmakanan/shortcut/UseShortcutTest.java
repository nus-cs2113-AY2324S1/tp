package essenmakanan.shortcut;

import essenmakanan.command.UseShortcutCommand;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UseShortcutTest {

    private ShortcutList shortcuts;
    private IngredientList ingredients;

    private UseShortcutCommand command;

    @BeforeEach
    public void setup() {
        shortcuts = new ShortcutList();
        ingredients = new IngredientList();
        Ingredient ingredient = new Ingredient("bread", 2.0, IngredientUnit.PIECE);
        ingredients.addIngredient(ingredient);
    }

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
