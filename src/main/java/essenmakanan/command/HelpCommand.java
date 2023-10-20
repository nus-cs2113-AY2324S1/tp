package essenmakanan.command;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }

    @Override
    public void executeCommand() {
        ui.showCommands();
    }
}
