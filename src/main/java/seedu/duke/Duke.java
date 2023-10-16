package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.Menu;
import seedu.duke.parser.Parser;
import seedu.duke.ui.Ui;
import seedu.duke.command.ExitCommand;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    private Ui ui;
    private Menu menu;
    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program until termination.  */
    public void run() {
        runCommandLoopUntilExitCommand();
        exit(ui);
    }
    /** Reads the user command and executes it, until the user issues the exit command.  */
    public void runCommandLoopUntilExitCommand() {
        do {
            String item = ui.receiveUserInput();
            Command c = Parser.parseCommand(menu, item);
            c.execute(menu, ui);
        } while (!ExitCommand.isExit());
    }
    private static void exit(Ui ui) {
        ui.showGoodbye();
        System.exit(0);
    }
}
