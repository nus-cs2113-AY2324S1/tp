package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenInvalidQuantityException;
import essenmakanan.exception.EssenShortcutException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;
import essenmakanan.parser.ShortcutParser;
import essenmakanan.ui.Ui;

public class AddShortcutCommand extends Command {

    private ShortcutList shortcuts;
    private IngredientList ingredients;
    private String input;

    public AddShortcutCommand(ShortcutList shortcuts, IngredientList ingredients, String input) {
        this.shortcuts = shortcuts;
        this.ingredients = ingredients;
        this.input = input;
    }

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
