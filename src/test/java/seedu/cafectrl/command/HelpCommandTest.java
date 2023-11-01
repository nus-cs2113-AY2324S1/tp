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

        ArrayList<String> expectedOutputs = new ArrayList<>();

        expectedOutputs.add(Messages.LINE_STRING);
        expectedOutputs.add(Messages.LIST_OF_COMMANDS);
        expectedOutputs.add(Messages.INSTRUCTION_ON_COMMAND_FORMAT);
        expectedOutputs.add(AddDishCommand.MESSAGE_USAGE);
        expectedOutputs.add(AddOrderCommand.MESSAGE_USAGE);
        expectedOutputs.add(BuyIngredientCommand.MESSAGE_USAGE);
        expectedOutputs.add(DeleteDishCommand.MESSAGE_USAGE);
        expectedOutputs.add(EditPriceCommand.MESSAGE_USAGE);
        expectedOutputs.add(ExitCommand.MESSAGE_USAGE);
        expectedOutputs.add(HelpCommand.MESSAGE_USAGE);
        expectedOutputs.add(ListIngredientCommand.MESSAGE_USAGE);
        expectedOutputs.add(ListMenuCommand.MESSAGE_USAGE);
        expectedOutputs.add(NextDayCommand.MESSAGE_USAGE);
        expectedOutputs.add(PreviousDayCommand.MESSAGE_USAGE);
        expectedOutputs.add(ViewTotalStockCommand.MESSAGE_USAGE);

        for (int i = 0; i < expectedOutputs.size(); i++) {
            assertEquals(expectedOutputs.get(i), actualOutputs.get(i));
        }
    }
}
