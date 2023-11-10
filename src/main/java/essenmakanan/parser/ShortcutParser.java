package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenShortcutException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;

public class ShortcutParser {

    public static Shortcut parseShortcut(IngredientList ingredients, String input) throws EssenFormatException
            , EssenShortcutException, NumberFormatException {
        input = input.replace("sc/", "");
        String[] shortcutDetails = input.split(",");

        if (shortcutDetails.length != 2) {
            throw new EssenFormatException();
        }

        String ingredientName = shortcutDetails[0].strip();

        if (!ingredients.exist(ingredientName)) {
            throw new EssenShortcutException();
        }

        double quantity = Double.parseDouble(shortcutDetails[1].strip());

        if (quantity < 0 || (int) quantity == 0) {
            throw new NumberFormatException();
        }

        return new Shortcut(ingredientName, quantity);
    }

    public static int getShortcutIndex(ShortcutList shortcuts, String input) {
        int index;

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = shortcuts.getIndex(input);
        }

        return index;
    }
}
