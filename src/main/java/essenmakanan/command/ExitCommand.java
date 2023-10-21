package essenmakanan.command;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void executeCommand() {
        ui.bye();
    }

    public static boolean isExitCommand(Command command) {
        return (command instanceof ExitCommand);
    }
}
