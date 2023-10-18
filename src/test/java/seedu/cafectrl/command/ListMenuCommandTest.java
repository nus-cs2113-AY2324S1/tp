package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListMenuCommandTest {
    @Test
    public void execute_addTwoDishes_expectTwoDishes() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice", 2.50F));
        menuItems.add(new Dish("Chicken Curry", 4.30F));
        Menu menu = new Menu(menuItems);
        assertEquals(2, menu.getSize());

        Command listMenuCommand = new ListMenuCommand();
        listMenuCommand.execute(menu, new Ui());
    }
}
