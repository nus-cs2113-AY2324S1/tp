package essenmakanan.parser;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenShortcutException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.shortcut.Shortcut;

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

        Double quantity = Double.parseDouble(shortcutDetails[1].strip());

        return new Shortcut(ingredientName, quantity);
    }
}
