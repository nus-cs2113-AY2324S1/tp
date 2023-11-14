package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.CommandManager;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.utils.Ui;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class HelpCommand extends Command {
    public static final String NAME = "help";

    public static final String USAGE = "help [COMMAND]";
    public static final String EXAMPLE =
            "help" + "\n" +
            "help budget";

    private static final String HELP_MESSAGE_GENERAL =
            "<> denotes required arguments, [] denotes optional arguments";

    private static final String HELP_ALL_PREFIX =
            "Here are the available commands:";

    // Will replace this in the future
    private static final String DELIMITER = "------------------------------";

    private final String commandName;

    public HelpCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
        if (rawCommand.args.isEmpty()) {
            commandName = null;
        } else if (rawCommand.args.size() == 1) {
            commandName = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Unknown arguments, type help for help");
        }
    }

    @Override
    public void execute() throws Exception {
        Ui ui = Ui.getInstance();
        CommandManager commandManager = CommandManager.getInstance();
        if (commandName == null) {
            ui.showMessage(HELP_MESSAGE_GENERAL);
            ui.showMessage(HELP_ALL_PREFIX);
            ui.showMessage(DELIMITER);
            for (String name : commandManager.getCommandNames()) {
                String usage = commandManager.getCommandUsage(name);
                String example = commandManager.getCommandExample(name);
                ui.showMessage("Usage of " + name + ":");
                ui.showMessage(usage);
                ui.showMessage("Example usage of " + name + ":");
                ui.showMessage(example);
                ui.showMessage(DELIMITER);
            }
            return;
        }
        String commandUsage = commandManager.getCommandUsage(commandName.toLowerCase());
        String commandExample = commandManager.getCommandExample(commandName.toLowerCase());
        ui.showMessage(HELP_MESSAGE_GENERAL);
        ui.showMessage("Usage of " + commandName.toLowerCase() + ":");
        ui.showMessage(commandUsage);
        ui.showMessage("Example usage of " + commandName.toLowerCase() + ":");
        ui.showMessage(commandExample);
    }
}
