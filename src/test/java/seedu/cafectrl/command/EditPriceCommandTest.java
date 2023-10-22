package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.data.dish.Dish;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditPriceCommandTest {

    @Test
    void execute_validInput_editPrice() {
        Menu menu = new Menu();
        Dish testDish = new Dish("Chicken Rice", 2.50F);
        menu.addDish(testDish);

        ArrayList<String> actualOutput = new ArrayList<>();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };

        int testDishIndex = 1;
        float testNewPrice = 3;
        Command editPriceCommand = new EditPriceCommand(testDishIndex, testNewPrice);
        editPriceCommand.execute(menu, ui);

        int firstLine = 0;
        int secondLine = 1;
        String expectedOutputFirstLine = Messages.PRICE_MODIFIED_MESSAGE;
        String expectedOutputSecondLine = testDish.toString();
        assertEquals(expectedOutputFirstLine, actualOutput.get(firstLine));
        assertEquals(expectedOutputSecondLine, actualOutput.get(secondLine));
    }
}
