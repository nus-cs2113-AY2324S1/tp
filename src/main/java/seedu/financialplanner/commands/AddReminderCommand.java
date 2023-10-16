package seedu.financialplanner.commands;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;
public class AddReminderCommand extends AbstractCommand{
    private final String type;
    private final String date;

    public AddReminderCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String typeString = String.join(" ", rawCommand.args);
        if(!rawCommand.extraArgs.containsKey("t")){
            throw new IllegalArgumentException("Reminder must have a type");
        }
        type = rawCommand.extraArgs.get("t");
        rawCommand.extraArgs.remove("t");
        if(!rawCommand.extraArgs.containsKey("d")){
            throw new IllegalArgumentException("Reminder must have a date");
        }
        date = rawCommand.extraArgs.get("d");
        rawCommand.extraArgs.remove("d");
        if(!rawCommand.extraArgs.isEmpty()){
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        ReminderList.INSTANCE.addReminder(type, date);
        Ui.INSTANCE.showMessage("Reminder added!");
    }
}
