package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Menu;
import seedu.duke.data.dish.Dish;
import seedu.duke.data.dish.Ingredient;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddDishCommandTest {
    @Test
    void execute_dishAdded_expectDishInMenu() {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Dish> menuItems = new ArrayList<>();
        Menu menu = new Menu(menuItems);
        Ui ui = new Ui();
        //creating a dish
        ingredients.add(new Ingredient("chicken", "100g"));
        Dish dish = new Dish("Chicken Rice", ingredients, (float) 1.00);
        
        AddDishCommand addDishCommand = new AddDishCommand(dish);

        addDishCommand.execute(menu, ui);

        assertEquals(1, menu.getMenuItemsList().size());
    }
}
