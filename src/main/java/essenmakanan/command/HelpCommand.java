package essenmakanan.command;

/**
 * Represents a help command.
 */
public class HelpCommand extends Command {

    /**
     * Creates a help command.
     */
    public HelpCommand() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeCommand() {
        ui.showCommands();
    }
}
