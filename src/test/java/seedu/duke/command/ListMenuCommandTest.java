package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Menu;
import seedu.duke.data.dish.Dish;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

class ListMenuCommandTest {
    @Test
    public static void main(String[] args) {
        ArrayList<Dish> menuItems = new ArrayList<>();
        menuItems.add(new Dish("Chicken Rice", 2.50F));
        menuItems.add(new Dish("Chicken Curry", 4.30F));
        Menu menu = new Menu(menuItems);

        Command ListMenuCommand = new ListMenuCommand();
        ListMenuCommand.execute(menu, new Ui());
    }
}
