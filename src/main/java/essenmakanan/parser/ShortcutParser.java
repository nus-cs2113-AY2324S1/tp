package essenmakanan.parser;

import essenmakanan.exception.EssenEditShortcutException;
import essenmakanan.exception.EssenFormatException;
import essenmakanan.exception.EssenInvalidQuantityException;
import essenmakanan.exception.EssenShortcutException;
import essenmakanan.ingredient.IngredientList;
import essenmakanan.shortcut.Shortcut;
import essenmakanan.shortcut.ShortcutList;
import essenmakanan.ui.Ui;

/**
 * Represents a parser related to shortcuts
 */
public class ShortcutParser {

    /**
     * Parse a shortcut based on the input and the ingredient list.
     *
     * @param ingredients The ingredient list.
     * @param input The given input.
     * @return A shortcut based on the input.
     * @throws EssenFormatException If the format is incorrect.
     * @throws EssenShortcutException If the shortcut refers to a non-existing ingredient.
     * @throws NumberFormatException If the quantity is invalid.
     */
    public static Shortcut parseShortcut(IngredientList ingredients, String input) throws EssenFormatException
            , EssenShortcutException, NumberFormatException {
        input = input.replace("sc/", "");
        String[] shortcutDetails = input.split(",");

        if (shortcutDetails.length != 2 || shortcutDetails[0].isBlank()) {
            throw new EssenFormatException();
        }

        String ingredientName = shortcutDetails[0].strip();

        if (!ingredients.exist(ingredientName)) {
            throw new EssenShortcutException();
        }

        double quantity = Double.parseDouble(shortcutDetails[1].strip());

        if (!IngredientParser.checkForValidQuantity(quantity)) {
            throw new NumberFormatException();
        }

        return new Shortcut(ingredientName, quantity);
    }

    /**
     * Gets shortcut index based on the input.
     *
     * @param shortcuts The shortcut list.
     * @param input The given input.
     * @return The index of the shortcut in the list.
     */
    public static int getShortcutIndex(ShortcutList shortcuts, String input) {
        int index;

        if (input.matches("\\d+")) { //if input only contains numbers
            index = Integer.parseInt(input) - 1;
        } else {
            index = shortcuts.getIndex(input);
        }

        return index;
    }

    /**
     * Changes the shortcut's name into a new name based on the ingredient list.
     *
     * @param shortcuts The shortcut list.
     * @param shortcut The shortcut that is going to be edited.
     * @param ingredients The ingredient list.
     * @param editDetail The new name.
     * @param hasEditName The status if the user has edited the name once in one line.
     */
    private static void editShortcutName(ShortcutList shortcuts, Shortcut shortcut, IngredientList ingredients
            , String editDetail , boolean hasEditName) {
        String newName = editDetail.substring(2).strip();
        String oldName = shortcut.getIngredientName();

        try {
            if (hasEditName) {
                throw new EssenEditShortcutException("usage");
            }

            if (newName.equals(oldName)) {
                throw new EssenEditShortcutException("same name");
            }

            if (!ingredients.exist(newName) || shortcuts.exist(newName)) {
                throw new EssenShortcutException();
            }

            Ui.printEditShortcutName(shortcut.getIngredientName(), newName);
            shortcut.setIngredientName(newName);
        } catch (EssenShortcutException exception) {
            exception.handleException();
        } catch (EssenEditShortcutException exception) {
            exception.handleException();
        }
    }

    /**
     * Changes the shortcut's quantity into a new quantity.
     *
     * @param shortcut The shortcut that is going to be edited.
     * @param editDetail The new quantity.
     * @param hasEditQuantity The status if the user has edited the quantity once in one line.
     */
    private static void editShortcutQuantity(Shortcut shortcut, String editDetail, boolean hasEditQuantity) {
        try {
            double newQuantity = Double.parseDouble(editDetail.substring(2).strip());
            if (hasEditQuantity) {
                throw new EssenEditShortcutException("usage");
            }

            if (!IngredientParser.checkForValidQuantity(newQuantity)) {
                throw new NumberFormatException();
            }

            if (newQuantity == shortcut.getQuantity()) {
                throw new EssenEditShortcutException("same quantity");
            }

            Ui.printEditShortcutQuantity(shortcut.getQuantity(), newQuantity);
            shortcut.setQuantity(newQuantity);
        } catch (NumberFormatException exception) {
            EssenInvalidQuantityException.handleException();
        } catch (EssenEditShortcutException exception) {
            exception.handleException();
        }
    }

    /**
     * Edits the shortcut's properties based on the flags.
     *
     * @param shortcuts The shortcut list.
     * @param shortcut The shortcut that is going to be edited.
     * @param ingredients The ingredient list.
     * @param editDetails A string filled with changes to be made.
     * @throws EssenFormatException If the format is incorrect.
     */
    public static void editShortcut(ShortcutList shortcuts, Shortcut shortcut, IngredientList ingredients
            , String[] editDetails) throws EssenFormatException {
        boolean hasEditName = false;
        boolean hasEditQuantity = false;

        for (int i = 1; i < editDetails.length; i++) {

            if (editDetails[i].isBlank()) {
                continue;
            }

            String flag = editDetails[i].substring(0, 2).strip();
            switch (flag) {
            case "n/":
                editShortcutName(shortcuts, shortcut, ingredients, editDetails[i], hasEditName);
                hasEditName = true;
                break;
            case "q/":
                editShortcutQuantity(shortcut, editDetails[i], hasEditQuantity);
                hasEditQuantity = true;
                break;
            default:
                throw new EssenFormatException();
            }
        }
    }

    /**
     * Converts a shortcut into string form.
     *
     * @param shortcut A shortcut
     * @return A shortcut that has been converted into a string.
     */
    public static String convertToString(Shortcut shortcut) {
        return shortcut.getIngredientName() + " | " + shortcut.getQuantity();
    }
}
