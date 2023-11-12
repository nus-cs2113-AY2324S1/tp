package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenOutOfRangeException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;

public class EditShortcutCommand extends Command {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private String input;

    public EditShortcutCommand(ShortcutList shortcuts, IngredientList ingredients, String input) {
        this.shortcuts = shortcuts;
        this.ingredients = ingredients;
        this.input = input;
    }

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
