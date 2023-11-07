package seedu.financialplanner.commands;

import seedu.financialplanner.commands.utils.Command;
import seedu.financialplanner.commands.utils.RawCommand;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;

@SuppressWarnings("unused")
public class ReminderListCommand extends Command {
    public static final String NAME = "reminderlist";

    public static final String USAGE = "UNDONE, PLEASE FILL THIS UP!";

    public ReminderListCommand(RawCommand rawCommand) throws IllegalArgumentException {

    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ReminderList reminderList = ReminderList.getInstance();
        ui.showMessage("Here is your reminder list:");
        ui.showMessage(reminderList.toString());
    }
}
