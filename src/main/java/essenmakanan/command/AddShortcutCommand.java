package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenInvalidQuantityException;
import essenmakanan.exception.EssenShortcutException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.ui.Ui;

/**
 * Represents an add shortcut command.
 */
public class AddShortcutCommand extends Command {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private String input;

    /**
     * Creates an add shortcut command.
     *
     * @param shortcuts The shortcut list.
     * @param ingredients The ingredient list.
     * @param input The given input.
     */
    public AddShortcutCommand(ShortcutList shortcuts, IngredientList ingredients, String input) {
        this.shortcuts = shortcuts;
        this.ingredients = ingredients;
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        try {
            Shortcut shortcut = ShortcutParser.parseShortcut(ingredients, input);

            if (shortcuts.exist(shortcut.getIngredientName())) {
                throw new EssenShortcutException();
            }

            shortcuts.addShortcut(shortcut);
            Ui.printAddShortcutSuccess(shortcut);
        } catch (EssenFormatException exception) {
            Ui.drawDivider();
            exception.handleException();
            Ui.drawDivider();
        } catch (EssenShortcutException exception) {
            exception.handleException();
        } catch (NumberFormatException exception) {
            EssenInvalidQuantityException.handleException();
        }
    }
}
