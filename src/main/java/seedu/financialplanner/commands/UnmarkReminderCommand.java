package seedu.financialplanner.commands;
import seedu.financialplanner.reminder.ReminderList;
import seedu.financialplanner.utils.Ui;
public class UnmarkReminderCommand extends Command{
    private final int index;
    public UnmarkReminderCommand(RawCommand rawCommand) throws IllegalArgumentException {
        String stringIndex;
        if(rawCommand.args.size() == 1) {
            stringIndex = rawCommand.args.get(0);
        } else {
            throw new IllegalArgumentException("Incorrect arguments.");
        }

        try {
            index = Integer.parseInt(stringIndex);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Index must be an integer");
        }
        if (index == 0) {
            throw new IllegalArgumentException("Index must be within the list");
        }
        if( index > ReminderList.getInstance().list.size()+1){
            throw new IllegalArgumentException("Index exceed the list size");
        }
        rawCommand.extraArgs.remove("i");
        if(!rawCommand.extraArgs.isEmpty()){
            String unknownExtraArgument = new java.util.ArrayList<>(rawCommand.extraArgs.keySet()).get(0);
            throw new IllegalArgumentException(String.format("Unknown extra argument: %s", unknownExtraArgument));
        }
    }

    @Override
    public void execute() {
        ReminderList.getInstance().list.get(index-1).unmark();
        Ui.getInstance().showMessage("You have unmarked "+ReminderList.getInstance().list.get(index-1));
    }
}
