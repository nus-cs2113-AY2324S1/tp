package seedu.cafectrl.storage;

import seedu.cafectrl.OrderList;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.Pantry;
import seedu.cafectrl.data.dish.Ingredient;

import java.util.ArrayList;

public class Encoder {

    public static ArrayList<String> encodePantryStock(Pantry pantry) {
        // Convert pantry stock to a list of String
        ArrayList<String> pantryStockInString = new ArrayList<>();
        ArrayList<Ingredient> pantryStock = pantry.getPantryStock();
        for (Ingredient ingredient : pantryStock) {
            String encodedIngredient = ingredient.getName() + " "
                    + ingredient.getQty() + " " + ingredient.getUnit();
            pantryStockInString.add(encodedIngredient);
        }
        return pantryStockInString;
    }

    public static ArrayList<String> encodeOrderList(OrderList orderList) {
        return null;
    }

    //@@author DextheChik3n
    public static ArrayList<String> encodeMenu(Menu menu) {
        /*
        ArrayList<Dish> dishArrayList = menu.getMenuItemsList();
        String tasksFilePathString = this.fileManager.openTextFile();

        if (dishArrayList.isEmpty()) {
            this.fileManager.overwriteFile(tasksFilePathString, ""); //overwrite text file to store empty text
        }
        //todo: remove testing code
        dishArrayList.add(new Dish("test", (float) 1.2));
        dishArrayList.add(new Dish("test", (float) 1.2));

        //input arraylist data into text file
        for (int i = 0; i < dishArrayList.size(); i++) {
            String taskDataRow = "chicken rice | 5.00 | rice 50g";

            if (i == 0) {
                this.fileManager.overwriteFile(tasksFilePathString, taskDataRow);
            } else {
                this.fileManager.appendToFile(tasksFilePathString, taskDataRow);
            }
        }
         */
        return null;
    }
}
