package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;

import java.util.ArrayList;

/**
 * Represents a command to exit the program.
 */
@SuppressWarnings("unused")
public class ExitCommand extends Command {
    public static final String NAME = "exit";

    public static final String USAGE =
            "exit";
    public static final String EXAMPLE =
            "exit";

    public ExitCommand(RawCommand rawCommand) {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
    }
}
