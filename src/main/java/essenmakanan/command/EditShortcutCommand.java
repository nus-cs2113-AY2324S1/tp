package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;

/**
 * Represents an edit shortcut command.
 */
public class EditShortcutCommand extends Command {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private String input;

    /**
     * Creates an edit shortcut command.
     *
     * @param shortcuts The shortcut list.
     * @param ingredients The ingredient list.
     * @param input The given input.
     */
    public EditShortcutCommand(ShortcutList shortcuts, IngredientList ingredients, String input) {
        this.shortcuts = shortcuts;
        this.ingredients = ingredients;
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        input = input.replace("sc/", "");

        String[] splitDetails = input.split(" ");

        try {
            if (splitDetails.length == 0) {
                throw new EssenFormatException();
            }

            int shortcutIndex = ShortcutParser.getShortcutIndex(shortcuts, splitDetails[0]);
            Shortcut shortcut = shortcuts.getShortcut(shortcutIndex);

            ShortcutParser.editShortcut(shortcut, ingredients, splitDetails);
        } catch (EssenOutOfRangeException exception) {
            exception.handleException();
        } catch (EssenFormatException exception) {
            exception.handleException();
        }
    }
}
