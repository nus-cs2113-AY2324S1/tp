package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.Ingredient;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.ingredient.IngredientUnit;
import essenmakanan.parser.IngredientParser;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;
import essenmakanan.ui.Ui;

/**
 * Creates a use shortcut command.
 */
public class UseShortcutCommand extends Command {
    
    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private String input;

    /**
     * Creates a use shortcut command.
     *
     * @param shortcuts The shortcut list.
     * @param ingredients The ingredient list.
     * @param input The given input.
     */
    public UseShortcutCommand(ShortcutList shortcuts, IngredientList ingredients, String input) {
        this.shortcuts = shortcuts;
        this.ingredients = ingredients;
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        Ui.drawDivider();

        try {
            int shortcutIndex = ShortcutParser.getShortcutIndex(shortcuts, input);
            Shortcut shortcut = shortcuts.getShortcut(shortcutIndex);
            String ingredientName = shortcut.getIngredientName();
            double quantity = shortcut.getQuantity();

            int ingredientIndex = IngredientParser.getIngredientIndex(ingredients, ingredientName);
            IngredientUnit unit = ingredients.getIngredient(ingredientIndex).getUnit();

            ingredients.updateIngredient(new Ingredient(ingredientName, quantity, unit));
        } catch (EssenOutOfRangeException exception) {
            exception.handleException();
            Ui.drawDivider();
        } catch (EssenFormatException exception) {
            exception.handleException();
            Ui.drawDivider();
        }
    }
}
