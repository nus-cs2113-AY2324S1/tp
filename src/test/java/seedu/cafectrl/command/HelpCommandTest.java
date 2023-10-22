package seedu.cafectrl.command;

import org.junit.jupiter.api.Test;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.ui.Messages;
import seedu.cafectrl.command.HelpCommand;
import seedu.cafectrl.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest {

    @Test
    void execute_validInput_editPrice() {

        ArrayList<String> actualOutput = new ArrayList<>();
        Menu menu = new Menu();
        Ui ui = new Ui() {
            @Override
            public void showToUser(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }

            @Override
            public void showToUserWithSpaceBetweenLines(String... message) {
                actualOutput.addAll(Arrays.asList(message));
            }
        };

        HelpCommand helpCommand = new HelpCommand();
        helpCommand.execute(menu, ui);
    }

}