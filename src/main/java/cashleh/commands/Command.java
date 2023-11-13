package cashleh.commands;

import cashleh.exceptions.CashLehException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Base class for commands in the CashLeh application.
 * It provides a simple mechanism for executing commands and includes a logger for logging command-related activities.
 */
public class Command {
    protected Logger logger = Logger.getLogger("CommandLogger");
    protected Level loggerLevel = Level.OFF;

    public Command() {
        this.logger.setLevel(loggerLevel);
    }

    public void execute() throws CashLehException {}
}
