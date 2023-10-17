package seedu.duke.data;

import seedu.duke.data.dish.Dish;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Dish> menuItems;

    public Menu(ArrayList<Dish> menuItems) {
        this.menuItems = menuItems;
    }

    public ArrayList<Dish> getMenuItemsList() {
        return menuItems;
    }
    public int getSize() {
        return menuItems.size();
    }
    public Dish getDish(int menuID) {
        return menuItems.get(menuID);
    }
    public void removeDish(int menuID) {
        menuItems.remove(menuID);
    }
    public void addDish(Dish dish) {
        menuItems.add(dish);
    }

    public boolean isValidDishIndex(int dishIndex) {
        int offSetDishIndex = dishIndex - Ui.OFFSET_LIST_INDEX;
        return offSetDishIndex >= 0 && offSetDishIndex < this.getSize();
    }
}
