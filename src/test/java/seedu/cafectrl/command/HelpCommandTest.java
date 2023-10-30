package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {

    @Test
    void execute_validInput_editPrice() {

        ArrayList<String> actualOutputs = new ArrayList<>();
        Menu menu = new Menu();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutputs.addAll(Arrays.asList(message));
            }

            @Override
            public void showToUserWithSpaceBetweenLines(String... message) {
                actualOutputs.addAll(Arrays.asList(message));
            }
        };

        HelpCommand helpCommand = new HelpCommand(ui);
        helpCommand.execute();
        int numOfLines = 8;

        ArrayList<String> expectedOutputs = new ArrayList<>();

        expectedOutputs.add(Messages.LINE_STRING);
        expectedOutputs.add(Messages.LIST_OF_COMMANDS);
        expectedOutputs.add(Messages.INSTRUCTION_ON_COMMAND_FORMAT);
        expectedOutputs.add(AddDishCommand.MESSAGE_USAGE);
        expectedOutputs.add(ListIngredientCommand.MESSAGE_USAGE);
        expectedOutputs.add(Messages.LIST_MENU_GUIDE);
        expectedOutputs.add(Messages.DELETE_GUIDE);
        expectedOutputs.add(EditPriceCommand.MESSAGE_USAGE);

        for (int i = 0; i < numOfLines; i++) {
            assertEquals(expectedOutputs.get(i), actualOutputs.get(i));
        }
    }
}
