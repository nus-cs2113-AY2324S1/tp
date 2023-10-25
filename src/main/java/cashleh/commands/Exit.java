package cashleh.commands;

import cashleh.Ui;

public class Exit extends Command {

    @Override
    public void execute() {
        Ui.printText("Bye. Hope to see you again soon!");
    }

}
