package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Messages;
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

        ArrayList<String> commandOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                String parseString = convertArrayToString(message, ",");
                commandOutput.add(parseString);
            }
            @Override
            public void formatListMenu(String dishName, String dishPrice) {
                String leftAlignFormat = "| %-24s | %-12s |";
                String parseString = String.format(leftAlignFormat, dishName, dishPrice);
                commandOutput.add(parseString);
            }
        };

        Command listMenuCommand = new ListMenuCommand(menu, ui);
        listMenuCommand.execute();

        String actualOutput = String.join(",", commandOutput);

        String expectedOutput = Messages.MENU_END_CAP
                + Messages.LIST_MENU_MESSAGE
                + Messages.MENU_CORNER
                + Messages.MENU_TITLE
                + Messages.MENU_CORNER
                + "| 1. Chicken Rice          |  $2.50       |"
                + "| 2. Chicken Curry         |  $4.30       |"
                + Messages.MENU_END_CAP;

        assert (expectedOutput.trim().replaceAll(",", "").equals(actualOutput.trim().replaceAll(",", "")));
    }

    @Test
    public void execute_emptyMenu_expectEmptyMenuMessage() {
        ArrayList<Dish> menuItems = new ArrayList<>();
        Menu menu = new Menu(menuItems);
        assertEquals(0, menu.getSize());
        ArrayList<String> commandOutput = new ArrayList<>();

        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                String parseString = convertArrayToString(message, ",");
                commandOutput.add(parseString);
            }
        };

        Command listMenuCommand = new ListMenuCommand(menu, ui);
        listMenuCommand.execute();

        String actualOutput = String.join(",", commandOutput);

        String expectedOutput = Messages.MENU_EMPTY_MESSAGE;

        assert (expectedOutput.trim().replaceAll(",", "").equals(actualOutput.trim().replaceAll(",", "")));
    }

    private static String convertArrayToString(String[] message, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : message) {
            sb.append(str.toString()).append(delimiter);
        }
        return sb.substring(0, sb.length() - 1);
    }
}
