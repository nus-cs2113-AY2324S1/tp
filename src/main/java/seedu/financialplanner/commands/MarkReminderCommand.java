package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class MarkReminderCommand extends Command {
    public static final String NAME = "markreminder";

    public static final String USAGE = "markreminder <INDEX>";
    public static final String EXAMPLE = "markreminder 1";
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private final int index;

    /**
     * Constructor of the command to mark a reminder as done.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public MarkReminderCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        if (rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Please specify a valid index of reminder");
        }

        try {
            logger.log(Level.INFO, "Parsing index as integer");
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid argument for index");
            throw new IllegalArgumentException("Index must be a valid integer");
        }
        if (index <= 0) {
            logger.log(Level.WARNING, "Invalid value for index");
            throw new IllegalArgumentException("Index must be within the list");
        }
        if (index > ReminderList.getInstance().list.size()) {
            logger.log(Level.WARNING, "Invalid value for index");
            throw new IllegalArgumentException("Index exceed the list size");
        }
        rawCommand.extraArgs.remove("i");
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to mark the reminder.
     */
    @Override
    public void execute() {
        assert index > 0 && index <= ReminderList.getInstance().list.size();
        ReminderList.getInstance().list.get(index - 1).markAsDone();
        Ui.getInstance().showMessage("You have marked " + ReminderList.getInstance().list.get(index - 1));
    }


}
