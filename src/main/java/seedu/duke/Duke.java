package seedu.duke;

import seedu.duke.Ui.Ui;
import java.util.Scanner;


public class Duke {
    private Ui ui;

    public void run() {
        ui = new Ui();
        Ui.printWelcomeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}