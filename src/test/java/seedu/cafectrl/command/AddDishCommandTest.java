package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.data.dish.Ingredient;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddDishCommandTest {
    @Test
    void execute_oneDishAdded_expectDishInMenu() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Dish> menuItems = new ArrayList<>();
        Menu menu = new Menu(menuItems);
        //creating a dish
        ingredients.add(new Ingredient("chicken", 100, "g"));
        Dish dish = new Dish("Chicken Rice", ingredients, (float) 1.00);
        Ui ui = new Ui();

        AddDishCommand addDishCommand = new AddDishCommand(dish, menu, ui);
        addDishCommand.execute();

        assertEquals(1, menu.getMenuItemsList().size());
    }
}
