package seedu.cafectrl.storage;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.ErrorMessages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class Decoder {
    private static final Ui ui = new Ui();
    public static Pantry decodePantryStockData(ArrayList<String> encodedPantryStock) {
        ArrayList<Ingredient> pantryStock = new ArrayList<>();

        if (encodedPantryStock.isEmpty()) {
            return new Pantry(ui);
        }
        for (String encodedData : encodedPantryStock) {
            String[] decodedData = encodedData.split(" ");
            if (!isValidPantryStockFormat(decodedData)) {
                ui.showToUser(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA);
            } else {
                Ingredient ingredient = new Ingredient(decodedData[0],
                        Integer.parseInt(decodedData[1]), decodedData[2]);
                pantryStock.add(ingredient);
            }
        }
        return new Pantry(ui, pantryStock);
    }

    private static boolean isValidPantryStockFormat(String[] decodedPantryStock) {
        if (decodedPantryStock.length != 3) {
            ui.showToUser(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA);
            return false;
        } else {
            try {
                Integer.parseInt(decodedPantryStock[2]);
            } catch (NumberFormatException e) {
                ui.showToUser(ErrorMessages.ERROR_IN_PANTRY_STOCK_DATA);
                return false;
            }
        }
        return true;
    }

    //@@author Dexter
    public Menu decodeMenuData(ArrayList<String> textLines) {
        ArrayList<Dish> dishArrayList = new ArrayList<>();
        for (String task : textLines) {
            String[] splitTaskString = task.split(" \\| ");
            String dishName = splitTaskString[0];
            float dishPrice = Float.parseFloat(splitTaskString[1]);
            String dishIngredient = splitTaskString[2];

            try {
                //todo: remove testing code
                Dish dish = new Dish(dishName, dishPrice);
                dishArrayList.add(dish);
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            }
        }

        return new Menu(dishArrayList);
    }
}
