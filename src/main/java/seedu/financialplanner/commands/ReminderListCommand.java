package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;

@SuppressWarnings("unused")
public class ReminderListCommand extends Command {
    public static final String NAME = "reminderlist";

    public static final String USAGE = "reminderlist";
    public static final String EXAMPLE = "reminderlist";

    public ReminderListCommand(RawCommand rawCommand) throws IllegalArgumentException {

    }

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
