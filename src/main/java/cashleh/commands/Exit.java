package cashleh.commands;

import cashleh.Ui;

public class Exit extends Command {
    private final Ui ui = new Ui();
    @Override
    public void execute() {
        ui.printText("Bye. Hope to see you again soon!");
    }

}
