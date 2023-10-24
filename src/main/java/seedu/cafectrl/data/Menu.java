package seedu.cafectrl.data;

import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Dish> menuItems;

    public Menu() {
        this.menuItems = new ArrayList<>();
    }

    public Menu(ArrayList<Dish> menuItems) {
        this.menuItems = menuItems;
    }

    public ArrayList<Dish> getMenuItemsList() {
        return menuItems;
    }
    public int getSize() {
        return menuItems.size();
    }
    public Dish getDishFromId(int menuID) {
        return menuItems.get(menuID);
    }

    /**
     * Checks if the ordered dish exist in the menu and returns the menu index if exist
     *
     * @param dishName Name of the ordered dish
     * @return index of the dish in menu if exists, null if not found
     */
    public Dish getDishFromName(String dishName) {
        String formattedDishName = dishName.toLowerCase().trim();
        for (int i = 0; i < getSize(); i++) {
            String menuDishName = getDishFromId(i).getName();
            String formattedMenuDishName = menuDishName.toLowerCase().trim();
            if (formattedMenuDishName.equals(formattedDishName)){
                return getDishFromId(i);
            }
        }
        return null;
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
