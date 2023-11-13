package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;
import java.util.logging.Logger;
import java.util.logging.Level;

@SuppressWarnings("unused")
public class ReminderListCommand extends Command {
    public static final String NAME = "reminderlist";

    public static final String USAGE = "reminderlist";
    public static final String EXAMPLE = "reminderlist";

    private static final Logger logger = Logger.getLogger("Financial Planner Logger");

    /**
     * Constructor of the command to list goals.
     *
     * @param rawCommand The input from the user.
     * @throws IllegalArgumentException if erroneous inputs are detected.
     */
    public ReminderListCommand(RawCommand rawCommand) throws IllegalArgumentException {
        if (!rawCommand.extraArgs.isEmpty()) {
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            logger.log(Level.WARNING, "Invalid extra arguments found");
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    /**
     * Executes the command to list the reminders.
     */
    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ReminderList reminderList = ReminderList.getInstance();
        if (reminderList.list.isEmpty()) {
            ui.showMessage("You have no reminders.");
            return;
        }
        ui.showMessage("Here is your reminder list:");
        ui.showMessage(reminderList.toString());
    }
}
