package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;

class ListMenuCommandTest {
    @Test
    public static void execute_expectTwoDishes() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice", 2.50F));
        menuItems.add(new Dish("Chicken Curry", 4.30F));
        Menu menu = new Menu(menuItems);

        Command listMenuCommand = new ListMenuCommand();
        listMenuCommand.execute(menu, new Ui());
    }
}
