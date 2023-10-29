package essenmakanan.command;

import essenmakanan.exception.EssenFormatException;
import essenmakanan.ui.Ui;

public abstract class Command {
    protected Ui ui;

    public Command() {
        ui = new Ui();
    }

    public abstract void executeCommand();
}
