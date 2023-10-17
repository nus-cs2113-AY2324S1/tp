package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Menu;
import seedu.duke.data.dish.Dish;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteDishCommandTest {

    @Test
    void execute_validInput() throws Exception {
        ArrayList<Dish> menuItems = new ArrayList<>();
        Menu menu = new Menu(menuItems);
        menu.addDish(new Dish("Chicken Rice", 2.50F));
        menu.addDish(new Dish("Chicken Curry", 4.30F));
        menu.addDish(new Dish("Nasi Lemak", 5.60F));

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };

        int testIndex = 2;
        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(testIndex);
        deleteDishCommand.execute(menu, ui);

        int actualOutputIndex = 0;
        String expectedOutput = "Okay! Chicken Curry is deleted! :)";
        assertEquals(expectedOutput, actualOutput.get(actualOutputIndex));
    }

    @Test
    void execute_invalidInput() throws Exception {
        ArrayList<Dish> menuItems = new ArrayList<>();
        Menu menu = new Menu(menuItems);
        menu.addDish(new Dish("Chicken Rice", 2.50F));
        menu.addDish(new Dish("Chicken Curry", 4.30F));
        menu.addDish(new Dish("Nasi Lemak", 5.60F));

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };

        int testIndex = 5;
        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(testIndex);
        deleteDishCommand.execute(menu, ui);

        int actualOutputIndex = 0;
        String expectedOutput = "Please select a valid dish index :)";
        assertEquals(expectedOutput, actualOutput.get(actualOutputIndex));
    }
}
