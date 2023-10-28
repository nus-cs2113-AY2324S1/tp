package seedu.cafectrl.storage;

import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class Decoder {
    private static Ui ui = new Ui();
    public static Pantry decodePantryStockData(ArrayList<String> encodedPantryStock) {
        ArrayList<Ingredient> pantryStock = new ArrayList<>();
        for (String encodedData : encodedPantryStock) {
            String[] decodedData = encodedData.split(" ");
            if (decodedData.length != 3) {
                ui.showToUser("Error in pantry stock data file! Skipping this particular ingredient!");
            } else if (isValidQuantityFormat(decodedData[1])) {
                Ingredient ingredient = new Ingredient(decodedData[0],
                        Integer.parseInt(decodedData[1]), decodedData[1]);
                pantryStock.add(ingredient);
            }
        }
        return new Pantry(ui, pantryStock);
    }

    private static boolean isValidQuantityFormat(String quantityInString) {
        try {
            Integer.parseInt(quantityInString);
        } catch (NumberFormatException e) {
            ui.showToUser("Error in pantry stock data file! Skipping this particular ingredient!");
            return false;
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
