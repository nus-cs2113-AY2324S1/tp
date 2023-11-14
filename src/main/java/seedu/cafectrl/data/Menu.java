package seedu.cafectrl.data;

import seedu.cafectrl.CafeCtrl;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Menu {
    private static final Logger logger = Logger.getLogger(CafeCtrl.class.getName());
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
            String formattedMenuDishName = menuDishName.trim();
            if (formattedMenuDishName.equalsIgnoreCase(formattedDishName)) {
                return getDishFromId(i);
            }
        }
        return null;
    }

    //@@author NaychiMin
    /**
     * Retrieves an ArrayList of Order objects representing aggregated orders for each menu item.
     * Each Order object is initialized with a dish from the menu and a quantity of 0.
     * Used in the print_sales function under Sales class.
     *
     * @return An ArrayList of Order objects representing aggregated orders for each menu item.
     */
    public ArrayList<Order> getAggregatedOrders() {
        logger.info("Getting aggregated orders...");
        ArrayList<Order> aggregatedOrders = new ArrayList<>();
        for (int i = 0; i < menuItems.size(); i++) {
            Order order = new Order(menuItems.get(i), 0);
            aggregatedOrders.add(order);
        }
        return aggregatedOrders;
    }

    //@@author ziyi105
    /**
     * Checks whether the dish index can be found in the menu
     *
     * @param dishIndex dish index to be checked
     * @return true if it is valid, false otherwise
     */
    public boolean isValidDishIndex(int dishIndex) {
        logger.info("Checking if dish index " + dishIndex + " is valid...");

        int offSetDishIndex = dishIndex - Ui.OFFSET_LIST_INDEX;
        return offSetDishIndex >= 0 && offSetDishIndex < this.getSize();
    }

    //@@author DextheChik3n
    public void removeDish(int menuID) {
        menuItems.remove(menuID);
    }
    public void addDish(Dish dish) {
        menuItems.add(dish);
    }
}
