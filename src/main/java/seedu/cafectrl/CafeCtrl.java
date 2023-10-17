package seedu.cafectrl;

import seedu.cafectrl.command.Command;
import seedu.cafectrl.command.ExitCommand;
import seedu.cafectrl.data.Menu;
import seedu.cafectrl.parser.Parser;
import seedu.cafectrl.ui.Ui;

public class CafeCtrl {
    private final Ui ui;
    private final Menu menu;
    private Command command;

    /**
     * Private constructor for the CafeCtrl class, used for initializing the user interface and menu list.
     */
    public CafeCtrl() {
        ui = new Ui();
        menu = new Menu();
    }

    private void setup() {
        ui.showWelcome();
    }
    
    /**
     * The main loop of the CafeCtrl application.
     *
     * <p> This method consistently receives user input, parses commands, and executes the respective command
     * until the user enters a "bye" command, terminating the application.</p>
     */
    private void run() {
        do {
            try {
                ui.printLine();
                String fullUserInput = ui.receiveUserInput();
                command = Parser.parseCommand(menu, fullUserInput);
                command.execute(menu, ui);
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printLine();
            }
        } while (!(command instanceof ExitCommand)); //end the program if the command obj is a ExitCommand
    }

    public static void main(String[] args) {
        CafeCtrl cafeCtrl = new CafeCtrl();
        cafeCtrl.setup();
        cafeCtrl.run();
    }
}

