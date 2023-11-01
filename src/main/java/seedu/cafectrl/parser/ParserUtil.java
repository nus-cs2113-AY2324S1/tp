package seedu.cafectrl.parser;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.data.CurrentDate;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.Sales;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

/**
 * Parser interface for external class to use Parser
 */
public interface ParserUtil {
    Command parseCommand(Menu menu, String userInput, Ui ui,
            Pantry pantry, Sales sales, CurrentDate currentDate);

    //@@author DextheChik3n
    /**
     * Converts text of price to float while also checking if the price input is within reasonable range
     * @param priceText text input for price argument
     * @return price in float format
     * @throws ArithmeticException if price > 10000000000.00
     */
    static float parsePriceToFloat(String priceText) throws ArithmeticException {
        float price = Float.parseFloat(priceText);
        float maxPriceValue = (float) 10000000000.00;

        if (price > maxPriceValue) {
            throw new ArithmeticException();
        }

        return price;
    }

    /**
     * Checks in the menu if the dish name already exists in the menu.
     * @param inputDishName dish name entered by the user
     * @param menu contains all the existing Dishes
     * @return boolean of whether a repeated dish name is detected
     * @throws NullPointerException if the input string is null
     */
    static boolean isRepeatedDishName(String inputDishName, Menu menu) throws NullPointerException {
        if (inputDishName == null) {
            throw new NullPointerException();
        }

        for (Dish dish: menu.getMenuItemsList()) {
            String menuDishNameLowerCase = dish.getName().toLowerCase();
            String inputDishNameLowerCase = inputDishName.toLowerCase();

            if (menuDishNameLowerCase.equals(inputDishNameLowerCase)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks the length of the name is too long
     * @param inputName name
     * @return boolean of whether the name is more than max character limit set
     * @throws NullPointerException if the input string is null
     */
    static boolean isNameLengthValid(String inputName) throws NullPointerException {
        int maxNameLength = 35;

        if (inputName == null) {
            throw new NullPointerException();
        }

        if (inputName.length() > maxNameLength) {
            return false;
        }

        return true;
    }
}
