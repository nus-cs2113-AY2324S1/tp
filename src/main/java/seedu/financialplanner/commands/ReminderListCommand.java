package seedu.financialplanner.commands;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;
public class ReminderListCommand extends Command{
    public ReminderListCommand(RawCommand rawCommand) throws IllegalArgumentException{

    }

    @Override
    public void execute() {
        Ui ui = Ui.getInstance();
        ReminderList reminderList = ReminderList.getInstance();
        ui.showMessage("Here is your reminder list:");
        ui.showMessage(ReminderList.getInstance().list.toString());
    }
}
