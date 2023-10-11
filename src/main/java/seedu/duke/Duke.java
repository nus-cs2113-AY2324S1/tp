package seedu.duke;

import seedu.duke.Ui.Ui;
import seedu.duke.Commands.Parser;

public class Duke {
    private Ui ui;

    //ui
    public void run() {
        ui = new Ui();
        Ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            String command = Parser.parse(fullCommand);
            String[] parts = command.split("-", 2);
            switch(parts[0]) {
                case "exit":
                    isExit = true;
                    ui.showLineDivider();
                    break;

                default:
                    ui.showLineDivider();
                    System.out.println("Invalid command. Please try again.");
                    ui.showLineDivider();
                    break;
            }
        }
        ui.printGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
