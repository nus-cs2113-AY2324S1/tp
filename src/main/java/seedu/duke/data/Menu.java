package seedu.duke.data;

import seedu.duke.data.dish.Dish;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Dish> menuItems;

    public Menu(ArrayList<Dish> menuItems) {
        this.menuItems = menuItems;
    }
}
