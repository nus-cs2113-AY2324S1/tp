package essenmakanan.command;

import essenmakanan.ui.Ui;

/**
 * Represent a command in the application.
 */
public abstract class Command {
    protected Ui ui;

    /**
     * Creates a new command.
     */
    public Command() {
        ui = new Ui();
    }

    /**
     * Executes the command's functions.
     */
    public abstract void executeCommand();
}
