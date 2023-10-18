package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Ui;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeleteDishCommandTest {

    @Test
    void execute_validInput() {
        Menu menu = new Menu();
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
    void execute_invalidInput_throwIndexOutOfBoundsException() {
        Menu menu = new Menu();
        menu.addDish(new Dish("Chicken Rice", 2.50F));
        menu.addDish(new Dish("Chicken Curry", 4.30F));
        menu.addDish(new Dish("Nasi Lemak", 5.60F));

        Ui ui = new Ui();
        int testIndex = 5;
        DeleteDishCommand deleteDishCommand = new DeleteDishCommand(testIndex);

        assertThrows(IndexOutOfBoundsException.class, () -> deleteDishCommand.execute(menu, ui));
    }
}
