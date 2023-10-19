package cashleh.commands;

import cashleh.exceptions.CashLehException;
import java.util.logging.Logger;
/**
 * Base class for commands in the CashLeh application.
 * It provides a simple mechanism for executing commands and includes a logger for logging command-related activities.
 */
public class Command {
    protected Logger logger = Logger.getLogger("CommandLogger");
    private int index;

    public Command(int index) {
        this.index = index;
    }

    public Command() {
    }

    public int getIndex() {
        return this.index;
    }

    public void execute() throws CashLehException {}
}
