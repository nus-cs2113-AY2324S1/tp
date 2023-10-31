package seedu.financialplanner.commands;

import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteReminderCommand extends Command{
    private static final Logger logger = Logger.getLogger("Financial Planner Logger");
    private final int index;
    public DeleteReminderCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        if(rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Incorrect arguments.");
        }

        try {
            logger.log(Level.INFO, "Parsing index as integer");
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Invalid argument for index");
            throw new IllegalArgumentException("Index must be an integer");
        }

        if (index == 0) {
            logger.log(Level.WARNING, "Invalid value for index");
            throw new IllegalArgumentException("Index must be within the list");
        }

        if (index > ReminderList.getInstance().list.size() + 1) {
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

    @Override
    public void execute() {
        ReminderList.getInstance().deleteReminder(index);
        Ui.getInstance().showMessage("You have deleted " + ReminderList.getInstance().list.get(index-1));
    }
}
